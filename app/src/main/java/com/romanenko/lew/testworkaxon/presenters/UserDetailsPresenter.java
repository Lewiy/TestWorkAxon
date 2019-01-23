package com.romanenko.lew.testworkaxon.presenters;

import android.content.Context;

import com.romanenko.lew.testworkaxon.App;
import com.romanenko.lew.testworkaxon.base.BasePresenter;
import com.romanenko.lew.testworkaxon.base.RandomUserProfileContract;
import com.romanenko.lew.testworkaxon.model.UserRepository;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserDetailsPresenter extends BasePresenter<RandomUserProfileContract.ViewListRandomUsers> implements RandomUserProfileContract.PresenterListRandomUsers {


    private Context context;
    public UserRepository userRepository;

    public UserDetailsPresenter(Context context) {
        this.context = context;
        /*DaggerRandomUserComponentPresent
                .builder()
                .contextModule*/
        userRepository = App.get(context).getRepositoryComponent().getUserRepository();
    }

    @Override
    public void loadRandomUserProfile(String email) {

      //  Observable<Result> randomUserProfile = UserRepository.getInstance().getRandomUser(email);
        Observable<Result> randomUserProfile =  userRepository.getRandomUser(email);

        Disposable disposable = randomUserProfile
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value ->  { getView().loadCallPhone(value.getPhone());
                            getView().loadDateOfBirth(parseDate(value.getDob().getDate()));
                            getView().loadEmail(value.getEmail());
                            getView().loadGender(value.getGender());
                            getView().loadImageProfile(value.getPicture().getLarge());
                            getView().loadName(value.getName().getFirst());
                            getView().loadSurname(value.getName().getLast());},
                        e -> getView().showError(e.getMessage().toString())
                );

    }

    @Override
    public void viewIsReady() {

    }

    private String parseDate(String dateInput) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:dd'Z'");
        SimpleDateFormat formatter;
        String formattedDate = null;
        Date date = null;
        try {
            date = parser.parse(dateInput);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            formattedDate = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            getView().showError(e.getMessage().toString());
        }

        return formattedDate;
    }
}

