package com.vishesh.tpc_stud.core.dagger;


import dagger.Component;

@PerActivity
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class, SchedulersModule.class,
                MockApiServiceModule.class})
public interface ActivityComponent extends TpcStudActivityComponent {


}
