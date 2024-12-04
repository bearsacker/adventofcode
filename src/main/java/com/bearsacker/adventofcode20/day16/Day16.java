package com.bearsacker.adventofcode20.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 {

    public static Long getErrorValuesCount(List<Field> fields, List<Ticket> tickets) {
        return tickets.stream().map(x -> x.getErrorValue(fields)).reduce(0L, (a, b) -> a + b);
    }

    public static void findFieldsPosition(List<Field> fields, List<Ticket> tickets, Ticket yourTicket) {
        List<Ticket> validTickets = tickets.stream().filter(x -> x.getErrorValue(fields) == 0).collect(Collectors.toList());

        // Find all possible positions for each field
        for (Field field : fields) {
            for (int i = 0; i < yourTicket.getValuesNumber(); i++) {
                final int position = i;
                if (validTickets.stream().allMatch(x -> field.isValid(x.getField(position)))) {
                    field.addPosition(i);
                }
            }
        }

        ArrayList<Field> notPositioned = new ArrayList<>(fields);
        while (!notPositioned.isEmpty()) {
            Field toPosition = notPositioned.stream().filter(x -> x.isPositioned()).findAny().orElse(null);
            if (toPosition != null) {
                notPositioned.remove(toPosition);
                notPositioned.forEach(x -> x.removePosition(toPosition.getPosition()));
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("\n--- Day 16: Ticket Translation ---");

        BufferedReader reader = new BufferedReader(new FileReader("2020/day16.txt"));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();

        List<Field> fields = Field.parse(lines);
        List<Ticket> tickets = Ticket.parse(lines);
        Ticket yourTicket = tickets.get(0);
        tickets.remove(0);

        System.out.println("Part 1: " + getErrorValuesCount(fields, tickets));

        findFieldsPosition(fields, tickets, yourTicket);
        System.out.println("Part 2: " + fields.stream().filter(x -> x.getName().startsWith("departure")).map(x -> yourTicket.getField(x))
                .reduce(1L, (a, b) -> a * b));
    }

}
