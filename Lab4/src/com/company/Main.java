package com.company;

import com.company.fa.FA;
import com.company.fa.FAConsole;
import com.company.model.scanner.MyScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("fa or scanner?");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        if (answer.equals("fa")) {
            FA fa = new FA("E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\com\\company\\fa\\fa.in");
            FAConsole faConsole = new FAConsole(fa);
            int option = -1;
            while (option != 0) {
                System.out.println(faConsole.showMenu());
                option = in.nextInt();
                if (option == 6) {
                    System.out.println("Sequence: ");
                    Scanner inn = new Scanner(System.in);
                    String sequence = inn.nextLine();
                    System.out.println(faConsole.dfa(sequence) + "\n");
                } else {
                    System.out.println(faConsole.display(option) + "\n");
                }
            }
        } else {
            if (answer.equals("scanner")) {
                MyScanner myScanner = new MyScanner("E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\p2.txt", "E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\token.in",10);

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
    }
}
