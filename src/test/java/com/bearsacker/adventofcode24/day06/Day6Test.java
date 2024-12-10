package com.bearsacker.adventofcode24.day06;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Day6Test {

    private final static List<String> DATA = asList(
            "....#.....", //
            ".........#", //
            "..........", //
            "..#.......", //
            ".......#..", //
            "..........", //
            ".#..^.....", //
            "........#.", //
            "#.........", //
            "......#...");

    private final static Board BOARD = new Board(DATA);

    @Test
    public void testLoad() {
        assertEquals(10, BOARD.getWidth());
        assertEquals(10, BOARD.getHeight());
        assertEquals(4, BOARD.getStart().x);
        assertEquals(6, BOARD.getStart().y);
    }

    @Test
    public void testSimulate() {
        assertEquals(41, BOARD.simulate());
    }

}
