package com.zhhl.smartpush.di.module;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.PetitionContact;
import com.zhhl.smartpush.mvp.model.PetitionModel;
import com.zhhl.smartpush.net.IModelInformation;
import com.zhhl.smartpush.net.IModelInformationStaticProxy;

import dagger.Module;
import dagger.Provides;


/**
 * Created by miao on 2019/2/28.
 */

@Module
public class PetitionModule {
    private PetitionContact.View view;


    public PetitionModule(PetitionContact.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PetitionContact.View providePetitionView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PetitionContact.Model providePetitionModel(Gson gson, OriginApp application, IModelInformation iModelInformation) {
        return new PetitionModel(gson, application, new IModelInformationStaticProxy(iModelInformation));
    }
}