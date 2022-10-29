package com.company;

import com.company.model.HashTable;

public class Main {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        System.out.println("1".hashCode());
        System.out.println("E".hashCode());
        System.out.println(hashTable.add("1"));
        System.out.println(hashTable.add("E"));
    }
}
