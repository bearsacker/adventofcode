package com.bearsacker.adventofcode24.day01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {

    public static int distance(List<Integer> left, List<Integer> right) {
        Collections.sort(left);
        Collections.sort(right);

        int distance = 0;

        for (int i = 0; i < left.size(); i++) {
            distance += Math.abs(left.get(i) - right.get(i));
        }

        return distance;
    }

    public static long countOccurences(List<Integer> list, int n) {
        return list.parallelStream().filter(x -> x == n).count();
    }

    public static int scoring(List<Integer> left, List<Integer> right) {
        int score = 0;

        for (int i = 0; i < left.size(); i++) {
            score += countOccurences(right, left.get(i)) * left.get(i);
        }

        return score;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        Files.lines(new File("2024/day01.txt").toPath()).forEach(x -> {
            String[] numbers = x.split("   ");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        });


        System.out.println("\n--- Day 1: Historian Hysteria ---");
        System.out.println("Part 1: " + distance(left, right));
        System.out.println("Part 2: " + scoring(left, right));
    }

}
