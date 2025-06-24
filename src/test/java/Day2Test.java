package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import main.java.Day2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day2Test {
  Day2 day2 = new Day2();
  private List<String> list = new ArrayList<>();

  @BeforeEach
  void setUp() {
    try {
      list = Files.readAllLines(Paths.get("src/test/resources/day-2-test.txt"));
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }

  @Test
  void testPart1() {
    assertEquals(2, day2.part1(list));
  }

  @Test
  void testPart2() {
    assertEquals(4, day2.part2(list));
  }
}