package com.bearsacker.adventofcode20.day21;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 {

    public static void main(String[] args) throws IOException {
        List<Food> foods = Files.lines(new File("2020/day21.txt").toPath()).map(x -> new Food(x)).collect(Collectors.toList());

        System.out.println("\n--- Day 21: Allergen Assessment ---");
        System.out.println("Part 1: " + 1);
    }

}
