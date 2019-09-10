package com.neomatrix.appbank.service.api;

import com.neomatrix.appbank.model.StatementsResponse;


import retrofit2.Call;


import retrofit2.http.GET;
import retrofit2.http.Path;


public interface StatementsApi {


    @GET("statements/{userId}")
    Call<StatementsResponse> getStatements(@Path("userId") int userId);

}
