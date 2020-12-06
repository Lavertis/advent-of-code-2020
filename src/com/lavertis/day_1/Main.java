package com.lavertis.day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static int part1(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++)
            for (int j = i; j < numbers.size(); j++)
                if (numbers.get(i) + numbers.get(j) == 2020)
                    return numbers.get(i) * numbers.get(j);
        return 0;
    }

    public static int part2(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++)
            for (int j = i; j < numbers.size(); j++)
                for (int k = j; k < numbers.size(); k++)
                    if (numbers.get(i) + numbers.get(j) + numbers.get(k) == 2020)
                        return numbers.get(i) * numbers.get(j) * numbers.get(k);
        return 0;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_1/input.txt"));
        List<Integer> numbers = lines.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("Part 1: " + part1(numbers));
        System.out.println("Part 2: " + part2(numbers));
    }
}
