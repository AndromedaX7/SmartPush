package com.zhhl.smartpush.di.component;

import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.di.module.ConcernedWithDrugsModule;
import com.zhhl.smartpush.mvp.view.activities.ConcernedWithDrugsActivity;

import dagger.Component;

/**
 * Created by miao on 2019/4/1.
 */

@ActivityScope
@Component(modules = ConcernedWithDrugsModule.class, dependencies = AppComponent.class)
public interface ConcernedWithDrugsComponent {
    void inject(ConcernedWithDrugsActivity activity);
}