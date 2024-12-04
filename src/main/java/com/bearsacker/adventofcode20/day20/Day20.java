package com.bearsacker.adventofcode20.day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day20 {

    public static long findCorners(ArrayList<Tile> tiles) {
        long count = 1;
        for (Tile tile : tiles) {
            if (tiles.parallelStream().filter(x -> tile.isConnected(x)).count() == 2) {
                count *= tile.getId();
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Tile> tiles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("2020/day20.txt"));
        while (true) {
            try {
                Tile tile = new Tile(reader);
                tiles.add(tile);
                reader.readLine();
            } catch (Exception e) {
                break;
            }
        }
        reader.close();

        System.out.println("\n--- Day 20: Jurassic Jigsaw ---");
        System.out.println("Part 1: " + findCorners(tiles));
    }

}
