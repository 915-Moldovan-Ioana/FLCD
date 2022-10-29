package com.company.model;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private final List<List<String>> hashtable;
    private final int size;

    public HashTable(int s) {
        size = s;
        hashtable = new ArrayList<List<String>>();
        for (int i = 0; i < size; i++) {
            hashtable.add(new ArrayList<String>());
        }
    }

    public int hash(String value) {
        return abs(value.hashCode() % size);
    }

    public boolean contains(String value) {
        return hashtable.get(hash(value)).contains(value);
    }

    public Pair<Integer, Integer> at(String value) {
        if (contains(value)) {
            int x = hash(value);
            int y = 0;
            for (String val : hashtable.get(x)) {
                if (!val.equals(value))
                    y++;
                else
                    return new Pair<Integer, Integer>(x, y);
            }
        }
        return new Pair<Integer, Integer>(-1, -1);
    }

    public Pair<Integer, Integer> add(String value) {
        if (!contains(value)) {
            hashtable.get(hash(value)).add(value);
        }
        return at(value);
    }

}