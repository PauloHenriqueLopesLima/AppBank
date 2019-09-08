package com.neomatrix.appbank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatementsResponse
{
    @SerializedName("statementList")
    @Expose
    private List<StatementList> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;

    public List<StatementList> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}