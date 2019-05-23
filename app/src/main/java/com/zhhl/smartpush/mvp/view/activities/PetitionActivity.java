package com.zhhl.smartpush.mvp.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.zhhl.smartpush.R;
import com.zhhl.smartpush.adapter.PushTrajectoryAdapter;
import com.zhhl.smartpush.common.BaseMvpActivity;
import com.zhhl.smartpush.data.PushInfo;
import com.zhhl.smartpush.di.component.AppComponent;
import com.zhhl.smartpush.di.component.DaggerPetitionComponent;
import com.zhhl.smartpush.di.module.PetitionModule;
import com.zhhl.smartpush.mvp.contacts.PetitionContact;
import com.zhhl.smartpush.mvp.presenter.PetitionPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by miao on 2019/2/28.
 */

public class PetitionActivity extends BaseMvpActivity<PetitionPresenter> implements PetitionContact.View {

    @BindView(R.id.mZaitaoRenyuan)
    ListView mPetitionList;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    private ProgressDialog dialog;

    private final PushTrajectoryAdapter mAdapter = new PushTrajectoryAdapter();

    @Override
    protected void initData() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        refreshLayout.setOnRefreshListener(mPresenter::exceptionInformation);
        mPetitionList.setAdapter(mAdapter);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载,请稍后...");
        dialog.setCancelable(false);
        refreshLayout.setRefreshing(true);
        mPresenter.exceptionInformation();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_zaitao;
    }

    @Override
    protected void setUpActivityComponent(AppComponent appComponent) {
        DaggerPetitionComponent
                .builder()
                .appComponent(appComponent)
                .petitionModule(new PetitionModule(this)) //请将PetitionModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnItemClick(R.id.mZaitaoRenyuan)
    void itemClick(int pos) {
        PushInfo.DataBean item = mAdapter.getItem(pos);
        Intent shangfangguiji = new Intent(this, PersonTrajectoryAnalysisActivity.class).putExtra("push", true);
        shangfangguiji.putExtra("idNumber", item.getZtidcard());
        startActivity(shangfangguiji);
    }

    @Override
    public void changeData(List<PushInfo.DataBean> data) {
        mAdapter.changeData(data);
    }

    @Override
    public void refreshOnly() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }
}