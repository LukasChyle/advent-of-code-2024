import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
  public int part1(List<String> list) {
    Map<String, List<Integer>> map = splitList(list);
    List<Integer> leftList = map.get("left");
    List<Integer> rightList = map.get("right");
    Collections.sort(leftList);
    Collections.sort(rightList);
    int totalDiff = 0;
    for (int i = 0; i < leftList.size(); i++) {
      int left = leftList.get(i);
      int right = rightList.get(i);
      totalDiff += left >= right ? left - right : right - left;
    }
    return totalDiff;
  }

  public int part2(List<String> list) {
    Map<String, List<Integer>> map = splitList(list);
    List<Integer> leftList = map.get("left");
    List<Integer> rightList = map.get("right");
    int totalSum = 0;
    for (Integer i : leftList) {
      int count = Collections.frequency(rightList, i);
      totalSum += count * i;
    }
    return totalSum;
  }

  private Map<String, List<Integer>> splitList(List<String> list) {
    Map<String, List<Integer>> map = new HashMap<>();
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();
    for (String s : list) {
      if (s.trim().isEmpty()) {
        continue;
      }
      String[] split = s.trim().split("\\s+");
      leftList.add(Integer.parseInt(split[0]));
      rightList.add(Integer.parseInt(split[1]));
    }
    map.put("left", leftList);
    map.put("right", rightList);
    return map;
  }

  public static void main(String[] args) {
    Day1 day1 = new Day1();
    try {
      List<String> list = Files.readAllLines(Paths.get("resources/day-1.txt"));
      System.out.println("part 1: " + day1.part1(list));
      System.out.println("part 2: " + day1.part2(list));
    } catch (IOException e) {
      e.printStackTrace(System.out);
    }
  }
}
