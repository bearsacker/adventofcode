package com.bearsacker.adventofcode20.day13;

import static java.lang.Integer.parseInt;
import static java.math.BigInteger.valueOf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Day13 {

    public static ArrayList<Shuttle> parseShuttles(String line) {
        ArrayList<Shuttle> shuttles = new ArrayList<>();

        String[] rawShuttles = line.split(",");
        for (int i = 0; i < rawShuttles.length; i++) {
            if (!rawShuttles[i].equals("x")) {
                shuttles.add(new Shuttle(parseInt(rawShuttles[i]), i));
            }
        }

        return shuttles;
    }

    public static BigInteger find(ArrayList<Shuttle> shuttles) {
        BigInteger n = shuttles.stream().map(x -> x.getId()).reduce(valueOf(1), (a, b) -> a.multiply(b));
        BigInteger x = valueOf(0);

        // https://en.wikipedia.org/wiki/Chinese_remainder_theorem
        // n = n1 * n2 * ... * ni
        // npi = n / ni
        // ei = npi %^-1 ni
        // x = E [npi * ei * resti] % n

        for (int i = 1; i < shuttles.size(); i++) {
            BigInteger np = n.divide(shuttles.get(i).getId());
            BigInteger e = np.modInverse(shuttles.get(i).getId());

            x = x.add(np.multiply(shuttles.get(i).getRest()).multiply(e));
        }

        return x.mod(n);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 13: Shuttle Search ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day13.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        int timestamp = parseInt(lines.get(0));
        ArrayList<Shuttle> shuttles = parseShuttles(lines.get(1));

        Shuttle nextShuttle = shuttles.stream().min(Comparator.comparingLong(x -> x.getEarliestDepart(timestamp)))
                .orElseThrow(NoSuchElementException::new);

        long diffTime = nextShuttle.getEarliestDepart(timestamp) - timestamp;

        System.out.println("Part 1: " + nextShuttle.getId().longValue() * diffTime);
        System.out.println("Part 2: " + find(shuttles));
    }

}
