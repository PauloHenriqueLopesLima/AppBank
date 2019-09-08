package com.neomatrix.appbank.model;

import java.util.ArrayList;
import java.util.List;

public class StatementsResponse {

    private String error;

    private ArrayList<Statements> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Statements> getResults() {
        return results;
    }

}
