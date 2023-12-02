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
      System.out.println(games[k]);
      String sequence = games[k].split(":")[1];
      String[] shows = sequence.split(";");
      for(String show : shows){
        System.out.println(show);
        String[] cubeCounts = show.split(",");
        for(String cubeCount : cubeCounts){
          System.out.println(cubeCount);
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
    return "0";
  }
}
