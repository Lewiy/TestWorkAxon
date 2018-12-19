package com.romanenko.lew.testworkaxon.network;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserApi {

    @GET("/api/")
    Observable<RandomUserContainer> getRandomUser(@Query("results") int number);
}
