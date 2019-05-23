package com.zhhl.smartpush.mvp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.xdja.uaac.api.TokenCallback;
import com.xdja.uaac.api.UaacApi;
import com.zhhl.smartpush.MainActivity;
import com.zhhl.smartpush.R;
import com.zhhl.smartpush.common.BaseMvpActivity;
import com.zhhl.smartpush.di.component.AppComponent;
import com.zhhl.smartpush.di.component.DaggerSplashComponent;
import com.zhhl.smartpush.di.module.SplashModule;
import com.zhhl.smartpush.mvp.contacts.SplashContract;
import com.zhhl.smartpush.mvp.presenter.SplashPresenter;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;


/**
 * Created by miao on 2018/9/7.
 */

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    //
//    @BindView(R.id.appName)
//    TextView appName;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.information)
    TextView information;
    private boolean finish;
    @BindView(R.id.root)
    ConstraintLayout splashView;
    @BindView(R.id.indicator)
    ProgressBar indicator;

    @Override
    protected void setUpActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this)) //请将SplashModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public void dismissIndicator() {
        indicator.setVisibility(View.INVISIBLE);
        Snackbar.make(splashView, "您的账户没有在系统注册,请与管理员联系", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        description.setText("①定制模型对数据进行专项分析\n" +
//                "②智能推送出重点人员异常轨迹信息");
//
//        information.setText("如需开通权限请联系系统管理员\n" +
//                "吉林省智慧互联信息科技有限公司\n" +
//                "技术联系电话18043134285");
        UaacApi.getToken(this, new TokenCallback() {
            @Override
            public void onSuccess(String token, boolean b) {
                if (b)
                    mPresenter.login(token);
                else
                    new Handler(Looper.getMainLooper()).postDelayed(() -> mPresenter.login(token), 5000);
            }

            @Override
            public void onError(String s) {
                if (s.equals("票据不存在")) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> UaacApi.getToken(SplashActivity.this, this), 2000);
                }
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public Context getContext() {
        return this;
    }
}