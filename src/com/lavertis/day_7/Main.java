package com.lavertis.day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/com/lavertis/day_7/input.txt"));
        List<Bag> bags = getBagsFromLine(lines);
        System.out.println("Part 1: " + part1(bags));
        System.out.println("Part 2: " + part2(bags));
    }

    public static long part2(List<Bag> bags) {
        List<Bag> shgmc = new ArrayList<>(getBagsWhichShinyGoldMustContain(bags));
        return policzDlaJednej(shgmc, "shiny gold") - 1;
    }

    public static long policzDlaJednej(List<Bag> bags, String colour) {
        int counter = 1;
        for (Bag b : bags) {
            if (b.getColour().equals(colour)) {
                for (int j = 0; j < b.getCanContain().size(); j++) {
                    String currentColour = b.getCanContain().get(j);
                    Integer currentColourNumber = b.getNumberOfColour().get(j);
                    counter += currentColourNumber * policzDlaJednej(bags, currentColour);
                }
            }
        }
        return counter;
    }

    public static Set<Bag> getBagsWhichShinyGoldMustContain(List<Bag> bags) {
        Set<Bag> currentBags = bags.stream().filter(bag -> bag.getColour().equals("shiny gold")).collect(Collectors.toSet());
        Set<Bag> temp = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (Bag currentBag : currentBags)
                for (Bag bag : bags)
                    if (currentBag.getCanContain().contains(bag.getColour()))
                        temp.add(bag);
            currentBags.addAll(temp);
        }
        currentBags.removeIf(b -> b.getCanContain().isEmpty());
        return currentBags;
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
            String coloursLine = Arrays.toString(s.split(" \\d+ "))
                    .replaceAll(" bags", "")
                    .replaceAll(" bag", "")
                    .replaceAll("\\.", "")
                    .replaceAll(",, ", ",")
                    .replaceAll(", ", ",")
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");
            List<Integer> numberOfColour = new ArrayList<>();
            for (int i = 0; i < s.length(); i++)
                if (Character.isDigit(s.charAt(i)))
                    numberOfColour.add(Character.getNumericValue(s.charAt(i)));
            String ownColour = coloursLine.split(" contain")[0];
            String[] commaSplit = coloursLine.split(",");
            List<String> canContain = new ArrayList<>(Arrays.asList(commaSplit).subList(1, commaSplit.length));
            Bag bag = new Bag(ownColour, canContain, numberOfColour);
            bags.add(bag);
        }
        return bags;
    }
}

class Bag {
    private final String colour;
    private final List<String> canContain;
    private final List<Integer> numberOfColour;

    public Bag(String name, List<String> canContain, List<Integer> numberOfColour) {
        this.colour = name;
        this.canContain = canContain;
        this.numberOfColour = numberOfColour;
    }

    public String getColour() {
        return colour;
    }

    public List<String> getCanContain() {
        return canContain;
    }

    public List<Integer> getNumberOfColour() {
        return numberOfColour;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "colour='" + colour + '\'' +
                ", canContain=" + canContain +
                ", numberOfColour=" + numberOfColour +
                '}';
    }
}
