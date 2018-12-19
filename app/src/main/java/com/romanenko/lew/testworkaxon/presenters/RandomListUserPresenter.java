package com.romanenko.lew.testworkaxon.presenters;

import android.content.Context;

import com.romanenko.lew.testworkaxon.base.BasePresenter;
import com.romanenko.lew.testworkaxon.base.RandomListUserContract;
import com.romanenko.lew.testworkaxon.model.Repository;
import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RandomListUserPresenter extends BasePresenter<RandomListUserContract.ViewListRandomUsers> implements RandomListUserContract.PresenterListRandomUsers {


    private Context context;
    private static final int NUMBERLOADEDUSERS = 10;

    public RandomListUserPresenter(Context context) {
        this.context = context;
    }

    public void getRandomUsers(int number) {

        Observable<RandomUserContainer> randomUserContainerFlowable = Repository.getInstance().getRandomUsers(number);

        randomUserContainerFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value ->  {getView().loadListRandomUsers(value.getResults());
                        Repository.getInstance().setRandomUserContainer(value.getResults());},
                        e -> getView().showError(e.getMessage().toString())
    );

    }

    @Override
    public void loadListRandomUsers() {

    }

    @Override
    public void viewIsReady() {
        getRandomUsers(NUMBERLOADEDUSERS);
    }
}
