import java.util.List;

public class Day06{
  public static String getPart01(List<String> input){
    String[] raceMaxTimes = input.get(0).split(":")[1].strip().split("\s+");
    String[] raceBestDistances = input.get(1).split(":")[1].strip().split("\s+");

    int product = 1;
    for(int k=0;k<raceMaxTimes.length;k++){
      int lb = Integer.parseInt(raceMaxTimes[k])/2;
      int ub = Integer.parseInt(raceMaxTimes[k])-lb;
      while((lb * ub) > Integer.parseInt(raceBestDistances[k])){
        lb--;
        ub++;
      }
      lb++;
      ub--;
      product *= (ub-lb+1);
    }
    return Integer.toString(product);
  }

  public static String getPart02(List<String> input){
    String raceMaxTime = input.get(0).replaceAll("\s","").split(":")[1];
    String raceBestDistance = input.get(1).replaceAll("\s","").split(":")[1];

    long lb = Long.parseLong(raceMaxTime)/2;
    long ub = Long.parseLong(raceMaxTime)-lb;
    while((lb * ub) > Long.parseLong(raceBestDistance)){
      lb--;
      ub++;
    }
    lb++;
    ub--;

    return Long.toString(ub-lb+1);
  }
}
