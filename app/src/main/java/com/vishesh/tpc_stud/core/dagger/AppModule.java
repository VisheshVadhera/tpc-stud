package com.vishesh.tpc_stud.core.dagger;

import android.content.Context;

import com.vishesh.tpc_stud.TpcStudApplication;
import com.vishesh.tpc_stud.core.helpers.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final TpcStudApplication tpcStudApplication;

    public AppModule(TpcStudApplication tpcStudApplication) {
        this.tpcStudApplication = tpcStudApplication;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return tpcStudApplication;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
