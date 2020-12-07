package com.lavertis.day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    static String[] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    static Predicate<String> hasRequiredFields = s -> Arrays.stream(requiredFields).filter(s::contains).count() == 7;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_4/input.txt"));
        String fileInOneLine = String.valueOf(lines).replaceAll(", ,", "\n");
        List<String> list = Arrays.asList(fileInOneLine.split("\n"));
        System.out.println("Part 1: " + part1(list));
    }

    public static long part1(List<String> list) {
        return list.stream().filter(hasRequiredFields).count();
    }
}
