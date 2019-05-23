package com.zhhl.smartpush.di.component;


import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.di.module.PersonTrajectoryAnalysisModule;
import com.zhhl.smartpush.mvp.view.activities.PersonTrajectoryAnalysisActivity;

import dagger.Component;


@ActivityScope
@Component(modules = PersonTrajectoryAnalysisModule.class, dependencies = AppComponent.class)
public interface PersonTrajectoryAnalysisComponent {
    void inject(PersonTrajectoryAnalysisActivity activity);
}