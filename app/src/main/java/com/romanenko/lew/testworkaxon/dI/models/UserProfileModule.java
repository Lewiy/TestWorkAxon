package com.romanenko.lew.testworkaxon.dI.models;

import com.romanenko.lew.testworkaxon.base.RandomUserProfileContract;
import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;

import dagger.Module;
import dagger.Provides;

@Module
public class UserProfileModule {

    RandomUserProfileContract.ViewListRandomUsers view;
    RandomUserProfileContract.PresenterListRandomUsers presenter;

    public UserProfileModule(RandomUserProfileContract.ViewListRandomUsers view, RandomUserProfileContract.PresenterListRandomUsers presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    @Provides
    public RandomUserProfileContract.ViewListRandomUsers provideView() {
        return view;
    }

    @MainActivityScope
    @Provides
    public RandomUserProfileContract.PresenterListRandomUsers providePresenter() {
        return presenter;
    }
}
