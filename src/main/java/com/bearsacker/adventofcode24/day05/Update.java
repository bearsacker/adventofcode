package com.bearsacker.adventofcode24.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Update {

    private List<Integer> pages;

    public Update(String raw) {
        pages = Arrays.stream(raw.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public Update(List<Integer> pages) {
        this.pages = new ArrayList<>(pages);
    }

    public boolean isValid(List<Rule> rules) {
        return rules.stream().allMatch(x -> x.isValid(pages));
    }

    public Rule firstRuleNotValid(List<Rule> rules) {
        return rules.stream().filter(x -> !x.isValid(pages)).findFirst().orElse(null);
    }

    public void invertPages(int x, int y) {
        int iX = pages.indexOf(x);
        int iY = pages.indexOf(y);

        pages.set(iX, y);
        pages.set(iY, x);
    }

    public Update correct(List<Rule> rules) {
        Update corrected = new Update(pages);

        Rule notValid = null;
        while ((notValid = corrected.firstRuleNotValid(rules)) != null) {
            corrected.invertPages(notValid.x, notValid.y);
        }

        return corrected;
    }

    public int middlePage() {
        return pages.get(pages.size() / 2);
    }
}
