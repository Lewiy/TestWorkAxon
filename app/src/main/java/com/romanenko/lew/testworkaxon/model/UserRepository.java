package com.romanenko.lew.testworkaxon.model;

import android.content.Context;
import android.util.Log;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.network.RandomUserApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository implements IuserRepository {


   private  RandomUserApi randomUsersApi;
   private  Picasso picasso;
   private  Context context;

    private Observable<List<Result>> randomUsersListFull;
    private List<Result> randomUsers = new ArrayList<>();


    public void setRandomUserContainer(List<Result> randomUsers) {
        this.randomUsers.addAll(randomUsers);
    }

   public UserRepository(Context context, RandomUserApi randomUserApi, Picasso picasso) {
        this.context = context;
        this.randomUsersApi = randomUserApi;
        this.picasso = picasso;
    }

    @Override
    public Observable<Result> getRandomUser(final String email) {
        return randomUsersListFull
                //.flatMapIterable(list -> Observable.fromIterable(list))
                .flatMap(list -> Observable.fromIterable(list))
                .filter(user -> user.getEmail().equals(email));
    }

    @Override
    public Observable<List<Result>> getRandomUsers(int number) {

        Observable <List<Result>> freshData = randomUsersApi.getRandomUser(number)
                .map(RandomUserContainer::getResults)
                .doOnNext(value -> Log.d("LevDebag",value + "FreshData"))
                .doOnNext(value -> randomUsers.addAll(value));


        randomUsersListFull = Observable.just(randomUsers)
        .mergeWith(freshData);


        return freshData;
    }

}
