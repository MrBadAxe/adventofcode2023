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
  public static int calculateEnergyLevel(CharGrid grid){
    int total = 0;
    for(int row=0;row<grid.getHeight();row++){
      for(int col=0;col<grid.getWidth();col++){
        total += grid.get(row,col) == '#' ? 1 : 0;
      }
    }
    return total;
  }
  public static String getPart01(List<String> input){
    MirrorGrid grid = parseMirrorGrid(input);
    CharGrid energized = grid.getEnergizeMap(new Beam(0,0,0,1));
    int energyLevel = calculateEnergyLevel(energized);
    return Integer.toString(energyLevel);
  }
  public static String getPart02(List<String> input){
    MirrorGrid grid = parseMirrorGrid(input);
    int best = 0;
    for(int row=0;row<grid.getHeight();row++){
      CharGrid fromLeft = grid.getEnergizeMap(new Beam(row,0,0,1));
      CharGrid fromRight = grid.getEnergizeMap(new Beam(row,grid.getWidth()-1,0,-1));
      best = Math.max(best, Math.max(calculateEnergyLevel(fromLeft), calculateEnergyLevel(fromRight)));
    }
    for(int col=0;col<grid.getWidth();col++){
      CharGrid fromTop = grid.getEnergizeMap(new Beam(0,col,1,0));
      CharGrid fromBottom = grid.getEnergizeMap(new Beam(grid.getHeight()-1,col,-1,0));
      best = Math.max(best, Math.max(calculateEnergyLevel(fromTop), calculateEnergyLevel(fromBottom)));
    }
    return Integer.toString(best);
  }
}
