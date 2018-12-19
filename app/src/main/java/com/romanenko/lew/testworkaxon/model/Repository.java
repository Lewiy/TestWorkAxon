package com.romanenko.lew.testworkaxon.model;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.network.RandomUserApi;
import com.romanenko.lew.testworkaxon.network.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;

public class Repository implements ReposutoryRandomUsers {

    private Observable<RandomUserContainer> randomUserContainerObservable;
    private List<Result> randomUsers = new ArrayList<>();
    private static Repository repository;

    public void setRandomUserContainer(List<Result> randomUsers) {
        this.randomUsers.addAll(randomUsers);
}

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
    public Observable<Result> getRandomUser(String email) {

        return Observable.just(search(email));
    }

    private Result search(String email){
        for (Result result:randomUsers) {
            if(result.getEmail().toString().equals(email))
                return result;
        }
        return null;
    }



}
