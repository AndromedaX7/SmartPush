package com.zhhl.smartpush.mvp.model;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.BaseModel;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.mvp.contacts.AtLargeContact;
import com.zhhl.smartpush.net.IModelInformation;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/2/28.
 */

public class AtLargeModel extends BaseModel implements AtLargeContact.Model {

    private final IModelInformation iModelInformation;

    public AtLargeModel(Gson gson, OriginApp application, IModelInformation iModelInformation) {
        super(application, gson);
        this.iModelInformation = iModelInformation;
    }


    @Override
    public Observable<PushInfo> exceptionAtLarge() {
        return iModelInformation.exceptionAtLarge();
    }
}

