import java.util.List;

public class Day21{
  private static GardenMap parseGardenMap(List<String> input){
    int height = input.size();
    int width = input.get(0).length();
    GardenMap grid = new GardenMap(height,width);
    for(int row=0;row<grid.getHeight();row++){
      String line = input.get(row);
      for(int col=0;col<grid.getWidth();col++){
        grid.set(row,col,line.charAt(col));
      }
    }
    return grid;
  }
  private static int countStepLocations(GardenMap grid){
    int total = 0;
    for(int row=0;row<grid.getHeight();row++){
      for(int col=0;col<grid.getWidth();col++){
        if(grid.get(row,col) == 'O'){
          total++;
        }
      }
    }
    return total;
  }
  public static String getPart01(List<String> input){
    int total = 0;
    GardenMap grid = parseGardenMap(input);
    System.out.println(grid);
    System.out.println(grid.getStart());
    for(int k=0;k<64;k++){
      grid = grid.step();
      System.out.println(grid);
    }
    return Integer.toString(countStepLocations(grid));
  }
}
