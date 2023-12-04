import java.util.List;
import java.util.HashSet;
public class Day04{

  public static String getPart01(List<String> input){
    int total = 0;
    for(String str : input){
      String card = str.split(":")[1];
      String[] firstHalf = card.split("\\|")[0].strip().split(" ");
      HashSet<Integer> winningNumbers = new HashSet<Integer>();
      for(int k=0;k<firstHalf.length;k++){
        //System.out.println(firstHalf[k]);
        if(firstHalf[k] != ""){
          winningNumbers.add(Integer.parseInt(firstHalf[k]));
        }
      }
      String[] secondHalf = card.split("\\|")[1].strip().split(" ");
      int numbersYouHave = 0;
      for(String num : secondHalf){
        System.out.println(num);
        if(num != "" && winningNumbers.contains(Integer.parseInt(num))){
          numbersYouHave++;
        }
      }
      System.out.println(">"+numbersYouHave);
      total += (numbersYouHave == 0 ? 0 : Math.pow(2,(numbersYouHave-1)));
    }
    return Integer.toString(total);
  }
}
