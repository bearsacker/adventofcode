package com.bearsacker.adventofcode20.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2020/day05.txt"));
        List<BoardingPass> boardingPass = reader.lines().map(x -> new BoardingPass(x)).collect(Collectors.toList());
        reader.close();

        Collections.sort(boardingPass);
        Collections.reverse(boardingPass);

        System.out.println("\n--- Day 5: Binary Boarding ---");
        System.out.println("Part 1: " + boardingPass.get(0));

        Collections.reverse(boardingPass);

        int yourSeatId = 0;
        for (int i = 0; i < boardingPass.size() - 2; i += 2) {
            int diff = boardingPass.get(i + 1).getSeatId() - boardingPass.get(i).getSeatId();
            if (diff == 2) {
                yourSeatId = boardingPass.get(i).getSeatId() + 1;
                break;
            }
        }

        System.out.println("Part 2: " + yourSeatId);
    }

}
