import java.util.List;

public class Day06{
  private static long getRaceNumWins(long time, long dist){
    long lb = time/2;
    long ub = time-lb;
    while((lb * ub) > dist){
      lb--;
      ub++;
    }
    lb++;
    ub--;
    return (ub-lb+1);
  }

  public static String getPart01(List<String> input){
    String[] raceMaxTimes = input.get(0).split(":")[1].strip().split("\s+");
    String[] raceBestDistances = input.get(1).split(":")[1].strip().split("\s+");

    int product = 1;
    for(int k=0;k<raceMaxTimes.length;k++){
      product *= getRaceNumWins(Long.parseLong(raceMaxTimes[k]),Long.parseLong(raceBestDistances[k]));
    }
    return Integer.toString(product);
  }

  public static String getPart02(List<String> input){
    String raceMaxTime = input.get(0).replaceAll("\s","").split(":")[1];
    String raceBestDistance = input.get(1).replaceAll("\s","").split(":")[1];
    return Long.toString(getRaceNumWins(Long.parseLong(raceMaxTime),Long.parseLong(raceBestDistance)));
  }
}
