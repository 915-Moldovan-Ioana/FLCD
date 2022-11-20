package com.company.fa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FA {
    private final File file;
    private List<String> states;
    private List<String> alphabet;
    private Map<FAPair, List<String>> transitions;
    private String initialState;
    private Set<String> finalStates;

    public FA(String filename) {
        this.file = new File(filename);
        this.states = new ArrayList<String>();
        this.alphabet = new ArrayList<String>();
        this.transitions = new HashMap<FAPair, List<String>>();
        this.initialState = null;
        this.finalStates = new HashSet<String>();
    }

    public String transitionToString(Map<FAPair, List<String>> transitions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<FAPair, List<String>> e : transitions.entrySet()) {
            stringBuilder.append(e.getKey().toString()).append(e.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public Map<FAPair, List<String>> getTransitions() {
        return transitions;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }

    public void parseFile() {
        try {
            // 0 - states; 1 - alphabet; 2 - initial state; 3 - final states; 4+ - transitions
            int lineNo = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (lineNo == 0) {
                    // read states
                    if (line == null) {
                        throw new FAException("No data for states");
                    }
                    String[] readStates = line.split(",");
                    states.addAll(Arrays.asList(readStates));
                } else {
                    if (lineNo == 1) {
                        // read alphabet
                        if (line == null) {
                            throw new FAException("No data for alphabet");
                        }
                        String[] readAlphabet = line.split(",");
                        alphabet.addAll(Arrays.asList(readAlphabet));
                    } else {
                        if (lineNo == 2) {
                            // read initial state
                            if (line == null) {
                                throw new FAException("No data for initial state");
                            }
                            if (line.length() != 1) {
                                throw new FAException("Wrong input for the initial state");
                            }
                            initialState = line;
                        } else {
                            if (lineNo == 3) {
                                // read final states
                                if (line == null) {
                                    throw new FAException("No data for final states");
                                }
                                String[] readFinalStates = line.split(",");
                                finalStates.addAll(Arrays.asList(readFinalStates));
                            } else {
                                if (lineNo >= 4) {
                                    // read transitions
                                    String pair = line.split(";")[0];
                                    String out = line.split(";")[1];
                                    FAPair faPair = new FAPair(pair.split(",")[0], pair.split(",")[1]);
                                    if (transitions.containsKey(faPair)) {
                                        transitions.get(faPair).add(out);
                                    } else {
                                        List<String> outList = new ArrayList<String>();
                                        outList.add(out);
                                        transitions.put(faPair, outList);
                                    }
                                }
                            }
                        }
                    }
                }
                lineNo++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isDFA() {
        for (Map.Entry<FAPair, List<String>> e : transitions.entrySet()) {
            List<String> value = e.getValue();
            if (value.size() != 1)
                return false;
        }
        return true;
    }

    public boolean verifySequence(String sequence) {
        return verifySequenceRec(initialState, sequence);
    }

    private boolean verifySequenceRec(String state, String sequence) {
        // if the sequence is empty, check if the fa contains only one state (which has to be the initial and also the final one)
        if (sequence == null || sequence.isEmpty()) {
            return finalStates.contains(state);
        }

        String symbol = String.valueOf(sequence.charAt(0));
        String subsequence = sequence.substring(1);

        FAPair input = new FAPair(state, symbol);
        List<String> output = transitions.get(input);
        // there is no transition with the current state and symbol
        if (output == null) {
            return false;
        }

        for (String outputState : output) {
            if (verifySequenceRec(outputState, subsequence)) {
                return true;
            }
        }
        return false;
    }
}
