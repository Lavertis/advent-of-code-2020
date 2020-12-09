package com.lavertis.day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_9/input.txt"));
        List<Long> numbers = lines.stream().mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        Long numberWithoutProperty = part1(numbers);
        System.out.println("Part 1: " + numberWithoutProperty);
        System.out.println("Part 2: " + part2(numbers, numberWithoutProperty));
    }

    public static Long part2(List<Long> numbers, Long numberWithoutProperty) {
        for (int i = 0; i < numbers.size(); i++) {
            long sum = 0;
            for (int j = i; j < numbers.size(); j++) {
                sum += numbers.get(j);
                if (numbers.get(j).equals(numberWithoutProperty))
                    break;
                if (sum == numberWithoutProperty) {
                    long max = Long.MIN_VALUE;
                    long min = Long.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        if (numbers.get(k) > max)
                            max = numbers.get(k);
                        if (numbers.get(k) < min)
                            min = numbers.get(k);
                    }
                    return max + min;
                } else if (sum > numberWithoutProperty)
                    break;
            }
        }
        return null;
    }

    public static Long part1(List<Long> numbers) {
        int preambleLength = 25;
        for (int i = preambleLength; i < numbers.size(); i++) {
            long number = numbers.get(i);
            boolean sums = false;
            for (int j = i; j >= i - preambleLength; j--) {
                for (int k = j - 1; k >= i - preambleLength; k--) {
                    if (numbers.get(j) + numbers.get(k) == number) {
                        sums = true;
                        break;
                    }
                }
            }
            if (!sums)
                return number;
        }
        return null;
    }
}
