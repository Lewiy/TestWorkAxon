package com.romanenko.lew.testworkaxon.presenters;

import android.content.Context;

import com.romanenko.lew.testworkaxon.App;
import com.romanenko.lew.testworkaxon.base.BasePresenter;
import com.romanenko.lew.testworkaxon.base.RandomListUserContract;
import com.romanenko.lew.testworkaxon.model.UserRepository;
import com.romanenko.lew.testworkaxon.model.requestPOJO.RandomUserContainer;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RandomListUserPresenter extends BasePresenter<RandomListUserContract.ViewListRandomUsers> implements RandomListUserContract.PresenterListRandomUsers {


    private Context context;
    private static final int NUMBERLOADEDUSERS = 10;

    public UserRepository userRepository;

    public RandomListUserPresenter(Context context) {
        this.context = context;
        userRepository = App.get(context).getRepositoryComponent().getUserRepository();

    }

    public void getRandomUsers(int number) {

        Observable<List<Result>> randomUserContainerFlowable = userRepository.getRandomUsers(number);

        randomUserContainerFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value ->  {getView().loadListRandomUsers(value);
                            //userRepository.setRandomUserContainer(value.getResults());
                            },
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
