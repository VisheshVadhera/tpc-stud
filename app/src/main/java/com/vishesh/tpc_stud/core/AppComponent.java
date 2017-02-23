package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.core.modules.ApiServiceModule;
import com.vishesh.tpc_stud.core.modules.AppModule;
import com.vishesh.tpc_stud.core.modules.DataModule;
import com.vishesh.tpc_stud.core.modules.RetrofitModule;
import com.vishesh.tpc_stud.core.modules.SchedulersModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vishesh on 12/2/17.
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class,
        RetrofitModule.class, SchedulersModule.class, DataModule.class})
public interface AppComponent {

}
