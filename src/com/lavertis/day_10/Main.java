package com.lavertis.day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        List<Long> buf = new ArrayList<>(Collections.nCopies(numbers.size(), -1L));
        System.out.println("Part 2: " + comb(numbers, 0, buf));
    }

    public static Long comb(List<Integer> numbers, int index, List<Long> buf) {
        if (buf.get(index) != -1)
            return buf.get(index);
        long counter = 0;
        if (index == numbers.size() - 1)
            return 1L;
        for (int i = index + 1; i < numbers.size(); i++)
            if (numbers.get(i) - numbers.get(index) <= 3)
                counter += comb(numbers, i, buf);
            else
                break;
        buf.set(index, counter);
        return counter;
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