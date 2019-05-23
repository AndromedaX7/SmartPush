package com.zhhl.smartpush.di.module;

import com.google.gson.Gson;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.SplashContract;
import com.zhhl.smartpush.mvp.model.SplashModel;
import com.zhhl.smartpush.net.IModelInformation;

import dagger.Module;
import dagger.Provides;


/**
 * Created by miao on 2018/9/7.
 */

@Module
public class SplashModule {
    private SplashContract.View view;

    /**
     * 构建SplashModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SplashContract.View provideSplashView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SplashContract.Model provideSplashModel(Gson gson, OriginApp application, IModelInformation iModel) {
        return new SplashModel(gson, application,iModel);
    }
}