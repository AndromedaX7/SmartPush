package com.zhhl.smartpush.mvp.presenter;

import com.zhhl.smartpush.common.BasePresenter;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.ConcernedWithDrugsContact;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miao on 2019/4/1.
 */

@ActivityScope
public class ConcernedWithDrugsPresenter extends BasePresenter<ConcernedWithDrugsContact.Model, ConcernedWithDrugsContact.View> {
    private OriginApp mApplication;

    @Inject
    public ConcernedWithDrugsPresenter(ConcernedWithDrugsContact.Model model, ConcernedWithDrugsContact.View rootView
            , OriginApp application) {
        super(model, rootView);
        this.mApplication = application;
    }
    public void exceptionInformation() {
        mModel.exceptionConcernedWithDrugs()
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
