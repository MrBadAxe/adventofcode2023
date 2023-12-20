import java.util.List;
import java.util.ArrayList;

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
  private static void setLens(ArrayList<ArrayList<String>> boxes, String label, int power){
    int boxNum = hash(label);
    String newLens = label + " " + power;
    boolean found = false;
    ArrayList<String> box = boxes.get(boxNum);
    for(int k=0;k<box.size()&&!found;k++){
      String lens = box.get(k);
      if(lens.split("\s")[0].equals(label)){
        box.set(k,newLens);
        found = true;
      }
    }
    if(!found){
      box.add(newLens);
    }
  }
  private static void removeLens(ArrayList<ArrayList<String>> boxes, String label){
    int boxNum = hash(label);
    boolean found = false;
    ArrayList<String> box = boxes.get(boxNum);
    for(int k=0;k<box.size()&&!found;k++){
      String lens = box.get(k);
      if(lens.split("\s")[0].equals(label)){
        box.remove(k);
        found = true;
      }
    }
  }
  private static int getFocusingPower(ArrayList<String> box){
    int total = 0;
    for(int k=0;k<box.size();k++){
      total += ((k+1) * Integer.parseInt(box.get(k).split("\s")[1]));
    }
    return total;
  }
  private static int getTotalFocusingPower(ArrayList<ArrayList<String>> boxes){
    int total = 0;
    for(int k=0;k<boxes.size();k++){
      total += ((k+1) * getFocusingPower(boxes.get(k)));
    }
    return total;
  }

  public static String getPart02(List<String> input){
    int total = 0;
    String[] tokens = input.get(0).split(",");
    ArrayList<ArrayList<String>> boxes = new ArrayList<ArrayList<String>>();
    for(int box=0;box<256;box++){
      boxes.add(new ArrayList<String>());
    }
    for(int k=0;k<tokens.length;k++){
      if(tokens[k].charAt(tokens[k].length()-1) == '-'){
        String target = tokens[k].substring(0,tokens[k].length()-1);
        removeLens(boxes,target);
      }else{
        String[] tokenSplit = tokens[k].split("=");
        String target = tokenSplit[0];
        int value = Integer.parseInt(tokenSplit[1]);
        setLens(boxes,target,value);
      }
    }
    return Integer.toString(getTotalFocusingPower(boxes));
  }
}
