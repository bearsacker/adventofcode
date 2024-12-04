package com.bearsacker.adventofcode21.day02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

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
        List<String> inputs = Files.lines(new File("2021/day02.txt").toPath()).collect(Collectors.toList());

        System.out.println("\n--- Day 2: Dive! ---");
        Submarine submarine = new Submarine();
        inputs.forEach(x -> submarine.applyInstruction(x));
        System.out.println("Part 1: " + submarine.getPositionProduct());

        submarine.init();
        inputs.forEach(x -> submarine.applyInstructionWithAim(x));
        System.out.println("Part 2: " + submarine.getPositionProduct());
    }

}
