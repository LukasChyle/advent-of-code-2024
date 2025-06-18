import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day1Test {
  Day1 day1 = new Day1();
  private List<String> list = new ArrayList<>();

  @BeforeEach
  void setUp() {
    try {
      list = Files.readAllLines(Paths.get("resources/day-1-test.txt"));
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }

  @Test
  void testPart1() {
    assertEquals(11, day1.part1(list));
  }

  @Test
  void testPart2() {
    assertEquals(31, day1.part2(list));
  }
}