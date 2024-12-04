package com.bearsacker.adventofcode20.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 11: Seating System ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day11.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        SeatsMap seatsMap = new SeatsMap(lines);
        while (!seatsMap.applyRules(false)) {
        }
        System.out.println("Part 1: " + seatsMap.countSeats(SeatsMap.OCCUPIED));

        seatsMap = new SeatsMap(lines);
        while (!seatsMap.applyRules(true)) {
        }
        System.out.println("Part 2: " + seatsMap.countSeats(SeatsMap.OCCUPIED));
    }

}
