package com.lavertis.day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_10/input.txt"));
        List<Integer> numbers = lines.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        numbers.add(0);
        Integer builtInAdapterVoltage = numbers.stream().max(Integer::compareTo).orElse(-1) + 3;
        numbers.add(builtInAdapterVoltage);
        Collections.sort(numbers);
        System.out.println("Part 1: " + part1(numbers));
    }

    public static int part1(List<Integer> numbers) {
        int oneVolt = 0;
        int threeVolts = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) - numbers.get(i - 1) == 1)
                oneVolt++;
            else if (numbers.get(i) - numbers.get(i - 1) == 3)
                threeVolts++;
        }
        return oneVolt * threeVolts;
    }
}