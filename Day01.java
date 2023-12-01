import java.util.List;

public class Day01{

  public static int getCalibrationValue(String str){
    String digitsOnly = str.replaceAll("[a-z]","");
    String first = digitsOnly.substring(0,1);
    String last = digitsOnly.substring(digitsOnly.length()-1);
    return Integer.parseInt(first + last);
  }
  public static String getPart01(List<String> input){
    int total = 0;
    for(String str : input){
      int calibrationValue = getCalibrationValue(str);
      //System.out.println(str + ": " + calibrationValue);
      total += calibrationValue;
    }
    return Integer.toString(total);
  }
}
