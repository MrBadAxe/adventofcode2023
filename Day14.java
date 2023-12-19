import java.util.List;
import java.util.HashMap;

public class Day14{
  public static ParabolicDish parseParabolicDish(List<String> input){
    int height = input.size();
    int width = input.get(0).length();
    ParabolicDish dish = new ParabolicDish(height, width);
    for(int row=0;row<dish.getHeight();row++){
      String line = input.get(row);
      for(int col=0;col<dish.getWidth();col++){
        dish.set(row,col,line.charAt(col));
      }
    }
    return dish;
  }
  public static String getPart01(List<String> input){
    ParabolicDish dish = parseParabolicDish(input);
    dish = dish.tiltNorth();
    return Integer.toString(dish.getLoad());
  }

  public static String getPart02(List<String> input){
    String dish = parseParabolicDish(input).toString();
    HashMap<String,String> north = new HashMap<String,String>();
    HashMap<String,String> west = new HashMap<String,String>();
    HashMap<String,String> south = new HashMap<String,String>();
    HashMap<String,String> east = new HashMap<String,String>();

    for(long l = 0;l < 1_000_000_000;l++){
      if(north.get(dish) == null){
        north.put(dish,ParabolicDish.fromString(dish).tiltNorth().toString());
      }
      dish = north.get(dish);

      if(west.get(dish) == null){
        west.put(dish,ParabolicDish.fromString(dish).tiltWest().toString());
      }
      dish = west.get(dish);

      if(south.get(dish) == null){
        south.put(dish,ParabolicDish.fromString(dish).tiltSouth().toString());
      }
      dish = south.get(dish);

      if(east.get(dish) == null){
        east.put(dish,ParabolicDish.fromString(dish).tiltEast().toString());
      }
      dish = east.get(dish);
    }
    return Integer.toString(ParabolicDish.fromString(dish).getLoad());
  }
}
