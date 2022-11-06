package com.company.model.pif;

import com.company.model.Pair;

public class PIFEntry {
    private String token;
    private Pair<Integer, Integer> index;

    public PIFEntry(String token, Pair<Integer, Integer> index) {
        this.token = token;
        this.index = index;
    }

    public String getToken() {
        return token;
    }

    public Pair<Integer, Integer> getIndex() {
        return index;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIndex(Pair<Integer, Integer> index) {
        this.index = index;
    }
}
