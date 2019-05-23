package com.zhhl.smartpush.mvp.model;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.BaseModel;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.mvp.contacts.PetitionContact;
import com.zhhl.smartpush.net.IModelInformation;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/2/28.
 */

public class PetitionModel extends BaseModel implements PetitionContact.Model {

    private IModelInformation iModelInformation;

    public PetitionModel(Gson gson, OriginApp application, IModelInformation iModelInformation) {
        super(application, gson);
        this.iModelInformation = iModelInformation;
    }

    @Override
    public Observable<PushInfo> exceptionPetition() {
        return iModelInformation.exceptionPetition();
    }

}

