package com.zhhl.smartpush.mvp.contacts;

import com.zhhl.smartpush.common.IModel;
import com.zhhl.smartpush.common.IView;
import com.zhhl.smartpush.data.PushInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by miao on 2019/4/1.
 */

public interface ConcernedWithDrugsContact {
    interface View extends IView {

        void refreshOnly();

        void dismiss();

        void changeData(List<PushInfo.DataBean> data);
    }

    interface Model extends IModel {
        Observable<PushInfo> exceptionConcernedWithDrugs();
    }

}