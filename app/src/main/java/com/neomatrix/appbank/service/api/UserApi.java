package com.neomatrix.appbank.service.api;


import com.neomatrix.appbank.model.UserAccount;
import com.neomatrix.appbank.model.UserAccountResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {

    @FormUrlEncoded
    @Headers( {"content-type: application/x-www-form-urlencoded;charset=UTF-8", })
    @POST("login")
    Call<UserAccountResponse> getUser(
            @Field("user")  String user,
            @Field("password") String password);

}
