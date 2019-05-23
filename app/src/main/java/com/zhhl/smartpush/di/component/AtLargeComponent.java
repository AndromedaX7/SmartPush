package com.zhhl.smartpush.di.component;

import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.di.module.AtLargeModule;
import com.zhhl.smartpush.mvp.view.activities.AtLargeActivity;

import dagger.Component;

/**
 * Created by miao on 2019/2/28.
 */

@ActivityScope
@Component(modules = AtLargeModule.class, dependencies = AppComponent.class)
public interface AtLargeComponent {
    void inject(AtLargeActivity activity);
}