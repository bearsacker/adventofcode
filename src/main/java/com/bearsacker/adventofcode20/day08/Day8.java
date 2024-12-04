package com.bearsacker.adventofcode20.day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 8: Handheld Halting ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day08.txt"));
        List<Instruction> instructions = reader.lines().map(Instruction::parse).collect(Collectors.toList());
        reader.close();

        Program program = new Program(instructions);
        program.run();

        System.out.println("Part 1: " + program.getAccumulator());

        for (int fixingPointer = 0; fixingPointer < program.getLength(); fixingPointer++) {
            program.runWithFix(fixingPointer);
            if (program.isEndedCorrectly()) {
                break;
            }
        }

        System.out.println("Part 2: " + program.getAccumulator());
    }

}
