package com.neomatrix.appbank.model;

import java.util.List;

public class StatementsResponse {

    private String error;

    private List<Statements> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Statements> getResults() {
        return results;
    }

    public void setResults(List<Statements> results) {
        this.results = results;
    }
}
