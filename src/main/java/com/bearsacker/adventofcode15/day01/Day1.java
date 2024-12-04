package com.bearsacker.adventofcode15.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("2015/day01.txt"));
        String line = reader.lines().reduce("", (a, b) -> a + b);
        reader.close();

        int floor = 0;
        int index = 0;
        boolean found = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                floor++;
            } else if (line.charAt(i) == ')') {
                floor--;

                if (!found && floor == -1) {
                    index = i + 1;
                    found = true;
                }
            }
        }

        System.out.println("\n--- Day 1: Not Quite Lisp ---");
        System.out.println("Part 1: " + floor);
        System.out.println("Part 2: " + index);
    }

}
