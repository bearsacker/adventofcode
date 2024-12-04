package com.bearsacker.adventofcode20.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 17: Conway Cubes ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day17.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        PocketDimension dimension = new PocketDimension(lines);
        for (int i = 0; i < 6; i++) {
            dimension.applyRules();
        }
        System.out.println("Part 1: " + dimension.countActiveCubes());

        Pocket4Dimension dimension4 = new Pocket4Dimension(lines);
        for (int i = 0; i < 6; i++) {
            dimension4.applyRules();
        }
        System.out.println("Part 2: " + dimension4.countActiveCubes());
    }

}
