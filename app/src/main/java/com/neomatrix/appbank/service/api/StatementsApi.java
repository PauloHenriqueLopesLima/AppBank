package com.neomatrix.appbank.service.api;

import com.neomatrix.appbank.model.Statements;
import com.neomatrix.appbank.model.StatementsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;

import retrofit2.http.GET;


import retrofit2.http.Path;

public interface StatementsApi {


    @GET("statements/{userId}")
    Call<List<Statements>> getStatements(
            @Field("userId") String userId);
    Call<List<StatementsResponse>> listStatements(@Path("userId") String userId);
}
