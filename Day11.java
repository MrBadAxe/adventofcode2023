import java.util.List;
import java.util.ArrayList;

public class Day11{
  private static GalaxyMap parseGalaxyMap(List<String> input){
    GalaxyMap map = new GalaxyMap(input.size(), input.get(0).length());
    for(int row=0;row<input.size();row++){
      String line = input.get(row);
      for(int col=0;col<line.length();col++){
        if(line.charAt(col) == '#'){
          map.set(row,col,'#');
        }
      }
    }
    return map;
  }
  private static ArrayList<Point> expand(ArrayList<Point> galaxies, ArrayList<Integer> emptyRows, ArrayList<Integer> emptyCols, int extraCells){
    ArrayList<Point> expanded = new ArrayList<Point>();
    for(Point p : galaxies){
      int emptyRowsBelow = 0;
      while(emptyRowsBelow < emptyRows.size() && emptyRows.get(emptyRowsBelow) < p.getX()){
        emptyRowsBelow++;
      }
      int emptyColsBelow = 0;
      while(emptyColsBelow < emptyCols.size() && emptyCols.get(emptyColsBelow) < p.getY()){
        emptyColsBelow++;
      }
      expanded.add(new Point(p.getX()+(emptyRowsBelow*extraCells),p.getY()+(emptyColsBelow*extraCells)));
    }
    return expanded;
  }
  /*
  public static String getPart01(List<String> input){
    GalaxyMap map = parseGalaxyMap(input);
    GalaxyMap map2 = map.expand(1);
    ArrayList<Point> galaxies = map2.findAllGalaxies();
    int total = 0;
    for(int k=0;k<galaxies.size()-1;k++){
      Point a = galaxies.get(k);
      for(int j=k+1;j<galaxies.size();j++){
        Point b = galaxies.get(j);
        total += a.taxicabDistance(b);
      }
    }
    return Integer.toString(total);
  }
  */
  public static String getPart01(List<String> input){
    GalaxyMap map = parseGalaxyMap(input);
    ArrayList<Point> galaxies = map.findAllGalaxies();
    ArrayList<Integer> emptyRows = map.getEmptyRows();
    ArrayList<Integer> emptyCols = map.getEmptyCols();
    System.out.println(emptyRows);
    System.out.println(emptyCols);
    ArrayList<Point> expanded = expand(galaxies,emptyRows,emptyCols,1);

    long total = 0;
    for(int k=0;k<expanded.size()-1;k++){
      Point a = expanded.get(k);
      for(int j=k+1;j<expanded.size();j++){
        Point b = expanded.get(j);
        total += a.taxicabDistance(b);
      }
    }
    return Long.toString(total);
  }
  public static String getPart02(List<String> input){
    GalaxyMap map = parseGalaxyMap(input);
    ArrayList<Point> galaxies = map.findAllGalaxies();
    ArrayList<Integer> emptyRows = map.getEmptyRows();
    ArrayList<Integer> emptyCols = map.getEmptyCols();
    ArrayList<Point> expanded = expand(galaxies,emptyRows,emptyCols,999999);

    long total = 0;
    for(int k=0;k<expanded.size()-1;k++){
      Point a = expanded.get(k);
      for(int j=k+1;j<expanded.size();j++){
        Point b = expanded.get(j);
        total += a.taxicabDistance(b);
      }
    }
    return Long.toString(total);
  }
}
