import java.util.List;
import java.util.ArrayList;
public class Day18{
  public static ArrayList<Point> buildPointsList(List<String> input){
    ArrayList<Point> perimeterPoints = new ArrayList<Point>();
    perimeterPoints.add(new Point(0,0));
    for(String line : input){
      String[] tokens = line.split("\s");
      String direction = tokens[0];
      int distance = Integer.parseInt(tokens[1]);
      long newX = perimeterPoints.get(perimeterPoints.size()-1).getX();
      long newY = perimeterPoints.get(perimeterPoints.size()-1).getY();
      switch(direction.charAt(0)){
        case 'U': newX -= distance; break;
        case 'D': newX += distance; break;
        case 'L': newY -= distance; break;
        case 'R': newY += distance; break;
      }
      perimeterPoints.add(new Point(newX,newY));
    }
    return perimeterPoints;
  }
  public static String getPart01(List<String> input){
    ArrayList<Point> perimeterPoints = buildPointsList(input);
    LavaTrench trench = new LavaTrench(perimeterPoints);
    trench.floodFill();
    return Long.toString(trench.getVolume());
  }
  public static long shoelaceFormula(List<Point> points){
    long total = 0;
    for(int k=0;k<points.size()-1;k++){
      Point p1 = points.get(k);
      Point p2 = points.get(k+1);
      long a = p1.getX();
      long b = p2.getX();
      long c = p1.getY();
      long d = p2.getY();
      long determinant = a*d - b*c;
      //System.out.println((a*d) + " - " + (b*c) + " = " + determinant);
      total += determinant;
    }
    return total;
  }
  public static long totalTaxicabDistance(List<Point> points){
    long total = 0;
    for(int k=0;k<points.size()-1;k++){
      Point p1 = points.get(k);
      Point p2 = points.get(k+1);
      total += p1.taxicabDistance(p2);
    }
    return total;
  }
  public static ArrayList<Point> buildPointsListHex(List<String> input){
    ArrayList<Point> perimeterPoints = new ArrayList<Point>();
    perimeterPoints.add(new Point(0,0));
    for(String line : input){
      String[] tokens = line.split("\s");
      String hexval = tokens[2];
      long distance = Long.parseLong(hexval.substring(2,7),16);
      String direction = "" + Character.toString("RDLU".charAt(hexval.charAt(7)-'0'));
      long newX = perimeterPoints.get(perimeterPoints.size()-1).getX();
      long newY = perimeterPoints.get(perimeterPoints.size()-1).getY();
      switch(direction.charAt(0)){
        case 'U': newX -= distance; break;
        case 'D': newX += distance; break;
        case 'L': newY -= distance; break;
        case 'R': newY += distance; break;
      }
      perimeterPoints.add(new Point(newX,newY));
    }
    return perimeterPoints;
  }
  public static String getPart02(List<String> input){
    ArrayList<Point> perimeterPoints = buildPointsListHex(input);
    long shoelace = Math.abs(shoelaceFormula(perimeterPoints)/2);
    long perimeter = totalTaxicabDistance(perimeterPoints);
    return Long.toString(shoelace + (perimeter/2) + 1);
  }
}
