package com.bearsacker.adventofcode24.day04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {

    private final static Pattern PATTERN_XMAS = Pattern.compile("XMAS");

    private final static Pattern PATTERN_SAMX = Pattern.compile("SAMX");

    public static List<String> extractHorizontals(char[][] grid) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            lines.add(new String(grid[i]));
        }

        return lines;
    }

    public static List<String> extractVerticals(char[][] grid) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            String line = "";
            for (int j = 0; j < grid[0].length; j++) {
                line += grid[j][i];
            }
            lines.add(line);
        }

        return lines;
    }

    public static List<String> extractDiagonals(char[][] grid) {
        List<String> diagonals = new ArrayList<>();
        int n = grid.length;

        // Diagonales principales (en bas à gauche vers en haut à droite)
        for (int start = 0; start < n; start++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = start, col = 0; row >= 0 && col < n; row--, col++) {
                diagonal.append(grid[row][col]);
            }
            diagonals.add(diagonal.toString());
        }

        for (int start = 1; start < n; start++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = n - 1, col = start; row >= 0 && col < n; row--, col++) {
                diagonal.append(grid[row][col]);
            }
            diagonals.add(diagonal.toString());
        }

        // Diagonales secondaires (en haut à gauche vers en bas à droite)
        for (int start = 0; start < n; start++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = start, col = 0; row < n && col < n; row++, col++) {
                diagonal.append(grid[row][col]);
            }
            diagonals.add(diagonal.toString());
        }

        for (int start = 1; start < n; start++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0, col = start; row < n && col < n; row++, col++) {
                diagonal.append(grid[row][col]);
            }
            diagonals.add(diagonal.toString());
        }

        return diagonals;
    }

    public static long countXMASSAMX(char[][] grid) {
        ArrayList<String> lines = new ArrayList<>();
        lines.addAll(extractHorizontals(grid));
        lines.addAll(extractVerticals(grid));
        lines.addAll(extractDiagonals(grid));

        return lines.stream().map(x -> PATTERN_XMAS.matcher(x).results().count() + PATTERN_SAMX.matcher(x).results().count())
                .reduce(0L, (a, b) -> a + b);
    }

    public static char[][] makeGridFromList(List<String> lines) {
        char[][] grid = new char[lines.get(0).length()][lines.size()];
        for (int i = 0; i < lines.get(0).length(); i++) {
            for (int j = 0; j < lines.size(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        return grid;
    }

    public static void main(String[] args) throws IOException {
        List<String> data = Files.lines(new File("2024/day04.txt").toPath()).collect(Collectors.toList());
        char[][] grid = makeGridFromList(data);

        System.out.println("\n--- Day 4: Ceres Search ---");
        System.out.println("Part 1: " + countXMASSAMX(grid));
    }

}
