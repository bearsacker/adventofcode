package com.bearsacker.adventofcode21.day04;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Day4 {

    public static Optional<Board> findWinnerBoard(ArrayList<Board> boards) {
        return boards.parallelStream().filter(x -> x.isWin()).findFirst();
    }

    public static boolean isAnyBoardWin(ArrayList<Board> boards) {
        return boards.parallelStream().anyMatch(x -> x.isWin());
    }

    public static int playToFindFirstWinner(List<Integer> draws, ArrayList<Board> boards) {
        for (int draw : draws) {
            boards.forEach(x -> x.mark(draw));
            Optional<Board> winner = findWinnerBoard(boards);
            if (winner.isPresent()) {
                return winner.get().getScore() * draw;
            }
        }

        return -1;
    }

    public static int playToFindLastWinner(List<Integer> draws, ArrayList<Board> boards) {
        int lastWinnerScore = -1;

        for (int draw : draws) {
            boards.forEach(x -> x.mark(draw));
            Optional<Board> winner = findWinnerBoard(boards);
            if (winner.isPresent()) {
                lastWinnerScore = winner.get().getScore() * draw;
                boards.removeIf(x -> x.isWin());
            }
        }

        return lastWinnerScore;
    }

    public static void main(String[] args) throws IOException {
        LinkedList<String> inputs =
                new LinkedList<>(Files.lines(new File("2021/day04.txt").toPath()).filter(x -> !x.isEmpty()).collect(toList()));

        List<Integer> draws = Arrays.stream(inputs.poll().split(",")).map(Integer::parseInt).collect(toList());

        ArrayList<Board> boards = new ArrayList<>();
        while (!inputs.isEmpty()) {
            boards.add(new Board(asList(inputs.poll(), inputs.poll(), inputs.poll(), inputs.poll(), inputs.poll())));
        }

        System.out.println("\n--- Day 4: Giant Squid ---");
        System.out.println("Part 1: " + playToFindFirstWinner(draws, boards));
        System.out.println("Part 2: " + playToFindLastWinner(draws, boards));
    }

}
