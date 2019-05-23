package com.zhhl.smartpush.mvp.view.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhhl.smartpush.utils.DateUtil;
import com.zhhl.smartpush.utils.DialogUtils;
import com.zhhl.smartpush.R;
import com.zhhl.smartpush.adapter.YearStringAdapter;
import com.zhhl.smartpush.adapter.GxclAdapter;
import com.zhhl.smartpush.adapter.GxrAdapter;
import com.zhhl.smartpush.adapter.PersonAnalysisAdapter;
import com.zhhl.smartpush.common.BaseMvpActivity;
import com.zhhl.smartpush.data.GxclData;
import com.zhhl.smartpush.di.component.AppComponent;
import com.zhhl.smartpush.di.component.DaggerPersonTrajectoryAnalysisComponent;
import com.zhhl.smartpush.di.module.PersonTrajectoryAnalysisModule;
import com.zhhl.smartpush.mvp.contacts.PersonTrajectoryAnalysisContract;
import com.zhhl.smartpush.mvp.presenter.PersonTrajectoryAnalysisPresenter;
import com.zhhl.smartpush.activity.GxrTrajectoryActivity;
import com.zhhl.smartpush.data.PersonTrajectory;
import com.zhhl.smartpush.data.Trajectory;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhhl.smartpush.Conts.yearData;


public class PersonTrajectoryAnalysisActivity extends BaseMvpActivity<PersonTrajectoryAnalysisPresenter> implements PersonTrajectoryAnalysisContract.View {
    private boolean self;

    private String sToday;
    private String _3day;
    private String _30day;

    private String start;
    private String end;

    private int date_flag = 1;
    private int gx_flag = 1;
    private int last_data_flag;


    private boolean push;

    @BindView(R.id.mTrajectoryYear)
    TextView mTrajectoryYear;
    @BindView(R.id.mTrajectoryName)
    TextView mTrajectoryName;
    @BindView(R.id.mTrajectoryPush)
    TextView mTrajectoryPush;
    @BindView(R.id.mTrajectoryList)
    ListView mTrajectoryList;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.nameWrapper)
    ImageView nameWrapper;

    private static final int REQUEST_CODE = 100;
    private final GxrAdapter mGxrAdapter = new GxrAdapter(new ArrayList<>());
    private final GxclAdapter mGxclAdapter = new GxclAdapter(new ArrayList<>());

    @Override
    protected void setUpActivityComponent(AppComponent appComponent) {
        DaggerPersonTrajectoryAnalysisComponent
                .builder()
                .appComponent(appComponent)
                .personTrajectoryAnalysisModule(new PersonTrajectoryAnalysisModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        getIntentData();
        setDate();
        initView2();
        start = sToday;
        end = sToday;
        createRequestDialog();
        clearAdapter();

        if (push) {
            start = "2018-01-01";
            end = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis());
        }
        mPresenter.getMyTrajectory(start, end);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_trajectory_analysis;
    }


    @Override
    public void showRequestDialog() {
        if (!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void dismissRequestDialog() {
        dialog.dismiss();
    }

    @Override
    public void addData(ArrayList<PersonTrajectory> data) {
        adapter.add(data);
        mTrajectoryList.setAdapter(adapter);
        dismiss(adapter.getData());
    }

    private void setDate() {
        sToday = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis());
        _3day = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 2L * 24 * 3600 * 1000);
        _30day = DateUtil.format("yyyy-MM-dd", System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000);
    }

    private void createRequestDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载,请稍后....");
        dialog.setCancelable(false);
    }

    private void getIntentData() {
        String idNumber = getIntent().getStringExtra("idNumber");
        self = getIntent().getBooleanExtra("self", true);
        mPresenter.setIdNumber(idNumber);
        push = getIntent().getBooleanExtra("push", false);
    }

    @Override
    public void setTrajectoryName(String xm) {
        mTrajectoryName.setText(xm);
    }

    @Override
    public void clearAdapter() {
        adapter.clear();
        mTrajectoryList.setAdapter(adapter);
    }

    @Override
    public void setGxrAdapter(ArrayList<PersonTrajectory> transitionWrapper) {
        mGxrAdapter.setData(transitionWrapper);
        mTrajectoryList.setAdapter(mGxrAdapter);
        dismiss(mGxrAdapter.getData());
    }

    @Override
    public void setGxclAdapter(GxclData o) {
        mGxclAdapter.setData(new ArrayList<>(o.getQscl()));
        mTrajectoryList.setAdapter(mGxclAdapter);
        dismiss(mGxclAdapter.getData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            start = data.getStringExtra("start");
            end = data.getStringExtra("end");
            invokeGx2(start, end);
            dismiss(adapter.getData());
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED) {
            parseLastFlag();
            Toast.makeText(this, "您取消了自定义时间查询", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.nameWrapper)
    public void nameClick() {
        if (mPresenter.getRenkou() == null) {
            if (mProgress == null) mProgress = DialogUtils.createProgressDialog(this, "正在设置数据,请稍候");
            mProgress.show();
            return;
        }
        showUserData();
    }

    private void dismiss(ArrayList data) {
        noDataIfNeed(data);
    }

    private ProgressDialog mProgress;

    private ProgressDialog dialog;

    private final PersonAnalysisAdapter adapter = new PersonAnalysisAdapter(new ArrayList<>());
    private final YearStringAdapter years = new YearStringAdapter(new ArrayList<>());

    private void parseLastFlag() {
        mTrajectoryYear.setText(yearData[last_data_flag - 1]);
    }

    private void initView2() {
        if (!push) {
            years.setData(new ArrayList<>(Arrays.asList(yearData)));
            mTrajectoryYear.setText(yearData[0]);
        }else {
            mTrajectoryYear.setText("");
        }
        mTrajectoryList.setAdapter(adapter);
        mTrajectoryList.setOnItemClickListener((parent, views, position, id) -> {
            if (gx_flag == 2) {
                int type = mGxrAdapter.getItem(position).getType();
                switch (type) {
                    case 46:
                        personTrajectoryTx(type, mGxrAdapter.getItem(position).getHbtx2());
                        break;
                    case 47:
                        personTrajectoryTx(type, mGxrAdapter.getItem(position).getHctx2());
                        break;
                    case 48:
                        personTrajectoryTx(type, mGxrAdapter.getItem(position).getKctx2());
                        break;
                    case 49:
                        personTrajectoryTx(type, mGxrAdapter.getItem(position).getZstx2());
                        break;
                    case 50:
                        personTrajectoryTx(type, mGxrAdapter.getItem(position).getHclz2());
                        break;
                }
            }
        });
    }

    private void personTrajectoryTx(int type, ArrayList<? extends Parcelable> o) {
        startActivity(
                new Intent(PersonTrajectoryAnalysisActivity.this, GxrTrajectoryActivity.class)
                        .putExtra("type", type)
                        .putExtra("self", false)
                        .putExtra("data", o));
    }


    private void invokeGx2(String start, String end) {
        switch (gx_flag) {
            case 1:
                clearAdapter();
                mPresenter.getDataRangeWx(start, end);
                mPresenter.getMyTrajectory(start, end);
                break;
            case 2:
                clearAdapter();
                mPresenter.getGxrTrajectory2(start, end);
                break;
            case 3:
                clearAdapter();
                mPresenter.getGxclTrajectory();
                break;
            case 6:
                clearAdapter();
                mPresenter.getChangQuDi(start, end);
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    static class ViewHolder {
        @BindView(R.id.mList)
        ListView mList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void showUserData() {
        if (self) {
            if (mPresenter.getRenkou() != null && mPresenter.getRenkou().size() > 0) {
                Trajectory.RenkouBean rk = this.mPresenter.getRenkou().get(0);
                AlertDialog alertDialog = DialogUtils.create(
                        this,
                        R.layout.dialog_user_info,
                        rk.getXm(),
                        rk.getXbdm(),
                        rk.getMzdm(),
                        rk.getXldm(),
                        rk.getGmsfhm(),
                        rk.getDzmc());
                alertDialog.show();
            }
        }
    }

    private void noDataIfNeed(ArrayList data) {
        if (data.size() == 0) {
            nodata.setVisibility(View.VISIBLE);
        } else {
            nodata.setVisibility(View.GONE);
        }
    }
}