package com.zhhl.smartpush.di.module;

import com.google.gson.Gson;
import com.zhhl.smartpush.net.ITrajectory;
import com.zhhl.smartpush.common.OriginApp;
import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.contacts.PersonTrajectoryAnalysisContract;
import com.zhhl.smartpush.mvp.model.PersonTrajectoryAnalysisModel;
import com.zhhl.smartpush.net.ITrajectoryStaticProxy;

import dagger.Module;
import dagger.Provides;


@Module
public class PersonTrajectoryAnalysisModule {
    private final PersonTrajectoryAnalysisContract.View view;

    /**
     * 构建PersonTrajectoryAnalysisModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view contract
     */
    public PersonTrajectoryAnalysisModule(PersonTrajectoryAnalysisContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PersonTrajectoryAnalysisContract.View providePersonTrajectoryAnalysisView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PersonTrajectoryAnalysisContract.Model providePersonTrajectoryAnalysisModel(Gson gson, OriginApp application, ITrajectory iMd) {
        return new PersonTrajectoryAnalysisModel(application, gson, new ITrajectoryStaticProxy(iMd));
    }
}