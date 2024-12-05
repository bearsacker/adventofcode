package com.bearsacker.adventofcode24.day05;

import java.util.List;

public class Rule {

    public int x;

    public int y;

    public Rule(String raw) {
        String[] data = raw.split("\\|");
        x = Integer.parseInt(data[0]);
        y = Integer.parseInt(data[1]);
    }

    public boolean isValid(List<Integer> values) {
        if (!values.contains(x) || !values.contains(y)) {
            return true;
        }

        if (values.indexOf(x) < values.indexOf(y)) {
            return true;
        }

        return false;
    }
}
