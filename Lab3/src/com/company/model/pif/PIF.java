package com.company.model.pif;

import com.company.model.Pair;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    private final List<PIFEntry> pif;

    public PIF() {
        pif = new ArrayList<PIFEntry>();
    }

    public void add(String token, Pair<Integer, Integer> index) {
        pif.add(new PIFEntry(token, index));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PIFEntry pifEntry : pif) {
            stringBuilder.append(pifEntry.getToken()).append(" : ").append(pifEntry.getIndex().toString()).append("\n");
        }
        return "PIF: \n" + stringBuilder;
    }
}
