package com.company.fa;

public class FAPair {
    private String state;
    private String symbol;

    public FAPair(String s, String sy) {
        state = s;
        symbol = sy;
    }

    @Override
    public String toString() {
        return "(" + state + ", " + symbol + ") = ";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof FAPair)) {
            return false;
        }

        FAPair faPair = (FAPair) obj;
        return faPair.state.equals(this.state) && faPair.symbol.equals(this.symbol);
    }

    @Override
    public int hashCode() {
        return this.state.hashCode();
    }

    public String getState() {
        return state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}