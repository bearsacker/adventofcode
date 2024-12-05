package com.bearsacker.adventofcode24.day05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    public static int sumMiddleValidUpdates(ArrayList<Rule> rules, ArrayList<Update> updates) {
        return updates.stream().filter(x -> x.isValid(rules)).map(Update::middlePage).reduce(0, (a, b) -> a + b);
    }

    public static int sumMiddleCorrectedUpdates(ArrayList<Rule> rules, ArrayList<Update> updates) {
        return updates.stream().filter(x -> !x.isValid(rules)).map(x -> x.correct(rules)).map(Update::middlePage).reduce(0,
                (a, b) -> a + b);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(new File("2024/day05.txt").toPath()).collect(Collectors.toList());

        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<Update> updates = new ArrayList<>();

        boolean parseRules = true;
        for (String line : lines) {
            if (line.isBlank()) {
                parseRules = false;
            } else {
                if (parseRules) {
                    rules.add(new Rule(line));
                } else {
                    updates.add(new Update(line));
                }
            }
        }

        System.out.println("\n--- Day 5: Print Queue ---");
        System.out.println("Part 1: " + sumMiddleValidUpdates(rules, updates));
        System.out.println("Part 2: " + sumMiddleCorrectedUpdates(rules, updates));
    }

}
