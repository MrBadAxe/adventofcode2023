import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day05{

  private static ArrayList<Long> getSeedsList(String str){
    String[] splitSeeds = str.substring(7).split(" ");
    ArrayList<Long> seeds = new ArrayList<Long>();
    for(int k=0;k<splitSeeds.length;k++){
      seeds.add(Long.parseLong(splitSeeds[k]));
    }
    return seeds;
  }

  private static LinkedList<ArrayList<MapTriple>> getMapsList(List<String> input){
    int index = 3;
    LinkedList<ArrayList<MapTriple>> mapSteps = new LinkedList<ArrayList<MapTriple>>();

    while(index < input.size()){
      ArrayList<MapTriple> mapStep = new ArrayList<MapTriple>();
      String map = input.get(index);
      while(!map.equals("")){
        String[] nums = map.split(" ");
        MapTriple mt = new MapTriple(Long.parseLong(nums[0]),Long.parseLong(nums[1]),Long.parseLong(nums[2]));
        mapStep.add(mt);
        index++;
        map = (index >= input.size()) ? "" : input.get(index);
      }
      mapSteps.add(mapStep);
      index += 2;
    }

    return mapSteps;
  }

  public static String getPart01(List<String> input){
    ArrayList<Long> seeds = getSeedsList(input.get(0));
    LinkedList<ArrayList<MapTriple>> mapSteps = getMapsList(input);

    long lowest = Long.MAX_VALUE;
    for(Long seed : seeds){
      long seedValue = seed;
      System.out.println(seedValue);
      for(ArrayList<MapTriple> mapStep : mapSteps){
        long offset = 0;
        for(MapTriple mt : mapStep){
          if(mt.inRange(seedValue)){
            offset = mt.getOffset();
          }
        }
        seedValue += offset;
      }
      System.out.println(seedValue);
      System.out.println("");
      lowest = Math.min(lowest,seedValue);
    }

    return Long.toString(lowest);
  }
}
