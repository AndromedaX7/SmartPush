package com.zhhl.smartpush.di.component;

import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.mvp.view.activities.SplashActivity;
import com.zhhl.smartpush.di.module.SplashModule;

import dagger.Component;

/**
 * Created by miao on 2018/9/7.
 */

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}