package com.bearsacker.adventofcode20.day19;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day19 {

    private final static Pattern REGEX_RULE = Pattern.compile("^([0-9]+): (.*)$");

    private final static Pattern REGEX_SENTENCE = Pattern.compile("^([ab]+)$");

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 19: Monster Messages ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day19.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        HashMap<Integer, Rule> rules = new HashMap<>();

        lines.stream().forEach(x -> {
            Matcher matcher = REGEX_RULE.matcher(x.replace("\"", ""));
            if (matcher.matches()) {
                int number = Integer.parseInt(matcher.group(1));
                String[] values = matcher.group(2).split(" \\| ");

                ArrayList<Integer> left = new ArrayList<>();
                if (values[0].equals("a") || values[0].equals("b")) {
                    rules.put(number, new Rule(values[0]));
                } else {
                    left.addAll(stream(values[0].split(" ")).map(Integer::parseInt).collect(Collectors.toList()));

                    ArrayList<Integer> right = new ArrayList<>();
                    if (values.length > 1) {
                        right.addAll(stream(values[1].split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
                    }

                    rules.put(number, new Rule(left, right));
                }
            }
        });

        List<String> sentences = lines.stream().filter(x -> REGEX_SENTENCE.matcher(x).matches()).collect(Collectors.toList());

        Pattern regex = Pattern.compile(rules.get(0).getRegex(rules, -1, 0));
        System.out.println("Part 1: " + sentences.parallelStream().filter(x -> regex.matcher(x).matches()).count());

        rules.put(8, new Rule(new ArrayList<>(asList(42)), new ArrayList<>(asList(42, 8))));
        rules.put(11, new Rule(new ArrayList<>(asList(42, 31)), new ArrayList<>(asList(42, 11, 31))));

        Pattern regex2 = Pattern.compile(rules.get(0).getRegex(rules, -1, 0));
        System.out.println("Part 2: " + sentences.parallelStream().filter(x -> regex2.matcher(x).matches()).count());
    }

}
