import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Day07{
  public static String getPart01(List<String> input){
    ArrayList<CamelPokerHand> hands = new ArrayList<CamelPokerHand>();
    for(String line : input){
      String hand = line.split("\s")[0];
      int value = Integer.parseInt(line.split("\s")[1]);
      hands.add(new CamelPokerHand(hand,value));
    }
    Collections.sort(hands);
    int total = 0;
    for(int k=0;k<hands.size();k++){
      total += hands.get(k).getValue() * (k+1);
    }
    return Integer.toString(total);
  }
}
