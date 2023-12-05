import java.util.List;
import java.util.HashSet;
public class Day04{

  public static int getCardMatchingValuesCount(String str){
    String card = str.split(":")[1];
    String[] firstHalf = card.split("\\|")[0].strip().split(" ");
    HashSet<Integer> winningNumbers = new HashSet<Integer>();
    for(int k=0;k<firstHalf.length;k++){
      if(firstHalf[k] != ""){
        winningNumbers.add(Integer.parseInt(firstHalf[k]));
      }
    }
    String[] secondHalf = card.split("\\|")[1].strip().split(" ");
    int numbersYouHave = 0;
    for(String num : secondHalf){
      if(num != "" && winningNumbers.contains(Integer.parseInt(num))){
        numbersYouHave++;
      }
    }
    return numbersYouHave;
  }

  public static String getPart01(List<String> input){
    int total = 0;
    for(String str : input){
      int matches = getCardMatchingValuesCount(str);
      total += (matches == 0 ? 0 : (int)Math.pow(2,matches-1));
    }
    return Integer.toString(total);
  }

  public static String getPart02(List<String> input){
    int total = 0;
    int[] cardCounts = new int[input.size()];
    for(int k=0;k<input.size();k++){
      cardCounts[k] = 1;
    }
    for(int k=0;k<input.size();k++){
      int value = getCardMatchingValuesCount(input.get(k));
      for(int j=0;j<value;j++){
        cardCounts[k+j+1]+=cardCounts[k];
      }
    }
    for(int k=0;k<input.size();k++){
      total += cardCounts[k];
    }
    return Integer.toString(total);
  }
}
