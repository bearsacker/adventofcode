package com.bearsacker.adventofcode24.day06;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(new File("2024/day06.txt").toPath()).collect(Collectors.toList());

        Board board = new Board(lines);

        System.out.println("\n--- Day 6: Guard Gallivant ---");
        System.out.println("Part 1: " + board.simulate());
        System.out.println("Part 2: " + board.simulate());
    }

}
