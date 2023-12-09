import java.util.List;
import java.util.HashMap;
public class Day08{
  private static HashMap<String,LRNode> buildMap(List<String> input){
    HashMap<String,LRNode> map = new HashMap<String,LRNode>();

    for(String line : input){
      String[] splitOnEquals = line.split("=");
      String name = splitOnEquals[0].strip();
      map.put(name, new LRNode(name));
    }
    for(String line : input){
      String[] splitOnEquals = line.split("=");
      String name = splitOnEquals[0].strip();
      String[] neighbors = splitOnEquals[1].strip().replaceAll("[\\(\\),]","").split("\s");
      LRNode left = map.get(neighbors[0]);
      LRNode right = map.get(neighbors[1]);
      map.get(name).setLeft(left);
      map.get(name).setRight(right);
    }

    return map;
  }

  private static int traverse(HashMap<String,LRNode> map, String directions, String start, String end){
    int index = 0;
    int steps = 0;
    LRNode position = map.get(start);
    while(!position.getName().matches(end)){
      char nextDirection = directions.charAt(index);
      LRNode nextPosition = (nextDirection == 'L' ? position.getLeft() : position.getRight());
      position = nextPosition;
      steps++;
      index = (index + 1) % directions.length();
    }
    return steps;
  }

  private static long ghostTraverse(HashMap<String,LRNode> map, String directions){
    int index = 0;
    long steps = 0;

    steps = 1;
    for(String nodeName : map.keySet()){
      if(nodeName.charAt(2) == 'A'){
        steps = lcm(steps, traverse(map, directions, nodeName,"..Z"));
      }
    }
    return steps;
  }
  private static long gcd(long a, long b){
    return (b == 0 ? a : gcd(b,a%b));
  }
  private static long lcm(long a, long b){
    return Math.abs(a*b)/gcd(a,b);
  }

  public static String getPart01(List<String> input){
    String directions = input.remove(0);
    String blank = input.remove(0);
    HashMap<String,LRNode> map = buildMap(input);
    int steps = traverse(map, directions, "AAA", "ZZZ");
    return Integer.toString(steps);
  }
  public static String getPart02(List<String> input){
    String directions = input.remove(0);
    String blank = input.remove(0);
    HashMap<String,LRNode> map = buildMap(input);
    long steps = ghostTraverse(map, directions);
    return Long.toString(steps);
  }
}
