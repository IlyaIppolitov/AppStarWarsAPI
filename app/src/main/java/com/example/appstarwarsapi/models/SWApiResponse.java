package com.example.appstarwarsapi.models;

import java.util.List;

public class SWApiResponse {
    private List<Character> results;

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
