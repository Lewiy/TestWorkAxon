package com.romanenko.lew.testworkaxon.base;

import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;

import java.util.List;

public interface RandomListUserContract {

    interface ViewListRandomUsers extends IView {
        void loadListRandomUsers(List<Result> items);
    }

    interface PresenterListRandomUsers extends IPresenter<ViewListRandomUsers> {
        void loadListRandomUsers();
    }
}
