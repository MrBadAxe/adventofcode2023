import java.util.List;

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
    //System.out.println(dish.toString());
    dish.tiltNorth();
    //System.out.println(dish.toString());
    return Integer.toString(dish.getLoad());
  }
}
