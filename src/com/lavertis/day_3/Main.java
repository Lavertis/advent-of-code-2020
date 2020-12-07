package com.lavertis.day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static int part1(List<String> lines, int lineLength, int right, int down) {
        int counter = 0;
        int j = 0;
        for (int i = 0; i < lines.size(); i += down) {
            if (j >= lineLength)
                j -= lineLength;
            if (lines.get(i).charAt(j) == '#')
                counter++;
            j += right;
        }
        return counter;
    }

    public static int part2(List<String> lines, int lineLength) {
        int a = part1(lines, lineLength, 1, 1);
        int b = part1(lines, lineLength, 3, 1);
        int c = part1(lines, lineLength, 5, 1);
        int d = part1(lines, lineLength, 7, 1);
        int e = part1(lines, lineLength, 1, 2);
        return a * b * c * d * e;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_3/input.txt"));
        int lineLength = lines.get(0).length();
        System.out.println("Part1 : " + part1(lines, lineLength, 3, 1));
        System.out.println("Part2 : " + part2(lines, lineLength));
    }
}
