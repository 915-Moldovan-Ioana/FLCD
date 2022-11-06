package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class SortedList {
    private List<String> list;

    public SortedList() {
        list = new ArrayList<String>();
    }

    public int search(String elem) {
        for (int i = 0; i < list.size(); i++) {
            if (elem.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public int add(String elem) {
        if (list.isEmpty()) {
            list.add(elem);
            return 0;
        } else {
            if (list.size() == 1) {
                if (elem.compareTo(list.get(0)) < 0) {
                    list.add(0, elem);
                    return 0;
                } else {
                    list.add(1, elem);
                    return 1;
                }
            }
        }
        if (search(elem) == -1) {
            for (int i = 1; i < list.size(); i++) {
                if (elem.compareTo(list.get(i - 1)) > 0 && elem.compareTo(list.get(i)) < 0) {
                    list.add(i, elem);
                    return i;
                }
            }
        }
        return search(elem);
    }

}
