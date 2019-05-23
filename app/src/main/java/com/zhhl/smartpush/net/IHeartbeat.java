package com.zhhl.smartpush.net;

import com.zhhl.smartpush.data.PushInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IHeartbeat {
    @POST("/jeesite/zaitaoxxts/ztmxyctsb/getList")
    @FormUrlEncoded
    Observable<PushInfo> heartbeat(@Field("jh") String code);
}
