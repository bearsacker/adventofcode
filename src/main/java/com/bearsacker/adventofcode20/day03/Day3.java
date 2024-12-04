package com.bearsacker.adventofcode20.day03;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.bearsacker.utils.Vec2;

public class Day3 {

    public final static List<Vec2> SLOPES = asList(
            new Vec2(1, 1), //
            new Vec2(3, 1), //
            new Vec2(5, 1), //
            new Vec2(7, 1), //
            new Vec2(1, 2));

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2020/day03.txt"));
        Forest map = new Forest(reader.lines().collect(Collectors.toList()));
        reader.close();

        System.out.println("\n--- Day 3: Toboggan Trajectory ---");
        int count = map.countTreesForDirection(new Vec2(3, 1));
        System.out.println("Part 1: " + count);

        int product = SLOPES.stream().map(slope -> map.countTreesForDirection(slope)).reduce(1, (a, b) -> a * b);
        System.out.println("Part 2: " + product);
    }

}
