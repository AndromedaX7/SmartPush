package com.zhhl.smartpush.mvp.presenter;

import com.zhhl.smartpush.common.BasePresenter;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.AtLargeContact;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miao on 2019/2/28.
 */

@ActivityScope
public class AtLargePresenter extends BasePresenter<AtLargeContact.Model, AtLargeContact.View> {
    private OriginApp mApplication;

    @Inject
    public AtLargePresenter(AtLargeContact.Model model, AtLargeContact.View rootView
            , OriginApp application) {
        super(model, rootView);
        this.mApplication = application;
    }


    public void exceptionInformation() {
        mModel.exceptionAtLarge()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::exceptionAtLarge, this::onError, this::onComplete)
                .isDisposed();
    }

    @Override
    public void onError(Throwable throwable) {
       super.onError(throwable);
        mRootView.refreshOnly();
        mRootView.dismiss();
    }


    private void exceptionAtLarge(PushInfo info) {
        mRootView.changeData(info.getData());
        mRootView.refreshOnly();
        mRootView.dismiss();
    }

}
