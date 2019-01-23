package com.romanenko.lew.testworkaxon.dI.models;

import android.content.Context;

import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.network.di.models.PicassoModule;
import com.romanenko.lew.testworkaxon.network.di.models.RandomUserAPIModule;
import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;
import com.romanenko.lew.testworkaxon.model.UserRepository;
import com.romanenko.lew.testworkaxon.network.RandomUserApi;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
@Module()
public class RepositoryModule {

    @Provides
    @MainActivityScope
    UserRepository provideRepo( Context context,RandomUserApi randomUserApi,Picasso picasso){
        return new UserRepository(context,randomUserApi,picasso);
    }
}
