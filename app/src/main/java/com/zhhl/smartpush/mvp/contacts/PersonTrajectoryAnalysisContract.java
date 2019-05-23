package com.zhhl.smartpush.mvp.contacts;

import com.zhhl.smartpush.common.IModel;
import com.zhhl.smartpush.common.IView;
import com.zhhl.smartpush.data.GxclData;
import com.zhhl.smartpush.data.GxrData2;
import com.zhhl.smartpush.data.ChangQuDiData;
import com.zhhl.smartpush.data.HunyinGuiji;
import com.zhhl.smartpush.data.PersonTrajectory;
import com.zhhl.smartpush.data.Trajectory;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/1/17.
 */

public interface PersonTrajectoryAnalysisContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void showRequestDialog();

        void dismissRequestDialog();

        void addData(ArrayList<PersonTrajectory> data);

        void setTrajectoryName(String xm);

        void clearAdapter();

        void setGxrAdapter(ArrayList<PersonTrajectory> transitionWrapper);

        void setGxclAdapter(GxclData o);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {

        Observable<Trajectory> selfTrajectory(String idNumber, String start, String end, String value);

        Observable<ChangQuDiData> frequented(String idNumber, String start, String end, String value);

        Observable<GxclData> trajectoryGxcl(String idNumber);

        Observable<GxrData2> trajectoryGxr(String idNumber, String start, String end);
    }

}