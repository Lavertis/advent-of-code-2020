package com.lavertis.day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_8/input.txt"));
        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " + part2(lines));
    }

    public static Integer part2(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            switch (split[0]) {
                case "nop" -> {
                    split[0] = "jmp";
                    lines.set(i, split[0] + ' ' + split[1]);
                    Integer acc = checkIfTerminates(lines);
                    if (acc != null)
                        return acc;
                    split[0] = "nop";
                    lines.set(i, split[0] + ' ' + split[1]);
                }
                case "jmp" -> {
                    split[0] = "nop";
                    lines.set(i, split[0] + ' ' + split[1]);
                    Integer acc = checkIfTerminates(lines);
                    if (acc != null)
                        return acc;
                    split[0] = "jmp";
                    lines.set(i, split[0] + ' ' + split[1]);
                }
            }
        }
        return null;
    }

    public static Integer checkIfTerminates(List<String> lines) {
        List<Integer> visitedLines = new ArrayList<>(Collections.nCopies(lines.size(), 0));
        int acc = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (visitedLines.get(i) != 0)
                break;
            visitedLines.set(i, 1);
            String[] split = lines.get(i).split(" ");
            switch (split[0]) {
                case "acc" -> {
                    acc += Integer.parseInt(split[1]);
                    if (i == lines.size() - 1)
                        return acc;
                }
                case "jmp" -> {
                    i += Integer.parseInt(split[1]) - 1;
                    if (i + 1 >= lines.size())
                        return acc;
                }
            }
        }
        return null;
    }

    public static Integer part1(List<String> lines) {
        List<Integer> visitedLines = new ArrayList<>(Collections.nCopies(lines.size(), 0));
        int acc = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (visitedLines.get(i) != 0)
                break;
            visitedLines.set(i, 1);
            String[] split = lines.get(i).split(" ");
            switch (split[0]) {
                case "acc" -> acc += Integer.parseInt(split[1]);
                case "jmp" -> i += Integer.parseInt(split[1]) - 1;
            }
        }
        return acc;
    }
}