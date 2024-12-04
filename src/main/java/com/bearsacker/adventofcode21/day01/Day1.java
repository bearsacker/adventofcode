package com.bearsacker.adventofcode21.day01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static int sum(List<Integer> numbers, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers.get(i);
        }

        return sum;
    }

    public static int getSweep(List<Integer> numbers, int length) {
        int n = 0;
        int previous = sum(numbers, 0, length);

        for (int i = length - 1; i < numbers.size(); i++) {
            int current = sum(numbers, i - length + 1, i + 1);
            n += current > previous ? 1 : 0;
            previous = current;
        }

        return n;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = Files.lines(new File("2021/day01.txt").toPath()).map(Integer::parseInt).collect(Collectors.toList());

        System.out.println("\n--- Day 1: Sonar Sweep ---");
        System.out.println("Part 1: " + getSweep(numbers, 1));
        System.out.println("Part 2: " + getSweep(numbers, 3));
    }

}
