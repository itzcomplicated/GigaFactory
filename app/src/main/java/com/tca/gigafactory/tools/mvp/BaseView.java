package com.tca.gigafactory.tools.mvp;

/**
 * Created by TCA on 15-04-2017.
 */


public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

}