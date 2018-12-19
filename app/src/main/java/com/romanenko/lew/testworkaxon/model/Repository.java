package com.romanenko.lew.testworkaxon.model;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.network.RandomUserApi;
import com.romanenko.lew.testworkaxon.network.Services;

import io.reactivex.Observable;

public class Repository implements ReposutoryRandomUsers {

    private Observable<RandomUserContainer> randomUserContainerObservable;

    public void setRandomUserContainer(RandomUserContainer randomUserContainer) {
        this.randomUserContainer = randomUserContainer;
    }

    private RandomUserContainer randomUserContainer;

    private static Repository repository;

    private Repository() {
    }

    public static Repository getInstance() {
        if (repository != null)
            return repository;
        else {
            repository = new Repository();
            return repository;
        }

    }


    @Override
    public Observable<RandomUserContainer> getRandomUsers(int number) {
        RandomUserApi randomUserApi = Services.createService(RandomUserApi.class);
        this.randomUserContainerObservable = randomUserApi.getRandomUser(number);
        return randomUserContainerObservable;
    }


    @Override
    public Observable<Result> getRandomUser(final int userId) {
               return Observable.just(randomUserContainer.getResults().get(userId));
    }


}
