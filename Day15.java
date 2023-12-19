import java.util.List;

public class Day15{
  public static int step(int i, char c){
    return (((i + (int)c) * 17) % 256);
  }
  public static int hash(String str){
    int value = 0;
    for(int k=0;k<str.length();k++){
      value = step(value, str.charAt(k));
    }
    return value;
  }
  public static String getPart01(List<String> input){
    int total = 0;
    String[] tokens = input.get(0).split(",");
    for(int k=0;k<tokens.length;k++){
      total += hash(tokens[k]);
    }
    return Integer.toString(total);
  }
}
