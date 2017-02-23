package com.vishesh.tpc_stud.core;


import com.vishesh.tpc_stud.auth.views.LoginFragment;
import com.vishesh.tpc_stud.core.modules.ActivityModule;
import com.vishesh.tpc_stud.core.modules.ApiServiceModule;
import com.vishesh.tpc_stud.core.modules.SchedulersModule;
import com.vishesh.tpc_stud.core.scopes.PerActivity;
import com.vishesh.tpc_stud.dashboard.views.DashboardFragment;
import com.vishesh.tpc_stud.dashboard.views.ProfileFragment;
import com.vishesh.tpc_stud.dashboard.views.RecruitersFragment;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class, ApiServiceModule.class, SchedulersModule.class})
public interface ActivityComponent {

    void inject(LoginFragment loginFragment);

    void inject(DashboardFragment dashboardFragment);

    void inject(RecruitersFragment recruitersFragment);

    void inject(ProfileFragment profileFragment);
}