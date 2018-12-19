package com.romanenko.lew.testworkaxon.presenters;

import android.content.Context;

import com.romanenko.lew.testworkaxon.base.BasePresenter;
import com.romanenko.lew.testworkaxon.base.RandomUserProfileContract;
import com.romanenko.lew.testworkaxon.model.Repository;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailsPresenter extends BasePresenter<RandomUserProfileContract.ViewListRandomUsers> implements RandomUserProfileContract.PresenterListRandomUsers {


    private Context context;

    public UserDetailsPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void loadRandomUserProfile(int userId) {

        Observable<Result> randomUserProfile = Repository.getInstance().getRandomUser(userId);

        randomUserProfile
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result randomUserProfile) {
                        //getView().loadListRandomUsers(randomUserContainer.getResults());

                        getView().loadCallPhone(randomUserProfile.getPhone());
                        getView().loadDateOfBirth(randomUserProfile.getDob().getDate());
                        getView().loadEmail(randomUserProfile.getEmail());
                        getView().loadGender(randomUserProfile.getGender());
                        getView().loadImageProfile(randomUserProfile.getPicture().getLarge());
                        getView().loadName(randomUserProfile.getName().getFirst());
                        getView().loadSurname(randomUserProfile.getName().getLast());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void viewIsReady() {

    }
}

