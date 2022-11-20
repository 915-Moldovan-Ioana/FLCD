package com.company.model.scanner;

public class ScannerEntry {
    private String token;
    private int line;

    public ScannerEntry(String token, int line) {
        this.token = token;
        this.line = line;
    }

    public String getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }
}