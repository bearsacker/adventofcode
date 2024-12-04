package com.bearsacker.adventofcode24.day02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<Report> reports = Files.lines(new File("2024/day02.txt").toPath()).map(x -> new Report(x)).collect(Collectors.toList());

        System.out.println("\n--- Day 2: Red-Nosed Reports ---");
        System.out.println("Part 1: " + reports.parallelStream().filter(Report::isSafe).count());
        System.out.println("Part 2: " + reports.parallelStream().filter(Report::isSafeWithDampener).count());
    }

}
