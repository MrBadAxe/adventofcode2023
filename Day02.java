import java.util.List;

public class Day02{
  public static final int MAX_RED = 12;
  public static final int MAX_GREEN = 13;
  public static final int MAX_BLUE = 14;

  public static String getPart01(List<String> input){
    String[] games = new String[input.size()];
    boolean[] gameIsValid = new boolean[input.size()];
    for(int k=0;k<games.length;k++){
      games[k] = input.get(k);
      gameIsValid[k] = true;
      String sequence = games[k].split(":")[1];
      String[] shows = sequence.split(";");
      for(String show : shows){
        String[] cubeCounts = show.split(",");
        for(String cubeCount : cubeCounts){
          String[] tokens = cubeCount.split(" ");
          int count = Integer.parseInt(tokens[1]);
          String color = tokens[2];
          if(color.equals("red") && count > MAX_RED){ gameIsValid[k] = false; }
          if(color.equals("green") && count > MAX_GREEN){ gameIsValid[k] = false; }
          if(color.equals("blue") && count > MAX_BLUE){ gameIsValid[k] = false; }
        }
      }
    }
    int total = 0;
    for(int k=0;k<games.length;k++){
      if(gameIsValid[k]){
        total += (k+1);
      }
    }
    return Integer.toString(total);
  }

  public static String getPart02(List<String> input){
    String[] games = new String[input.size()];
    int total = 0;
    for(int k=0;k<games.length;k++){
      games[k] = input.get(k);
      String sequence = games[k].split(":")[1];
      String[] shows = sequence.split(";");
      int minRed = 0;
      int minGreen = 0;
      int minBlue = 0;
      for(String show : shows){
        String[] cubeCounts = show.split(",");
        for(String cubeCount : cubeCounts){
          String[] tokens = cubeCount.split(" ");
          int count = Integer.parseInt(tokens[1]);
          String color = tokens[2];
          if(color.equals("red")) { minRed = Math.max(minRed, count); }
          if(color.equals("green")) { minGreen = Math.max(minGreen, count); }
          if(color.equals("blue")) { minBlue = Math.max(minBlue, count); }
        }
      }
      int power = minRed * minGreen * minBlue;
      total += power;
    }
    return Integer.toString(total);
  }
}
