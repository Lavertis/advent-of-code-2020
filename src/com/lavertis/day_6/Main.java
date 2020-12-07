package com.lavertis.day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_6/input.txt"));
        lines.add("");
        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " + part2(lines));
    }

    public static int part2(List<String> lines) {
        List<Set<Character>> answersFromEachGroup = new ArrayList<>();
        List<Set<Character>> groupSet = new ArrayList<>();
        Set<Character> temp = new HashSet<>();

        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = 0; j < lines.get(i).length(); j++)
                temp.add(lines.get(i).charAt(j));
            groupSet.add(new HashSet<>(temp));
            temp.clear();

            if (lines.get(i + 1).equals("")) {
                for (Set<Character> set : groupSet)
                    groupSet.get(0).retainAll(set);
                answersFromEachGroup.add(new HashSet<>(groupSet.get(0)));
                groupSet.clear();
                i++;
            }
        }
        return answersFromEachGroup.stream().mapToInt(Set::size).sum();
    }

    public static int part1(List<String> lines) {
        List<Set<Character>> answersFromEachGroup = new ArrayList<>();
        Set<Character> temp = new HashSet<>();

        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = 0; j < lines.get(i).length(); j++)
                temp.add(lines.get(i).charAt(j));

            if (lines.get(i + 1).equals("")) {
                answersFromEachGroup.add(new HashSet<>(temp));
                temp.clear();
                i++;
            }
        }
        return answersFromEachGroup.stream().mapToInt(Set::size).sum();
    }
}
