package com.bearsacker.adventofcode20.day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Day7 {

    public static boolean findBagColor(HashMap<String, Rule> rules, Rule rule, String wantedColor) {
        if (rule.getName().equals(wantedColor)) {
            return true;
        }

        if (rule.hasDependencies()) {
            for (Entry<String, Integer> dependency : rule.getDependencies().entrySet()) {
                if (findBagColor(rules, rules.get(dependency.getKey()), wantedColor)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int countBags(HashMap<String, Rule> rules, Rule rule, boolean isRoot) {
        int count = isRoot ? 0 : 1;
        if (rule.hasDependencies()) {
            for (Entry<String, Integer> dependency : rule.getDependencies().entrySet()) {
                count += dependency.getValue() * countBags(rules, rules.get(dependency.getKey()), false);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Rule> rules = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader("2020/day07.txt"));
        reader.lines().forEach(line -> {
            Rule rule = new Rule(line);
            rules.put(rule.getName(), rule);
        });
        reader.close();

        int count = 0;
        for (Entry<String, Rule> rule : rules.entrySet()) {
            if (!rule.getKey().equals("shiny gold")) {
                count += findBagColor(rules, rule.getValue(), "shiny gold") ? 1 : 0;
            }
        }

        System.out.println("\n--- Day 7: Handy Haversacks ---");
        System.out.println("Part 1: " + count);
        System.out.println("Part 2: " + countBags(rules, rules.get("shiny gold"), true));
    }

}
