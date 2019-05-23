package com.zhhl.smartpush.mvp.contacts;

import com.zhhl.smartpush.common.IModel;
import com.zhhl.smartpush.common.IView;
import com.zhhl.smartpush.data.PushInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/2/28.
 */

public interface PetitionContact {
    interface View extends IView {
        void changeData(List<PushInfo.DataBean> data);

        void refreshOnly();

        void dismiss();
    }

    interface Model extends IModel {
        Observable<PushInfo> exceptionPetition();
    }

}