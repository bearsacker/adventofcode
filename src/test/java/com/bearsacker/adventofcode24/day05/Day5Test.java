package com.bearsacker.adventofcode24.day05;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Day5Test {

    private final static List<String> DATA = asList(
            "47|53",
            "97|13",
            "97|61",
            "97|47",
            "75|29",
            "61|13",
            "75|53",
            "29|13",
            "97|29",
            "53|29",
            "61|53",
            "97|53",
            "61|29",
            "47|13",
            "75|47",
            "97|75",
            "47|61",
            "75|61",
            "47|29",
            "75|13",
            "53|13",
            "",
            "75,47,61,53,29",
            "97,61,53,29,13",
            "75,29,13",
            "75,97,47,61,53",
            "61,13,29",
            "97,13,75,29,47");

    private final static ArrayList<Rule> RULES = new ArrayList<>();

    private final static ArrayList<Update> UPDATES = new ArrayList<>();

    @Before
    public void init() {
        RULES.clear();
        UPDATES.clear();

        boolean parseRules = true;
        for (String line : DATA) {
            if (line.isBlank()) {
                parseRules = false;
            } else {
                if (parseRules) {
                    RULES.add(new Rule(line));
                } else {
                    UPDATES.add(new Update(line));
                }
            }
        }
    }

    @Test
    public void testMiddlePages() {
        assertEquals(6, UPDATES.size());
        assertEquals(61, UPDATES.get(0).middlePage());
        assertEquals(53, UPDATES.get(1).middlePage());
        assertEquals(29, UPDATES.get(2).middlePage());
        assertEquals(47, UPDATES.get(3).middlePage());
        assertEquals(13, UPDATES.get(4).middlePage());
        assertEquals(75, UPDATES.get(5).middlePage());
    }

    @Test
    public void testSumMiddleValidUpdates() {
        assertEquals(143, Day5.sumMiddleValidUpdates(RULES, UPDATES));
    }

    @Test
    public void testSumMiddleCorrectedUpdates() {
        assertEquals(123, Day5.sumMiddleCorrectedUpdates(RULES, UPDATES));
    }

}
