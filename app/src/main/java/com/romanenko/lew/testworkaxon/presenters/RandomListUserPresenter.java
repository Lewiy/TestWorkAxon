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

    public RandomListUserPresenter(Context context) {
        this.context = context;
    }

    public void getRandomUserApi() {

        Observable<RandomUserContainer> randomUserContainerFlowable = Repository.getInstance().getRandomUsers(10);

        randomUserContainerFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RandomUserContainer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RandomUserContainer randomUserContainer) {
                        getView().loadListRandomUsers(randomUserContainer.getResults());
                        Repository.getInstance().setRandomUserContainer(randomUserContainer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        // int a = 5;
                    }
                });

       // RandomUserContainer randomUserContainer   = Repository.getInstance().getRandomUsers(10);

    }


    @Override
    public void loadListRandomUsers() {

    }

    @Override
    public void viewIsReady() {
        getRandomUserApi();
    }
}
