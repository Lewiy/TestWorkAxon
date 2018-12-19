package com.romanenko.lew.testworkaxon.model;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import io.reactivex.Observable;


public interface ReposutoryRandomUsers {

  Observable<RandomUserContainer> getRandomUsers(int number);
  Observable<Result> getRandomUser(int userId);

}
