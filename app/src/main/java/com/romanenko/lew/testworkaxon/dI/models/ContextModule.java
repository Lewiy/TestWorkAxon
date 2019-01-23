package com.romanenko.lew.testworkaxon.dI.models;

import android.content.Context;

import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
