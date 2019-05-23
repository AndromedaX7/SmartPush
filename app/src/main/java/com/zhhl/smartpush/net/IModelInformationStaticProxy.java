package com.zhhl.smartpush.net;

import com.zhhl.smartpush.app.App;
import com.zhhl.smartpush.data.Cunguanguiji;
import com.zhhl.smartpush.data.Cunguanrenyuan;
import com.zhhl.smartpush.data.Cunguanzhixi;
import com.zhhl.smartpush.data.PushInfo;

import io.reactivex.Observable;

public class IModelInformationStaticProxy implements IModelInformation {

    private IModelInformation information;


    private ILogUploadImpl logUpload;

    public IModelInformationStaticProxy(IModelInformation iModelInformation) {
        this.information = iModelInformation;
        this.logUpload = App.app().getLogReport();


    }

    @Override
    public Observable<Cunguanrenyuan> cunguanmingdan() {
        return null;
    }

    @Override
    public Observable<Cunguanzhixi> cunguanzhixi(String sfzh) {
        return null;
    }

    @Override
    public Observable<Cunguanguiji> cunguanguiji(String sfzh) {
        return null;
    }

    @Override
    public Observable<PushInfo> exceptionAtLarge() {
        Observable<PushInfo> pushInfoObservable = information.exceptionAtLarge();
        logUpload.log("{}");
        return pushInfoObservable;
    }

    @Override
    public Observable<PushInfo> exceptionPetition() {
        Observable<PushInfo> pushInfoObservable = information.exceptionPetition();
        logUpload.log("{}");
        return pushInfoObservable;
    }

    @Override
    public Observable<Boolean> checkPermission(String jinghao, String dizhi) {
        return information.checkPermission(jinghao,dizhi);
    }

    @Override
    public Observable<PushInfo> exceptionConcernedWithDrugs() {
        return information.exceptionConcernedWithDrugs();
    }
}
