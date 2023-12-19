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
    int z = 0;

    List<Integer> h = grid.findReflectionsH();
    z += (h.size() > 0 ? ((h.get(0)+1)*100) : 0);

    List<Integer> v = grid.findReflectionsV();
    z += (v.size() > 0 ? (v.get(0)+1) : 0);

    return z;
  }
  public static int calculateAlternateGridScore(LavaGrid before, LavaGrid after){
    int z = 0;

    List<Integer> hBefore = before.findReflectionsH();
    List<Integer> hAfter = after.findReflectionsH();
    if(hBefore.size() < hAfter.size()){
      hAfter.removeAll(hBefore);
    }
    z += (hAfter.size() > 0 ? (hAfter.get(0) + 1)*100 : 0);
    //if(z != 0){ return z; }

    List<Integer> vBefore = before.findReflectionsV();
    List<Integer> vAfter = after.findReflectionsV();
    if(vBefore.size() < vAfter.size()){
      vAfter.removeAll(vBefore);
    }
    z += (vAfter.size() > 0 ? (vAfter.get(0) + 1) : 0);

    return z;
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
