package com.romanenko.lew.testworkaxon.dI.components;

import com.romanenko.lew.testworkaxon.dI.models.UserProfileModule;
import com.romanenko.lew.testworkaxon.dI.models.UsersListModule;
import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.screens.UserProfileFragment;

import dagger.Component;

@Component(modules = {UserProfileModule.class})
@MainActivityScope
public interface ActivityComponentUserProfile {
    void inject(UserProfileFragment userProfileView);
}
