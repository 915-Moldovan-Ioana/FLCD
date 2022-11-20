package com.company.fa;

public class FAConsole {
    private final FA fa;

    public FAConsole(FA fa) {
        this.fa = fa;
        this.fa.parseFile();
    }

    public String showMenu() {
        return "Choose what to display: \n" +
                "\t0. Exit.\n" +
                "\t1. The states.\n" +
                "\t2. The alphabet.\n" +
                "\t3. The transitions.\n" +
                "\t4. The initial state.\n" +
                "\t5. The final states.\n" +
                "\t6. Verify sequence.\n" +
                "Option: ";
    }

    public String dfa(String sequence) {
        return fa.isDFA() ? verify(sequence) : "not DFA!";
    }

    public String verify(String sequence) {
        return fa.verifySequence(sequence) ? "The sequence " + sequence + " is accepted by the FA" : "Te sequence " + sequence + " is not accepted by the FA";
    }

    public String display(int option) {
        switch (option) {
            case 1:
                return fa.getStates().toString();
            case 2:
                return fa.getAlphabet().toString();
            case 3:
                return fa.transitionToString(fa.getTransitions());
            case 4:
                return fa.getInitialState();
            case 5:
                return fa.getFinalStates().toString();
        }
        return null;
    }
}
