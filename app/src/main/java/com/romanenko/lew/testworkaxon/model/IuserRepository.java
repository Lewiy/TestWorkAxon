package com.romanenko.lew.testworkaxon.model;

import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import java.util.List;

import io.reactivex.Observable;


public interface IuserRepository {
  Observable<Result> getRandomUser(String email);
  Observable<List<Result>> getRandomUsers(int number);
}
