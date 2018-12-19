package com.romanenko.lew.testworkaxon.base;

public interface IPresenter<V extends IView> {
    void attachView(V IView);

    void viewIsReady();

    void detachView();
}
