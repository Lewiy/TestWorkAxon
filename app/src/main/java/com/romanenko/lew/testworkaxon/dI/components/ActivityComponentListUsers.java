package com.romanenko.lew.testworkaxon.dI.components;

import com.romanenko.lew.testworkaxon.dI.models.UsersListModule;
import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.network.di.components.RandomUserAPIComp;
import com.romanenko.lew.testworkaxon.screens.randomUserList.ListUsersFragment;

import dagger.Component;

@Component(dependencies = RandomUserAPIComp.class, modules = {UsersListModule.class})
@MainActivityScope
public interface ActivityComponentListUsers {
    void inject(ListUsersFragment listUsersView);
}
