package com.bearsacker.adventofcode24.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private List<Integer> levels;

    public Report(String raw) {
        levels = Arrays.stream(raw.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private boolean isSafe(List<Integer> levels) {
        int sign = 0;
        int previous = levels.get(0);
        for (int i = 1; i < levels.size(); i++) {
            int current = levels.get(i);
            int distance = Math.abs(previous - current);
            if (distance < 1 || distance > 3) {
                return false;
            }

            if (sign == 0) {
                sign = Integer.compare(previous, current);
            } else if (Integer.compare(previous, current) != sign) {
                return false;
            }

            previous = current;
        }

        return true;
    }

    public boolean isSafe() {
        return isSafe(levels);
    }

    public boolean isSafeWithDampener() {
        if (isSafe()) {
            return true;
        }

        for (int i = 0; i < levels.size(); i++) {
            ArrayList<Integer> removed = new ArrayList<>(levels);
            removed.remove(i);

            if (isSafe(removed)) {
                return true;
            }
        }

        return false;
    }
}
