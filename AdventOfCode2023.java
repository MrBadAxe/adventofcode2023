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
    System.out.println("Day 01 Part 01: " + Day01.getPart01(readInput("./input_day01.txt")));
    System.out.println("Day 01 Part 02: " + Day01.getPart02(readInput("./input_day01.txt")));
  }
}
