import java.util.List;

public class Day16{
  public static MirrorGrid parseMirrorGrid(List<String> input){
    int height = input.size();
    int width = input.get(0).length();
    MirrorGrid grid = new MirrorGrid(height,width);
    for(int row=0;row<grid.getHeight();row++){
      String line = input.get(row);
      for(int col=0;col<grid.getWidth();col++){
        grid.set(row,col,line.charAt(col));
      }
    }
    return grid;
  }
  public static String getPart01(List<String> input){
    int total = 0;
    MirrorGrid grid = parseMirrorGrid(input);
    System.out.println(grid.toString());

    CharGrid energized = grid.getEnergizeMap(new Beam(0,0,0,1));
    System.out.println(energized.toString());
    for(int row=0;row<energized.getHeight();row++){
      for(int col=0;col<energized.getWidth();col++){
        total += energized.get(row,col) == '#' ? 1 : 0;
      }
    }
    return Integer.toString(total);
  }
}
