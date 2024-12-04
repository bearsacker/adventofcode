package com.bearsacker.adventofcode21.day03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    public static List<String> findStartWithPrefix(List<String> inputs, String prefix) {
        return inputs.stream().filter(x -> x.startsWith(prefix)).collect(Collectors.toList());
    }

    public static int getMostCommonBitAtIndex(List<String> inputs, int index) {
        int n0 = 0;
        int n1 = 0;

        for (String input : inputs) {
            n0 += input.charAt(index) == '0' ? 1 : 0;
            n1 += input.charAt(index) == '1' ? 1 : 0;
        }

        return n0 > n1 ? 0 : 1;
    }

    public static int getGammaEpsilonProduct(List<String> inputs, int size) {
        String gamma = "";
        String epsilon = "";
        for (int i = 0; i < size; i++) {
            int mostCommonBit = getMostCommonBitAtIndex(inputs, i);
            gamma += Integer.toString(mostCommonBit);
            epsilon += Integer.toString(mostCommonBit == 0 ? 1 : 0);
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }

    public static int getOxygen(List<String> inputs, int size) {
        String oxygen = null;
        List<String> inputsRemaining = new ArrayList<>(inputs);
        String prefix = "";
        for (int i = 0; i < size; i++) {
            int mostCommonBit = getMostCommonBitAtIndex(inputsRemaining, i);
            prefix += Integer.toString(mostCommonBit);

            inputsRemaining = findStartWithPrefix(inputsRemaining, prefix);
            if (inputsRemaining.size() == 1) {
                oxygen = inputsRemaining.get(0);
                break;
            }
        }

        return Integer.parseInt(oxygen, 2);
    }

    public static int getCO2(List<String> inputs, int size) {
        String co2 = null;
        List<String> inputsRemaining = new ArrayList<>(inputs);
        String prefix = "";
        for (int i = 0; i < size; i++) {
            int lessCommonBit = getMostCommonBitAtIndex(inputsRemaining, i) == 0 ? 1 : 0;
            prefix += Integer.toString(lessCommonBit);

            inputsRemaining = findStartWithPrefix(inputsRemaining, prefix);
            if (inputsRemaining.size() == 1) {
                co2 = inputsRemaining.get(0);
                break;
            }
        }

        return Integer.parseInt(co2, 2);
    }

    public static void main(String[] args) throws IOException {
        List<String> inputs = Files.lines(new File("2021/day03.txt").toPath()).collect(Collectors.toList());

        int gammaEpsilon = getGammaEpsilonProduct(inputs, 12);
        int oxygen = getOxygen(inputs, 12);
        int co2 = getCO2(inputs, 12);

        System.out.println("\n--- Day 3: Binary Diagnostic ---");
        System.out.println("Part 1: " + gammaEpsilon);
        System.out.println("Part 2: " + (oxygen * co2));
    }

}
