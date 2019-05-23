package com.zhhl.smartpush.di.module;

import com.google.gson.Gson;
import com.zhhl.smartpush.app.App;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.AtLargeContact;
import com.zhhl.smartpush.mvp.model.AtLargeModel;
import com.zhhl.smartpush.net.IModelInformation;
import com.zhhl.smartpush.net.IModelInformationStaticProxy;

import dagger.Module;
import dagger.Provides;


/**
 * Created by miao on 2019/2/28.
 */

@Module
public class AtLargeModule {
    private AtLargeContact.View view;


    public AtLargeModule(AtLargeContact.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AtLargeContact.View provideAtLargeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AtLargeContact.Model provideAtLargeModel(Gson gson, OriginApp application, IModelInformation iModelInformation) {
        return new AtLargeModel(gson, application,new IModelInformationStaticProxy(iModelInformation));
    }
}