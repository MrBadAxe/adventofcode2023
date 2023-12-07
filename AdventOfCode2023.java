import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode2023{
  public static List<String> readInput(String filepath){
    List<String> lines = new ArrayList<String>();
    try{
      lines = Files.readAllLines(Path.of(filepath));
    }catch(java.io.IOException e){
      System.err.println("IOException: " + e.getMessage());
    }
    return lines;
  }

  public static void main(String[] args){
    /*
    System.out.println("Day 01 Part 01: " + Day01.getPart01(readInput("./input_day01.txt")));
    System.out.println("Day 01 Part 02: " + Day01.getPart02(readInput("./input_day01.txt")));

    System.out.println("Day 02 Part 01: " + Day02.getPart01(readInput("./input_day02.txt")));
    System.out.println("Day 02 Part 02: " + Day02.getPart02(readInput("./input_day02.txt")));

    System.out.println("Day 03 Part 01: " + Day03.getPart01(readInput("./input_day03.txt")));
    System.out.println("Day 03 Part 02: " + Day03.getPart02(readInput("./input_day03.txt")));

    System.out.println("Day 04 Part 01: " + Day04.getPart01(readInput("./input_day04.txt")));
    System.out.println("Day 04 Part 02: " + Day04.getPart02(readInput("./input_day04.txt")));

    System.out.println("Day 05 Part 01: " + Day05.getPart01(readInput("./input_day05.txt")));
    System.out.println("Day 05 Part 02: " + Day05.getPart02(readInput("./input_day05.txt")));
    */

    System.out.println("Day 06 Part 01: " + Day06.getPart01(readInput("./input_day06.txt")));
    System.out.println("Day 06 Part 02: " + Day06.getPart02(readInput("./input_day06.txt")));

  }
}
