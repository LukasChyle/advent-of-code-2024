package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

  public int part1(List<String> list) {
    List<List<Integer>> levelsList = splitLinesToLevels(list);
    int secureReports = 0;
    for (List<Integer> s : levelsList) {
      boolean successIncreasing = part1Validator(s, true);
      boolean successDecreasing = part1Validator(s, false);
      if (successIncreasing || successDecreasing) {
        secureReports++;
      }
    }
    return secureReports;
  }

  private boolean part1Validator(List<Integer> levels, boolean increasing) {
    for (int i = 1; i < levels.size(); i++) {
      int levelDifference = levels.get(i) - levels.get(i - 1);
      if ((increasing && levelDifference <= 0) || (!increasing && levelDifference >= 0) || (levelDifference > 3 || levelDifference < -3)) {
        return false;
      }
    }
    return true;
  }

  public int part2(List<String> list) {
    List<List<Integer>> levelsList = splitLinesToLevels(list);
    int secureReports = 0;
    for (List<Integer> s : levelsList) {
      boolean successIncreasing = part2Validator(s, 0, true);
      boolean successDecreasing = part2Validator(s, 0, false);
      if (successIncreasing || successDecreasing) {
        secureReports++;
      }
    }
    return secureReports;
  }

  private boolean part2Validator(List<Integer> levels, int numberOfBadLevels, boolean increasing) {
    for (int i = 1; i < levels.size(); i++) {
      int levelDifference = levels.get(i) - levels.get(i - 1);
      if ((increasing && levelDifference <= 0) || (!increasing && levelDifference >= 0) || (levelDifference > 3 || levelDifference < -3)) {
        if (numberOfBadLevels == 1) {
          return false;
        }
        List<Integer> levelsCopyWithoutPrevious = new ArrayList<>(levels);
        levelsCopyWithoutPrevious.remove(i - 1);
        boolean validWithoutPrev = part2Validator(levelsCopyWithoutPrevious, 1, increasing);
        List<Integer> levelsCopyWithoutCurrent = new ArrayList<>(levels);
        levelsCopyWithoutCurrent.remove(i);
        boolean validWithoutCurrent = part2Validator(levelsCopyWithoutCurrent, 1, increasing);
        return validWithoutPrev || validWithoutCurrent;
      }
    }
    return true;
  }

  private List<List<Integer>> splitLinesToLevels(List<String> list) {
    List<List<Integer>> levelsList = new ArrayList<>();
    for (String s : list) {
      if (s.trim().isEmpty()) {
        continue;
      }
      List<Integer> levels = Arrays.stream(s.trim().split("\\s+"))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
      levelsList.add(levels);
    }
    return levelsList;
  }

  public static void main(String[] args) {
    Day2 day2 = new Day2();
    try {
      List<String> list = Files.readAllLines(Paths.get("src/main/resources/day-2.txt"));
      System.out.println("part 1: " + day2.part1(list));
      System.out.println("part 2: " + day2.part2(list));
    } catch (IOException e) {
      e.printStackTrace(System.out);
    }
  }
}
