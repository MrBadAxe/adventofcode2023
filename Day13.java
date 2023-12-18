import java.util.List;

public class Day13{
  private static LavaGrid parseLavaGrid(List<String> input, int start, int end){
    int height = (end - start);
    //System.out.println(height);
    LavaGrid grid = new LavaGrid(height, input.get(start).length(),'.');
    for(int row=0;row<grid.getHeight();row++){
      String line = input.get(row+start);
      //System.out.println(line);
      for(int col=0;col<grid.getWidth();col++){
        grid.set(row,col,line.charAt(col));
      }
    }
    return grid;
  }
  public static int calculateGridScore(LavaGrid grid){
    int h = grid.findReflectionH();
    int v = grid.findReflectionV();
    return ((h+1)*100 + (v+1));
  }
  public static String getPart01(List<String> input){
    int total = 0;

    int start=0;
    int end = start;
    while(end < input.size()){
      while(end < input.size() && !input.get(end).equals("")){
        end++;
      }
      System.out.println(start + " " + end);
      LavaGrid grid = parseLavaGrid(input,start,end);
      System.out.println(grid.toString());
      System.out.println(calculateGridScore(grid));
      total += calculateGridScore(grid);

      start = end+1;
      end = start;
    }

    return Integer.toString(total);
  }
}
