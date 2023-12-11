import java.util.List;
import java.util.ArrayList;

public class Day10{
  private static PipeGrid parsePipeMap(List<String> input){
    PipeGrid map = new PipeGrid(input.size(),input.get(0).length());
    for(int row=0;row<input.size();row++){
      String line = input.get(row);
      for(int col=0;col<line.length();col++){
        map.set(row,col,line.charAt(col));
      }
    }
    return map;
  }

  public static String getPart01(List<String> input){
    PipeGrid map = parsePipeMap(input);
    List<Point> path = map.getAllPointsOnLoop();
    return Integer.toString((path.size()-1)/2);
  }
  public static String getPart02(List<String> input){
    PipeGrid map = parsePipeMap(input);
    int total = map.countInsideLoop();
    return Integer.toString(total);
  }
}
