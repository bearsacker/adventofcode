package com.bearsacker.adventofcode20.day02;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    public final static Pattern REGEX = Pattern.compile("^([0-9]+)-([0-9]+) ([a-z]{1}): ([a-z]+)$");

    public static void main(String[] args) throws IOException {
        int countValidPasswordFirstPolicy = 0;
        int countValidPasswordSecondPolicy = 0;
        BufferedReader reader = new BufferedReader(new FileReader("2020/day02.txt"));

        String line = reader.readLine();
        do {
            Matcher matcher = REGEX.matcher(line);
            if (matcher.matches()) {
                String password = matcher.group(4);
                FirstPolicy policy = new FirstPolicy(matcher.group(3).charAt(0), parseInt(matcher.group(1)), parseInt(matcher.group(2)));
                SecondPolicy newPolicy =
                        new SecondPolicy(matcher.group(3).charAt(0), parseInt(matcher.group(1)), parseInt(matcher.group(2)));

                countValidPasswordFirstPolicy += policy.checkPassword(password) ? 1 : 0;
                countValidPasswordSecondPolicy += newPolicy.checkPassword(password) ? 1 : 0;
            } else {
                System.err.println(line);
            }

            line = reader.readLine();
        } while (line != null);

        System.out.println("\n--- Day 2: Password Philosophy ---");
        System.out.println("Part 1: " + countValidPasswordFirstPolicy);
        System.out.println("Part 2: " + countValidPasswordSecondPolicy);

        reader.close();
    }

}
