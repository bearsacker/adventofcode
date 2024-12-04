package com.bearsacker.adventofcode20.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day18 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 18: Operation Order ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day18.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        System.out.println("Part 1: " + lines.stream().map(x -> new Expression(x).compute(false)).reduce(0L, (a, b) -> a + b));
        System.out.println("Part 2: " + lines.stream().map(x -> new Expression(x).compute(true)).reduce(0L, (a, b) -> a + b));
    }

}
