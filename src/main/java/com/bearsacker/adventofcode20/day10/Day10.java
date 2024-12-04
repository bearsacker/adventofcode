package com.bearsacker.adventofcode20.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 10: Adapter Array ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day10.txt"));
        List<Integer> numbers = reader.lines().map(Integer::parseInt).collect(Collectors.toList());
        reader.close();

        Chain chain = new Chain(numbers);
        HashMap<Integer, Integer> differences = chain.sortChainByDifferences();
        System.out.println("Part 1: " + differences.get(1) * differences.get(3));
        System.out.println("Part 2: " + chain.findArrangements());
    }

}
