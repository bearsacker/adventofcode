package com.bearsacker.adventofcode24.day04;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class Day4Test {

    private final static List<String> DATA = asList(
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX");

    @Test
    public void testMakeGridFromList() {
        char[][] grid = Day4.makeGridFromList(DATA);

        assertEquals(10, grid.length);
        assertEquals(10, grid[0].length);
    }

    @Test
    public void testExtractHorizontals() {
        char[][] grid = Day4.makeGridFromList(DATA);
        List<String> result = Day4.extractHorizontals(grid);

        assertEquals(10, result.size());
        assertEquals(DATA, result);
    }

    @Test
    public void testExtractVerticals() {
        char[][] grid = Day4.makeGridFromList(DATA);
        List<String> result = Day4.extractVerticals(grid);

        assertEquals(10, result.size());
        assertEquals(asList(
                "MMAMXXSSMM",
                "MSMSMXMAAX",
                "MAXAAASXMM",
                "SMSMSMMAMX",
                "XXXAAMSMMA",
                "XMMSMXAAXX",
                "MSAMXXSSMM",
                "AMASAAXAMA",
                "SSMMMMSAMS",
                "MAMXMASAMX"), result);
    }

    @Test
    public void testExtractDiagonals() {
        char[][] grid = Day4.makeGridFromList(DATA);
        List<String> result = Day4.extractDiagonals(grid);

        assertEquals(38, result.size());
    }

    @Test
    public void testCountXMASSAMX() {
        char[][] grid = Day4.makeGridFromList(DATA);

        assertEquals(18, Day4.countXMASSAMX(grid));
    }

    @Test
    public void testCountXMAS() {
        char[][] grid = Day4.makeGridFromList(DATA);

        assertEquals(9, Day4.countXMAS(grid));
    }

    @Test
    public void testIsXMAS() {
        char[][] grid = Day4.makeGridFromList(DATA);

        assertTrue(Day4.isXMAS(grid, 1, 2));
        assertTrue(Day4.isXMAS(grid, 2, 6));
        assertTrue(Day4.isXMAS(grid, 2, 7));
        assertTrue(Day4.isXMAS(grid, 3, 2));
        assertTrue(Day4.isXMAS(grid, 3, 4));
        assertTrue(Day4.isXMAS(grid, 7, 1));
        assertTrue(Day4.isXMAS(grid, 7, 3));
        assertTrue(Day4.isXMAS(grid, 7, 5));
        assertTrue(Day4.isXMAS(grid, 7, 7));

        assertFalse(Day4.isXMAS(grid, 4, 2));
        assertFalse(Day4.isXMAS(grid, 4, 4));
        assertFalse(Day4.isXMAS(grid, 5, 2));
        assertFalse(Day4.isXMAS(grid, 5, 7));
        assertFalse(Day4.isXMAS(grid, 6, 5));
        assertFalse(Day4.isXMAS(grid, 7, 8));
        assertFalse(Day4.isXMAS(grid, 8, 1));
    }
}
