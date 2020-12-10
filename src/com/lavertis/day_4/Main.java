package com.lavertis.day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static String[] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    static Predicate<String> hasRequiredFields = s -> Arrays.stream(requiredFields).filter(s::contains).count() == 7;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_4/input.txt"));
        String fileInOneLine = String.valueOf(lines)
                .replaceAll(", ,", "\n")
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace("  ", " ");
        List<String> list = Arrays.asList(fileInOneLine.split("\n"));
        System.out.println("Part 1: " + part1(list));
        System.out.println("Part 2: " + part2(list));
    }

    public static long part2(List<String> list) {
        long validCount = 0;
        for (String s : list) {
            if (Arrays.stream(new String[]{s}).noneMatch(hasRequiredFields))
                continue;
            String[] entry = s.split(" ");
            boolean valid = true;
            for (String e : entry) {
                String[] split = e.split(":");
                switch (split[0]) {
                    case "byr" -> {
                        String patternString = "^\\d{4}$";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (!matches) {
                            valid = false;
                            break;
                        }
                        int number = Integer.parseInt(split[1]);
                        if (!(1920 <= number && number <= 2002))
                            valid = false;
                    }
                    case "iyr" -> {
                        String patternString = "^\\d{4}$";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (!matches) {
                            valid = false;
                            break;
                        }
                        int number = Integer.parseInt(split[1]);
                        if (!(2010 <= number && number <= 2020))
                            valid = false;
                    }
                    case "eyr" -> {
                        String patternString = "^\\d{4}$";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (!matches) {
                            valid = false;
                            break;
                        }
                        int number = Integer.parseInt(split[1]);
                        if (!(2020 <= number && number <= 2030))
                            valid = false;
                    }
                    case "hgt" -> {
                        String patternString = "(^[0-9]{2}in$)|(^[0-9]{3}cm$)";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (!matches) {
                            valid = false;
                            break;
                        }

                        String unit = split[1].substring(split[1].length() - 2);
                        int number = Integer.parseInt(split[1].substring(0, split[1].length() - 2));
                        switch (unit) {
                            case "cm" -> {
                                if (!(150 <= number && number <= 193))
                                    valid = false;
                            }
                            case "in" -> {
                                if (!(59 <= number && number <= 76))
                                    valid = false;
                            }
                        }
                    }
                    case "hcl" -> {
                        String patternString = "^#([a-f0-9]{6}|[a-f0-9]{3})$";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (split[1].length() != 7)
                            matches = false;
                        if (!matches)
                            valid = false;
                    }
                    case "ecl" -> {
                        List<String> colours = new ArrayList<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
                        if (!colours.contains(split[1]))
                            valid = false;
                    }
                    case "pid" -> {
                        String patternString = "^\\d{9}$";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(split[1]);
                        boolean matches = matcher.matches();
                        if (!matches)
                            valid = false;
                    }
                }
            }
            if (valid)
                validCount++;
        }
        return validCount;
    }

    public static long part1(List<String> list) {
        return list.stream().filter(hasRequiredFields).count();
    }
}
