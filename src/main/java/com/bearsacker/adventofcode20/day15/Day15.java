package com.bearsacker.adventofcode20.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 15: Rambunctious Recitation ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day15.txt"));
        String line = reader.lines().reduce("", (a, b) -> a + b);
        reader.close();

        List<Integer> turns = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        MemoryGame game = new MemoryGame(turns);
        while (game.getTurn() < 2020) {
            game.nextTurn();
        }
        System.out.println("Part 1: " + game.getLastNumber());

        while (game.getTurn() < 30000000) {
            game.nextTurn();
        }
        System.out.println("Part 2: " + game.getLastNumber());
    }

}
