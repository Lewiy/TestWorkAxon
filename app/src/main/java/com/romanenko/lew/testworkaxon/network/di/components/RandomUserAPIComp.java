package com.romanenko.lew.testworkaxon.network.di.components;

import android.content.Context;

import com.romanenko.lew.testworkaxon.dI.models.ContextModule;
import com.romanenko.lew.testworkaxon.network.RandomUserApi;
import com.romanenko.lew.testworkaxon.network.di.models.PicassoModule;
import com.romanenko.lew.testworkaxon.network.di.models.RandomUserAPIModule;
import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

@ApplicationScope
@Component(modules = {RandomUserAPIModule.class,PicassoModule.class})
public interface RandomUserAPIComp {
    RandomUserApi  randomUserAPI();
    Context context();
    Picasso picasso();
}
