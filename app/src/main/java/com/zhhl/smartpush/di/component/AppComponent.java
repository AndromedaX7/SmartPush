package com.zhhl.smartpush.di.component;

import com.google.gson.Gson;
import com.zhhl.smartpush.net.IModelInformation;
import com.zhhl.smartpush.net.ITrajectory;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.di.module.AppModule;
import com.zhhl.smartpush.di.module.ClientModule;
import com.zhhl.smartpush.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, NetworkModule.class})

public interface AppComponent {
    OriginApp application();

    Gson gson();

    OkHttpClient client();

    IModelInformation iModelInformation();

    ITrajectory iTrajectory();

}