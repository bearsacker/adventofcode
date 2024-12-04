package com.bearsacker.adventofcode24.day03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {

    private final static Pattern PATTERN = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)|do\\(\\)|don't\\(\\)");

    public static int mul(String program, boolean conditioned) {
        boolean doing = true;
        int result = 0;
        Matcher matcher = PATTERN.matcher(program);
        while (matcher.find()) {
            if (matcher.group(0).contains("mul")) {
                if (!conditioned || doing) {
                    result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                }
            } else if (matcher.group(0).contains("do()")) {
                doing = true;
            } else {
                doing = false;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        String program = Files.lines(new File("2024/day03.txt").toPath()).collect(Collectors.joining());


        System.out.println("\n--- Day 3: Mull It Over ---");
        System.out.println("Part 1: " + mul(program, false));
        System.out.println("Part 2: " + mul(program, true));
    }

}
