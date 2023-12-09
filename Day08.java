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

  private static int traverse(HashMap<String,LRNode> map, String directions){
    int index = 0;
    int steps = 0;
    LRNode position = map.get("AAA");
    while(!position.getName().equals("ZZZ")){
      //System.out.println(position.getName());
      char nextDirection = directions.charAt(index);
      LRNode nextPosition = (nextDirection == 'L' ? position.getLeft() : position.getRight());
      position = nextPosition;
      steps++;
      index = (index + 1) % directions.length();
    }
    return steps;
  }

  public static String getPart01(List<String> input){
    String directions = input.remove(0);
    String blank = input.remove(0);
    HashMap<String,LRNode> map = buildMap(input);
    int steps = traverse(map, directions);
    return Integer.toString(steps);
  }
}
