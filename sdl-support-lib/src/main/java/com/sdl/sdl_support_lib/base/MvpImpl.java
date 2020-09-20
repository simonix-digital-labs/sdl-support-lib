package com.sdl.sdl_support_lib.base;

public abstract class MvpImpl<T extends CarlsMvp.BaseView> implements CarlsMvp.BasePresenter<T> {

    /**
     * The View linked to this Presenter
     */
    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}