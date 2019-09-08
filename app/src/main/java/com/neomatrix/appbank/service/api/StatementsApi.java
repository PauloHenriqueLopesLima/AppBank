package com.neomatrix.appbank.service.api;

import com.neomatrix.appbank.model.StatementsResponse;


import retrofit2.Call;


import retrofit2.http.GET;


public interface StatementsApi {


    @GET("statements/1")
    Call<StatementsResponse> getStatements();

}
