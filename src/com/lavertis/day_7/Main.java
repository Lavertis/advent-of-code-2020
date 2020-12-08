package com.lavertis.day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_7/input.txt"));
        List<Bag> bags = getBagsFromLine(lines);
        System.out.println("Part 1: " + part1(bags));
    }

    public static int part1(List<Bag> bags) {
        Set<String> bagsWhichCanContain = new HashSet<>();
        bagsWhichCanContain.add("shiny gold");
        Set<String> temp = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (String bagColour : bagsWhichCanContain)
                for (Bag bagToCheck : bags)
                    if (bagToCheck.getCanContain().contains(bagColour))
                        temp.add(bagToCheck.getColour());
            bagsWhichCanContain.addAll(temp);
        }
        return bagsWhichCanContain.size() - 1;
    }

    public static List<Bag> getBagsFromLine(List<String> lines) {
        List<Bag> bags = new ArrayList<>();
        for (String s : lines) {
            String line = Arrays.toString(s.split(" \\d+ "))
                    .replaceAll(" bags", "")
                    .replaceAll(" bag", "")
                    .replaceAll("\\.", "")
                    .replaceAll(",, ", ",")
                    .replaceAll(", ", ",")
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");
            String ownColour = line.split(" contain")[0];
            String[] commaSplitted = line.split(",");
            List<String> canContain = new ArrayList<>(Arrays.asList(commaSplitted).subList(1, commaSplitted.length));
            Bag bag = new Bag(ownColour, canContain);
            bags.add(bag);
        }
        return bags;
    }
}

class Bag {
    private final String colour;
    private final List<String> canContain;

    public Bag(String name, List<String> canContain) {
        this.colour = name;
        this.canContain = canContain;
    }

    public String getColour() {
        return colour;
    }

    public List<String> getCanContain() {
        return canContain;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "name='" + colour + '\'' +
                ", canContain=" + canContain +
                '}';
    }
}
