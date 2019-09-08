package com.neomatrix.appbank.service.api;

import com.neomatrix.appbank.model.StatementList;

import retrofit2.Call;


import retrofit2.http.GET;


public interface StatementsApi {


    @GET("statements/1")
    Call<StatementList> getStatements();


}
