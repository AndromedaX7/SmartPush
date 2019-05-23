package com.zhhl.smartpush.net;



import com.zhhl.smartpush.data.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/9/27.
 */
public interface Login {
    String path = "/uas/sso/singlesignoncontrol/checkbill.do";

    @FormUrlEncoded
    @POST(path)
    Observable<LoginBean> login(@Field("strBill") String token);

}
