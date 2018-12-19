package com.romanenko.lew.testworkaxon.base;

import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

public interface RandomUserProfileContract {
    interface ViewListRandomUsers extends IView {
        void loadRandomUserProfile(Result item);
        void loadImageProfile(String url);
        void loadName(String name);
        void loadSurname(String surName);
        void loadGender(String gender);
        void loadDateOfBirth(String dayOfBirth);
        void loadCallPhone(String cellPhone);
        void loadEmail(String email);
    }

    interface PresenterListRandomUsers extends IPresenter<ViewListRandomUsers> {
       void loadRandomUserProfile(int userId);
    }
}
