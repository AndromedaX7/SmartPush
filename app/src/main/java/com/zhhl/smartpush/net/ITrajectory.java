package com.zhhl.smartpush.net;

import com.zhhl.smartpush.data.ChangQuDiData;
import com.zhhl.smartpush.data.GxclData;
import com.zhhl.smartpush.data.GxrData2;
import com.zhhl.smartpush.data.Trajectory;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by miao on 2018/11/26.
 */
public interface ITrajectory {

    @POST(Api.selfTrajectory)
    @FormUrlEncoded
    Observable<Trajectory> selfTrajectory(@Field("sfzh") String sfzh, @Field("dateform") String dateform, @Field("dateto") String to, @Field("type") String type);

    @POST(Api.frequented)
    @FormUrlEncoded
    Observable<ChangQuDiData> frequented(@Field("sfzh") String sfzh, @Field("dateform") String dateform, @Field("dateto") String to, @Field("type") String type);

    @POST(Api.relationalVehicle)
    @FormUrlEncoded
    Observable<GxclData> trajectoryGxcl(@Field("sfzh") String idNumber);

    @POST(Api.relationalPerson)
    @FormUrlEncoded
    Observable<GxrData2> trajectoryGxr(@Field("sfzh") String idNumber, @Field("dateform") String dateform, @Field("dateto") String dateto);
}
