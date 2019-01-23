package com.romanenko.lew.testworkaxon.dI.components;

import com.romanenko.lew.testworkaxon.dI.models.RepositoryModule;
import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.model.UserRepository;
import com.romanenko.lew.testworkaxon.network.di.components.RandomUserAPIComp;
import com.romanenko.lew.testworkaxon.network.di.scopes.ApplicationScope;

import dagger.Component;

@MainActivityScope
@Component(dependencies = RandomUserAPIComp.class, modules = RepositoryModule.class)
public interface RepositoryComponent {
    UserRepository getUserRepository();
}
