package com.zhhl.smartpush.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.BaseModel;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.mvp.contacts.SplashContract;
import com.zhhl.smartpush.net.IModelInformation;

import io.reactivex.Observable;

/**
 * Created by miao on 2018/9/7.
 */

public class SplashModel extends BaseModel implements SplashContract.Model {

    private final IModelInformation iModel;

    public SplashModel(Gson gson, OriginApp application, IModelInformation iModel) {
        super(application, gson);
        this.iModel = iModel;
    }


    @Override
    public Observable<Boolean> checkPermission(String code, String requiredId) {
        Log.e("checkPermission: ", requiredId);
        return iModel.checkPermission(code, requiredId);
    }
}

