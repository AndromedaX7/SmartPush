package com.zhhl.smartpush.net;

import com.zhhl.smartpush.app.App;
import com.zhhl.smartpush.data.ChangQuDiData;
import com.zhhl.smartpush.data.GxclData;
import com.zhhl.smartpush.data.GxrData2;
import com.zhhl.smartpush.data.Trajectory;

import io.reactivex.Observable;

public class ITrajectoryStaticProxy implements ITrajectory {
    private  ITrajectory iTrajectory;
    private ILogUploadImpl logUpload;

    public ITrajectoryStaticProxy(ITrajectory iTrajectory ) {
        this.iTrajectory = iTrajectory;
        this.logUpload = App.app().getLogReport();
    }

    @Override
    public Observable<Trajectory> selfTrajectory(String sfzh, String dateform, String to, String type) {
        Observable<Trajectory> trajectoryObservable = iTrajectory.selfTrajectory(sfzh, dateform, to, type);
        logUpload.log("{\"sfzh\":\""+sfzh+"\"}");
        return trajectoryObservable;
    }

    @Override
    public Observable<ChangQuDiData> frequented(String sfzh, String dateform, String to, String type) {
        logUpload.log("{\"sfzh\":\""+sfzh+"\"}");
        return iTrajectory.frequented(sfzh, dateform, to, type);
    }

    @Override
    public Observable<GxclData> trajectoryGxcl(String idNumber) {
        logUpload.log("{\"sfzh\":\""+idNumber+"\"}");
        return iTrajectory.trajectoryGxcl(idNumber);
    }

    @Override
    public Observable<GxrData2> trajectoryGxr(String idNumber, String dateform, String dateto) {
        logUpload.log("{\"sfzh\":\""+idNumber+"\"}");//见面时拍摄身份证照片、、未见面时场所照片
        return iTrajectory.trajectoryGxr(idNumber, dateform, dateto) ;
    }
}
