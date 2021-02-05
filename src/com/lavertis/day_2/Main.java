package com.lavertis.day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static long part1(List<String> lines) {
        Predicate<String> predicate = s -> {
            String[] tab = s.split(" ");
            List<Integer> range = Arrays.stream(tab[0].split("-")).map(Integer::valueOf).collect(Collectors.toList());
            char c = tab[1].charAt(0);
            long count = tab[2].chars().filter(ch -> ch == c).count();
            return count >= range.get(0) && count <= range.get(1);
        };
        return lines.stream().filter(predicate).count();
    }

    public static long part2(List<String> lines) {
        Predicate<String> predicate = s -> {
            String[] tab = s.split(" ");
            List<Integer> places = Arrays.stream(tab[0].split("-")).map(Integer::valueOf).collect(Collectors.toList());
            char c = tab[1].charAt(0);
            return (tab[2].charAt(places.get(0) - 1) == c) ^ (tab[2].charAt(places.get(1) - 1) == c);
        };
        return lines.stream().filter(predicate).count();
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_2/input.txt"));
        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " + part2(lines));
    }
}