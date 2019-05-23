package com.zhhl.smartpush.di.component;

import com.zhhl.smartpush.di.ActivityScope;
import com.zhhl.smartpush.di.module.PetitionModule;
import com.zhhl.smartpush.mvp.view.activities.PetitionActivity;

import dagger.Component;

/**
 * Created by miao on 2019/2/28.
 */

@ActivityScope
@Component(modules = PetitionModule.class, dependencies = AppComponent.class)
public interface PetitionComponent {
    void inject(PetitionActivity activity);
}