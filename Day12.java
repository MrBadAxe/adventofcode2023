import java.util.List;
import java.util.ArrayList;

public class Day12{
  public static int[] parseSpringList(String str){
    String[] split = str.split(",");
    int[] z = new int[split.length];
    for(int k=0;k<z.length;k++){
      z[k] = Integer.parseInt(split[k]);
    }
    return z;
  }
  public static List<String> fillFirstWildcard(String str){
    ArrayList<String> z = new ArrayList<String>();
    String operational = str.replaceFirst("\\?",".");
    String damaged = str.replaceFirst ("\\?","#");
    z.add(operational);
    z.add(damaged);
    return z;
  }
  public static List<String> expandWildcards(String str){
    ArrayList<String> z = new ArrayList<String>();
    z.addAll(fillFirstWildcard(str));
    String next = z.get(0);
    while(next.indexOf('?') >= 0){
      next = z.remove(0);
      z.addAll(fillFirstWildcard(next));
      next = z.get(0);
    }
    return z;
  }
  public static int[] parseGrouping(String str){
    String[] groups = str.replaceAll("\\."," ").strip().split("\s+");
    int[] z = new int[groups.length];
    for(int k=0;k<z.length;k++){
      z[k] = groups[k].length();
    }
    return z;
  }
  public static boolean equalGrouping(int[] a, int[] b){
    if(a.length != b.length){
      return false;
    }
    for(int k=0;k<a.length;k++){
      if(a[k] != b[k]){
        return false;
      }
    }
    return true;
  }
  public static String getPart01(List<String> input){
    int total = 0;
    for(String line : input){
      String[] split = line.split("\s");
      String arrangement = split[0];
      String groupings = split[1];
      int[] springList = parseSpringList(groupings);

      System.out.println(arrangement);
      for(String str : expandWildcards(arrangement)){
        int[] group = parseGrouping(str);
        if(equalGrouping(springList,group)){
          total++;
        }
      }
    }
    return Integer.toString(total);
  }
}
