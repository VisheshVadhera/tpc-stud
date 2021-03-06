package com.vishesh.placement.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.vishesh.placement.R;
import com.vishesh.placement.auth.constants.AuthConstants;
import com.vishesh.placement.auth.views.LoginActivity;
import com.vishesh.placement.dashboard.views.DashboardActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    /**
     * Testing Strategy:
     * <p>
     * <p>
     * For login flow, partition the state space as follows:
     * <p>
     * 1. Successful login and no cancellation by the user,
     * 2. Successful login and cancellation by the user,
     * 3. Unsuccessful login.
     */
    private static final String FAKE_AUTH_CODE = "123";
    private static final String FAKE_ACCESS_TOKEN = "abc";
    private static final int FAKE_TOKEN_REFRESH_INTERVAL = 24;

    @Rule
    public IntentsTestRule<LoginActivity> loginActivityIntentsTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(
                loginActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Covers the following case:
     * Successful login and no cancellation by the user
     */
    @Test
    public void clickLoginButton_successFulLogin_openDashboard() {

        ActivityResult activityResult = createSuccessfulLoginResultStub();

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);

        onView(withId(R.id.button_login)).perform(click());

        intended(hasComponent(new ComponentName(
                InstrumentationRegistry.getTargetContext(),
                DashboardActivity.class)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.action_logout))
                .check(matches(isDisplayed()));
    }

    /**
     * Covers the following case:
     * Successful login and cancellation by the user
     */
    @Test
    public void clickLoginButton_successFulLogin_cancelled_showSnackBar() {

        ActivityResult activityResult = createSuccessfulLoginCancelledResultStub();

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);

        onView(withId(R.id.button_login)).perform(click());

        onView(withText(AuthConstants.LOGIN_CANCELLED_MSG))
                .check(matches(withEffectiveVisibility(
                        Visibility.VISIBLE)));
    }

    /**
     * Covers the following case:
     * unsuccessful login
     */
    @Test
    public void clickLoginButton_unsuccessfulLogin() {

        ActivityResult activityResult = createUnsuccessfulLoginResultStub();

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);

        onView(withId(R.id.button_login)).perform(click());

        onView(withText(AuthConstants.ACCOUNT_KIT_ERROR_MSG))
                .check(matches(withEffectiveVisibility(
                        Visibility.VISIBLE)));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                loginActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    private ActivityResult createSuccessfulLoginResultStub() {
        Intent intent = new Intent();
        AccountKitLoginResult accountKitLoginResult = new FakeAccountKitLoginResult(
                getAccessToken(),
                FAKE_AUTH_CODE,
                false,
                null,
                "authState",
                24);
        intent.putExtra(AccountKitLoginResult.RESULT_KEY, accountKitLoginResult);
        return new ActivityResult(Activity.RESULT_OK, intent);
    }

    private ActivityResult createSuccessfulLoginCancelledResultStub() {
        Intent intent = new Intent();
        AccountKitLoginResult accountKitLoginResult = new FakeAccountKitLoginResult(
                null,
                null,
                true,
                null,
                "authState",
                FAKE_TOKEN_REFRESH_INTERVAL);
        intent.putExtra(AccountKitLoginResult.RESULT_KEY, accountKitLoginResult);
        return new ActivityResult(Activity.RESULT_OK, intent);
    }

    private AccessToken getAccessToken() {
        return new AccessToken(FAKE_ACCESS_TOKEN,
                "1", "appId", FAKE_TOKEN_REFRESH_INTERVAL, new Date());
    }

    private ActivityResult createUnsuccessfulLoginResultStub() {
        Intent intent = new Intent();
        AccountKitLoginResult accountKitLoginResult = new FakeAccountKitLoginResult(
                null,
                null,
                false,
                getAccountKitError(),
                null,
                FAKE_TOKEN_REFRESH_INTERVAL
        );
        intent.putExtra(AccountKitLoginResult.RESULT_KEY, accountKitLoginResult);
        return new ActivityResult(Activity.RESULT_OK, intent);
    }

    private AccountKitError getAccountKitError() {
        return new AccountKitError(
                AccountKitError.Type.NETWORK_CONNECTION_ERROR,
                InternalAccountKitError.NO_NETWORK_CONNECTION);
    }
}
