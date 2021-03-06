package com.vishesh.placement.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.vishesh.placement.auth.constants.AuthConstants;
import com.vishesh.placement.auth.models.AccessToken;
import com.vishesh.placement.auth.useCases.LoginUseCase;
import com.vishesh.placement.core.presenters.BasePresenter;
import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.views.BaseView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class LoginPresenter extends BasePresenter {

    private LoginView loginView;

    private final LoginUseCase loginUseCase;
    private final LocalCache localCache;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase, LocalCache localCache) {
        this.loginUseCase = loginUseCase;
        this.localCache = localCache;
    }

    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void onEmailLoginResultReceived(AccountKitLoginResult accountKitLoginResult) {

        if (accountKitLoginResult.getError() != null) {

            loginView.showMessage(AuthConstants.ACCOUNT_KIT_ERROR_MSG);

        } else if (accountKitLoginResult.wasCancelled()) {

            loginView.showMessage(AuthConstants.LOGIN_CANCELLED_MSG);

        } else if (accountKitLoginResult.getAuthorizationCode() != null) {
            Map<String, String> map = new HashMap<>();
            map.put("authorizationCode", accountKitLoginResult.getAuthorizationCode());
            loginView.showLoader();
            loginUseCase.execute(new LoginObserver(), map, null);
        }
    }

    public void onEmailLoginClicked() {
        AccountKitConfiguration accountKitConfiguration =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE).build();

        loginView.startLoginProcess(accountKitConfiguration);
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void openDashboard();

        void startLoginProcess(AccountKitConfiguration accountKitConfiguration);
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            localCache.saveAccessToken(value.getAccessToken());
            loginView.hideLoader();
            loginView.openDashboard();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }
}
