package com.zhhl.smartpush.mvp.model;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.BaseModel;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.mvp.contacts.ConcernedWithDrugsContact;
import com.zhhl.smartpush.net.IModelInformation;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/4/1.
 */

public class ConcernedWithDrugsModel extends BaseModel implements ConcernedWithDrugsContact.Model {

    private final IModelInformation iModelInformation;

    public ConcernedWithDrugsModel(Gson gson, OriginApp application, IModelInformation iModelInformation) {
        super(application, gson);
        this.iModelInformation = iModelInformation;
    }

    @Override
    public Observable<PushInfo> exceptionConcernedWithDrugs() {
        return iModelInformation.exceptionConcernedWithDrugs();
    }
}

