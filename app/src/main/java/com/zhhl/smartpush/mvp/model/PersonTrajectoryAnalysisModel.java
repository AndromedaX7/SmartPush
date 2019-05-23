package com.zhhl.smartpush.mvp.model;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.BaseModel;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.ChangQuDiData;
import com.zhhl.smartpush.data.GxclData;
import com.zhhl.smartpush.data.GxrData2;
import com.zhhl.smartpush.data.Trajectory;
import com.zhhl.smartpush.mvp.contacts.PersonTrajectoryAnalysisContract;
import com.zhhl.smartpush.net.ITrajectory;

import io.reactivex.Observable;

public class PersonTrajectoryAnalysisModel extends BaseModel implements PersonTrajectoryAnalysisContract.Model {

    private final ITrajectory iTrajectory;

    public PersonTrajectoryAnalysisModel(OriginApp application, Gson gson, ITrajectory iMd) {
        super(application, gson);
        this.iTrajectory = iMd;
    }


    @Override
    public Observable<Trajectory> selfTrajectory(String idNumber, String start, String end, String value) {
        return iTrajectory.selfTrajectory(idNumber, start, end, value);
    }

    @Override
    public Observable<ChangQuDiData> frequented(String idNumber, String start, String end, String value) {
        return iTrajectory.frequented(idNumber, start, end, value);
    }

    @Override
    public Observable<GxclData> trajectoryGxcl(String idNumber) {
        return iTrajectory.trajectoryGxcl(idNumber);
    }

    @Override
    public Observable<GxrData2> trajectoryGxr(String idNumber, String start, String end) {
        return iTrajectory.trajectoryGxr(idNumber, start, end);
    }
}

