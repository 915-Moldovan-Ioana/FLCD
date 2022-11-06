package com.company;

import com.company.model.scanner.MyScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        MyScanner myScanner = new MyScanner("E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\p3.txt", "E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\token.in",10);

        try {
            myScanner.parse();

            System.out.println("lexically correct");

            PrintStream pif = new PrintStream(new File("PIF.out"));
            System.setOut(pif);
            System.out.println(myScanner.getPif().toString());

            PrintStream st = new PrintStream(new File("ST.out"));
            System.setOut(st);
            System.out.println(myScanner.getSymbolTable().toString());
        } catch (LexicalError le){
            System.out.println(le.getMessage());
        }
    }
}
