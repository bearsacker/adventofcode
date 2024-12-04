package com.bearsacker.adventofcode20.day14;

import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 {

    public final static Pattern REGEX_MASK = Pattern.compile("^mask = ([01X]{36})$");

    public final static Pattern REGEX_MEMORY_WRITE = Pattern.compile("^mem\\[([0-9]+)\\] = ([0-9]+)$");

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 14: Docking Data ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day14.txt"));
        List<String> instructions = reader.lines().collect(Collectors.toList());
        reader.close();

        Memory memory = new Memory();
        Memory memory2 = new Memory();

        for (String instruction : instructions) {
            Matcher maskMatcher = REGEX_MASK.matcher(instruction);
            Matcher memoryWriteMatcher = REGEX_MEMORY_WRITE.matcher(instruction);

            if (maskMatcher.matches()) {
                memory.setMask(maskMatcher.group(1));
                memory2.setMask(maskMatcher.group(1));
            } else if (memoryWriteMatcher.matches()) {
                memory.put(parseLong(memoryWriteMatcher.group(1)), new Binary(parseLong(memoryWriteMatcher.group(2)), memory.getMask()));

                Binary memoryValue = new Binary(parseLong(memoryWriteMatcher.group(2)));
                Binary adresses = new Binary(parseLong(memoryWriteMatcher.group(1)));
                adresses.applyFloatingBitsMask(memory2.getMask());
                for (Long adress : adresses.getLongValues()) {
                    memory2.put(adress, memoryValue);
                }
            }
        }

        System.out.println("Part 1: " + memory.sum());
        System.out.println("Part 2: " + memory2.sum());
    }

}
