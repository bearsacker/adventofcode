package com.bearsacker.adventofcode20.day20;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tile {

    private final static Pattern NUMBER_PATTERN = Pattern.compile("Tile ([0-9]+):");

    public final static int SIZE = 10;

    private int id;

    private boolean[][] cells;

    private ArrayList<String> connectors;

    public Tile(BufferedReader reader) throws IOException {
        cells = new boolean[SIZE][SIZE];
        String idLine = reader.readLine();
        if (idLine != null) {
            Matcher matcher = NUMBER_PATTERN.matcher(idLine);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
            }

            for (int i = 0; i < SIZE; i++) {
                String line = reader.readLine();
                for (int j = 0; j < SIZE; j++) {
                    cells[i][j] = line.charAt(j) == '#';
                }
            }
        } else {
            throw new EOFException();
        }

        connectors = new ArrayList<>();
        connectors.add(encodeLine(0));
        connectors.add(encodeFlipLine(0));
        connectors.add(encodeLine(SIZE - 1));
        connectors.add(encodeFlipLine(SIZE - 1));
        connectors.add(encodeColumn(0));
        connectors.add(encodeFlipColumn(0));
        connectors.add(encodeColumn(SIZE - 1));
        connectors.add(encodeFlipColumn(SIZE - 1));
    }

    private String encodeLine(int line) {
        String result = "";
        for (int i = 0; i < SIZE; i++) {
            result += cells[line][i] ? '#' : ".";
        }

        return result;
    }

    private String encodeFlipLine(int line) {
        return new StringBuilder(encodeLine(line)).reverse().toString();
    }

    private String encodeColumn(int column) {
        String result = "";
        for (int i = 0; i < SIZE; i++) {
            result += cells[i][column] ? '#' : ".";
        }

        return result;
    }

    private String encodeFlipColumn(int column) {
        return new StringBuilder(encodeColumn(column)).reverse().toString();
    }

    public boolean isConnected(Tile other) {
        return id != other.id && connectors.parallelStream().anyMatch(x -> other.connectors.contains(x));
    }

    @Override
    public String toString() {
        String result = id + "\n";
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result += cells[i][j] ? '#' : ".";
            }
            result += "\n";
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
