package com.romanenko.lew.testworkaxon;

import android.app.Application;
import android.content.Context;

import com.romanenko.lew.testworkaxon.dI.components.DaggerRepositoryComponent;
import com.romanenko.lew.testworkaxon.dI.components.RepositoryComponent;
import com.romanenko.lew.testworkaxon.dI.models.RepositoryModule;
import com.romanenko.lew.testworkaxon.network.di.components.DaggerRandomUserAPIComp;
import com.romanenko.lew.testworkaxon.network.di.components.RandomUserAPIComp;
import com.romanenko.lew.testworkaxon.dI.models.ContextModule;

public class App extends Application{

    private RandomUserAPIComp randomUserApplicationComponent;

    private RepositoryComponent repositoryComponent;

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // Timber.plant(new Timber.DebugTree());

        randomUserApplicationComponent = DaggerRandomUserAPIComp.builder()
                .contextModule(new ContextModule(this))
                .build();


        repositoryComponent = DaggerRepositoryComponent.builder()
                .randomUserAPIComp(randomUserApplicationComponent)
                .build();



    }

    public RandomUserAPIComp getRandomUserApplicationComponent(){
        return randomUserApplicationComponent;
    }

    public RepositoryComponent getRepositoryComponent() {
        return repositoryComponent;
    }
}
