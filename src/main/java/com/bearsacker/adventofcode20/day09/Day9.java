package com.bearsacker.adventofcode20.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 9: Encoding Error ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day09.txt"));
        List<Long> numbers = reader.lines().map(Long::parseLong).collect(Collectors.toList());
        reader.close();

        XMas xmas = new XMas(numbers, 25);

        Long wrongNumber = xmas.firstWrongNumber();
        System.out.println("Part 1: " + wrongNumber);

        ArrayList<Long> sumList = xmas.findContiguousSum(wrongNumber);
        System.out.println("Part 2: " + (sumList.get(0) + sumList.get(sumList.size() - 1)));
    }

}
