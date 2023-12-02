import java.util.List;

public class Day01 implements AdventOfCodeDay{

  public static int getCalibrationValue(String str){
    String digitsOnly = str.replaceAll("[a-z]","");
    String first = digitsOnly.substring(0,1);
    String last = digitsOnly.substring(digitsOnly.length()-1);
    return Integer.parseInt(first + last);
  }

  public static int firstDigit(String str){
    for(int k=0;k<str.length();k++){
      switch(str.charAt(k)){
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          return str.charAt(k)-'0';
        case 'o':
          if(k+3<str.length() && str.substring(k,k+3).equals("one")){ return 1; }
          break;
        case 't':
          if(k+3<str.length() && str.substring(k,k+3).equals("two")){ return 2; }
          if(k+5<str.length() && str.substring(k,k+5).equals("three")){ return 3; }
          break;
        case 'f':
          if(k+4<str.length() && str.substring(k,k+4).equals("four")){ return 4; }
          if(k+4<str.length() && str.substring(k,k+4).equals("five")){ return 5; }
          break;
        case 's':
          if(k+3<str.length() && str.substring(k,k+3).equals("six")){ return 6; }
          if(k+5<str.length() && str.substring(k,k+5).equals("seven")){ return 7; }
          break;
        case 'e':
          if(k+5<str.length() && str.substring(k,k+5).equals("eight")){ return 8; }
          break;
        case 'n':
          if(k+4<str.length() && str.substring(k,k+4).equals("nine")){ return 9; }
          break;
        default:
      }
    }
    return 0;
  }

  public static int lastDigit(String str){
    for(int k=str.length()-1;k>=0;k--){
      switch(str.charAt(k)){
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          return str.charAt(k)-'0';
        case 'e':
          if(k-2>=0 && str.substring(k-2,k+1).equals("one")){ return 1; }
          if(k-4>=0 && str.substring(k-4,k+1).equals("three")){ return 3; }
          if(k-3>=0 && str.substring(k-3,k+1).equals("five")){ return 5; }
          if(k-3>=0 && str.substring(k-3,k+1).equals("nine")){ return 9; }
          break;
        case 'o':
          if(k-2>=0 && str.substring(k-2,k+1).equals("two")){ return 2; }
          break;
        case 'r':
          if(k-3>=0 && str.substring(k-3,k+1).equals("four")){ return 4; }
          break;
        case 'x':
          if(k-2>=0 && str.substring(k-2,k+1).equals("six")){ return 6; }
          break;
        case 'n':
          if(k-4>=0 && str.substring(k-4,k+1).equals("seven")){ return 7; }
          break;
        case 't':
          if(k-4>=0 && str.substring(k-4,k+1).equals("eight")){ return 8; }
          break;
        default:
      }
    }
    return 0;
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

  public static String getPart02(List<String> input){
    int total = 0;
    for(String str : input){
      System.out.println(str + ": " + firstDigit(str) + lastDigit(str));
      int calibrationValue = (firstDigit(str) * 10) + lastDigit(str);
      total += calibrationValue;
    }
    return Integer.toString(total);
  }
}
