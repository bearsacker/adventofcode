package com.bearsacker.adventofcode20.day01;

import static java.lang.Math.max;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static int find(int wantedSum, List<Integer> numbers, int maxDepth) {
        return find(wantedSum, numbers, maxDepth, 0, new ArrayList<>(), 0);
    }

    public static int find(int wantedSum, List<Integer> numbers, int maxDepth, int startIndex, ArrayList<Integer> previousValues,
            int depth) {
        int result = 0;

        for (int i = startIndex; i < numbers.size() - 1; i++) {
            int number = numbers.get(i);
            int sum = previousValues.stream().reduce(0, (a, b) -> a + b) + number;
            int product = previousValues.stream().reduce(1, (a, b) -> a * b) * number;

            if (sum == wantedSum) {
                return product;
            } else if (depth < maxDepth) {
                ArrayList<Integer> newPreviousValues = new ArrayList<>(previousValues);
                newPreviousValues.add(number);

                result = max(result, find(wantedSum, numbers, maxDepth, i + 1, newPreviousValues, depth + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = Files.lines(new File("2020/day01.txt").toPath()).map(Integer::parseInt).collect(Collectors.toList());

        System.out.println("\n--- Day 1: Report Repair ---");
        System.out.println("Part 1: " + find(2020, numbers, 1));
        System.out.println("Part 2: " + find(2020, numbers, 2));
    }

}
