package com.bearsacker.adventofcode20.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Group> groups = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("day6/input.txt"));

        String groupData = "";
        String line = reader.readLine();
        do {
            groupData += " " + line;

            if (line.isEmpty()) {
                groups.add(new Group(groupData));
                groupData = "";
            }

            line = reader.readLine();
        } while (line != null);

        groups.add(new Group(groupData));

        reader.close();

        System.out.println("Part 1: counts: " + groups.stream().map(x -> x.countsAnyone()).reduce(0, (a, b) -> a + b));
        System.out.println("Part 2: counts: " + groups.stream().map(x -> x.countsEveryone()).reduce(0, (a, b) -> a + b));
    }

}
