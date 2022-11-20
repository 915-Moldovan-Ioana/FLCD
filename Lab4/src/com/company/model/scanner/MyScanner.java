package com.company.model.scanner;

import com.company.LexicalError;
import com.company.fa.FA;
import com.company.model.HashTable;
import com.company.model.Pair;
import com.company.model.pif.PIF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyScanner {
    private PIF pif;
    private HashTable symbolTable;
    private File file;
    private File tokenFile;
    private List<String> tokens;
    private List<ScannerEntry> words;
    private FA faIdentifier;
    private FA faInteger;

    public MyScanner(String filename, String tokenFileName, int size) {
        pif = new PIF();
        symbolTable = new HashTable(size);
        file = new File(filename);
        tokenFile = new File(tokenFileName);
        words = new ArrayList<ScannerEntry>();
        tokens = new ArrayList<String>();
        faIdentifier = new FA("E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\com\\company\\model\\scanner\\fa_identifier.in");
        faIdentifier.parseFile();
        faInteger = new FA("E:\\ioana\\3rd year\\Sem1\\FLCD\\Labs\\Lab2\\src\\com\\company\\model\\scanner\\fa_integer.in");
        faInteger.parseFile();
    }

    public PIF getPif() {
        return pif;
    }

    public HashTable getSymbolTable() {
        return symbolTable;
    }

    public List<ScannerEntry> getWords() {
        return words;
    }

    public void parse() {
        read();
        readTokens();
        StringBuilder errors = new StringBuilder();
        for (ScannerEntry laEntry : getWords()) {
            if (parseKeywords(laEntry.getToken()) != null) {
                getPif().add(laEntry.getToken(), parseKeywords(laEntry.getToken()));
            } else {
                if (parseIdentifiers(laEntry.getToken()) != null)
                    getPif().add("id", parseIdentifiers(laEntry.getToken()));
                else {
                    if (parseConstants(laEntry.getToken()) != null)
                        getPif().add("const", parseConstants(laEntry.getToken()));
                    else {
                        errors.append("lexical error: token '").append(laEntry.getToken()).append("' cannot be classified (line ").append(laEntry.getLine()).append(") \n");
                    }
                }
            }
        }
        if (!errors.toString().isEmpty()) {
            throw new LexicalError(errors.toString());
        }
    }

    public void read() {
        try {
            int lineNo = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.split("\\s+");
                for (String split : splitted) {
                    if (split != "") {
                        String c = split.substring(split.length() - 1);
                        if (c.equals(";") && split.length() != 1) {
                            split = split.substring(0, split.length() - 1);
                            words.add(new ScannerEntry(split, lineNo));
                            words.add(new ScannerEntry(";", lineNo));
                        } else
                            words.add(new ScannerEntry(split, lineNo));
                    }
                }
                lineNo++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void readTokens() {
        try {
            Scanner scanner = new Scanner(tokenFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tokens.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Pair<Integer, Integer> parseKeywords(String word) {
        if (tokens.contains(word)) {
            return new Pair<Integer, Integer>(-1, -1);
        }
        return null;
    }

    public Pair<Integer, Integer> parseIdentifiers(String identifier) {
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*$");
        Matcher matcher = pattern.matcher(identifier);
//        boolean matchFound = matcher.find();
        boolean matchFound = faIdentifier.verifySequence(identifier);
        if (matchFound) {
            return symbolTable.add(identifier);
        }
        return null;
    }

    public Pair<Integer, Integer> parseConstants(String identifier) {
        if (isCharacter(identifier) || isBoolean(identifier) || isInteger(identifier) || isString(identifier)) {
            return symbolTable.add(identifier);
        }
        return null;
    }

    public boolean isCharacter(String identifier) {
        Pattern pattern = Pattern.compile("\'[a-zA-Z0-9]\'");
        Matcher matcher = pattern.matcher(identifier);
        return matcher.find();
    }

    public boolean isBoolean(String identifier) {
        return identifier.equals("true") || identifier.equals("false");
    }

    public boolean isInteger(String identifier) {
        return faInteger.verifySequence(identifier);
//        Pattern pattern = Pattern.compile("^(0|[+-]?[1-9][0-9]*)$");
//        Matcher matcher = pattern.matcher(identifier);
//        return matcher.find();
    }

    public boolean isString(String identifier) {
        Pattern pattern = Pattern.compile("\"[^\"]*\"");
        Matcher matcher = pattern.matcher(identifier);
        return matcher.find();
    }
}