package com.lavertis.day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_5/input.txt"));
        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " + part2(lines));
    }

    public static int part2(List<String> lines) {
        int max = part1(lines);
        int min = lines.stream().mapToInt(Main::getSeatID).min().orElse(-1);
        List<Integer> allIDs = lines.stream().mapToInt(Main::getSeatID).boxed().collect(Collectors.toList());
        for (int i = min + 1; i < max; i++)
            if (!allIDs.contains(i))
                return i;
        return -1;
    }

    public static int part1(List<String> lines) {
        return lines.stream().mapToInt(Main::getSeatID).max().orElse(-1);
    }

    public static int getRow(String s) {
        String row = s.substring(0, 7);
        double start = 0;
        double end = 127;
        for (int i = 0; i < 7; i++) {
            if (row.charAt(i) == 'F')
                end = (int) ((end - start) / 2) + start;
            else if (row.charAt(i) == 'B')
                start += Math.ceil((end - start) / 2);
        }
        return (int) start;
    }

    public static int getColumn(String s) {
        String row = s.substring(7);
        double start = 0;
        double end = 7;
        for (int i = 0; i < 3; i++) {
            if (row.charAt(i) == 'L')
                end = (int) ((end - start) / 2) + start;
            else if (row.charAt(i) == 'R')
                start += Math.ceil((end - start) / 2);
        }
        return (int) start;
    }

    public static int getSeatID(String s) {
        return getRow(s) * 8 + getColumn(s);
    }
}
