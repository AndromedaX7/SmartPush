package com.zhhl.smartpush.net;

import com.zhhl.smartpush.data.Cunguanguiji;
import com.zhhl.smartpush.data.Cunguanrenyuan;
import com.zhhl.smartpush.data.Cunguanzhixi;
import com.zhhl.smartpush.data.PushInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/26.
 */
public interface IModelInformation {

    @Deprecated
    @POST("/jeesite/cunguan/mingdan")
    Observable<Cunguanrenyuan> cunguanmingdan();

    @Deprecated
    @POST("/jeesite/cunguan/zhixi")
    @FormUrlEncoded
    Observable<Cunguanzhixi> cunguanzhixi(@Field("sfzh") String sfzh);

    @Deprecated
    @POST("/jeesite/cunguan/guiji")
    @FormUrlEncoded
    Observable<Cunguanguiji> cunguanguiji(@Field("sfzh") String sfzh);

    @POST(Api.exceptionAtLarge)
    Observable<PushInfo> exceptionAtLarge();

    @POST(Api.exceptionPetition)
    Observable<PushInfo> exceptionPetition();
    @POST("/jeesite/jinghao/yanzheng")
    @FormUrlEncoded
    Observable<Boolean> checkPermission(@Field("jinghao") String jinghao, @Field("dizhi") String dizhi);

    @POST(Api.exceptionConcernedWithDrugs)
    Observable<PushInfo> exceptionConcernedWithDrugs();


}
