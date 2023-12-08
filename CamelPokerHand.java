public class CamelPokerHand implements Comparable<CamelPokerHand>{
  public static enum Rank {HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND;}

  private String hand;
  private Rank rank;
  private int value;
  private boolean hasJokers;

  public CamelPokerHand(String str, int i, boolean jokers){
    this.hand = str.toUpperCase().replaceAll("[^2-9AJKQT]","").substring(0,5);
    this.rank = calculateHandRank(jokers);
    this.value = i;
    this.hasJokers = jokers;
  }

  private int getCardRankValue(char c, boolean jokers){
    if(jokers){
      switch(c){
        case 'J': return 0;
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': return c-'1';
        case 'T': return 9;
        case 'Q': return 10;
        case 'K': return 11;
        case 'A': return 12;
        default:  return -1;
      }
    }else{
      switch(c){
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': return c-'2';
        case 'T': return 8;
        case 'J': return 9;
        case 'Q': return 10;
        case 'K': return 11;
        case 'A': return 12;
        default:  return -1;
      }
    }
  }
  private Rank calculateHandRank(boolean jokers){
    int[] cardRanks = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    for(int k=0;k<5;k++){
      char c = this.hand.charAt(k);
      cardRanks[getCardRankValue(c,jokers)]++;
    }
    String groups = "";
    for(int k=0;k<13;k++){
      if(cardRanks[k] > 0){
        groups += Integer.toString(cardRanks[k]);
      }
    }
    if(groups.contains("5")){ return Rank.FIVE_OF_A_KIND;  }
    if(groups.contains("4")){ return Rank.FOUR_OF_A_KIND;  }
    if(groups.contains("3")){
      if(groups.contains("2")){
        return Rank.FULL_HOUSE;
      }else{
        return Rank.THREE_OF_A_KIND;
      }
    }
    if(groups.contains("2")){
      if(groups.contains("11")){
        return Rank.ONE_PAIR;
      }else{
        return Rank.TWO_PAIR;
      }
    }
    return Rank.HIGH_CARD;
  }

  private Rank calculateHandRankWithJokers(){
    int[] cardRanks = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    for(int k=0;k<5;k++){
      char c = this.hand.charAt(k);
      cardRanks[getCardRankValue(c,true)]++;
    }
    String groups = "";

    for(int k=1;k<13;k++){
      if(cardRanks[k] > 0){
        groups += Integer.toString(cardRanks[k]);
      }
    }
    int wild = cardRanks[0];

    //System.out.println(this.hand + "->" + groups + "(" + wild + ")");
    if(groups.contains("5")){ return Rank.FIVE_OF_A_KIND;  }
    if(groups.contains("4")){
      switch(wild){
        case 1:   return Rank.FIVE_OF_A_KIND;
        default:  return Rank.FOUR_OF_A_KIND;
      }
    }
    if(groups.contains("3")){
      switch(wild){
        case 2:   return Rank.FIVE_OF_A_KIND;
        case 1:   return Rank.FOUR_OF_A_KIND;
        default:
          if(groups.contains("2")){
            return Rank.FULL_HOUSE;
          }else{
            return Rank.THREE_OF_A_KIND;
          }
      }
    }
    if(groups.contains("2")){
      switch(wild){
        case 3:   return Rank.FIVE_OF_A_KIND;
        case 2:   return Rank.FOUR_OF_A_KIND;
        case 1:
          if(groups.contains("22")){
            return Rank.FULL_HOUSE;
          }else{
            return Rank.THREE_OF_A_KIND;
          }
        default:
          if(groups.contains("11")){
            return Rank.ONE_PAIR;
          }else{
            return Rank.TWO_PAIR;
          }
      }
    }
    if(groups.contains("1")){
      switch(wild){
        case 4:   return Rank.FIVE_OF_A_KIND;
        case 3:   return Rank.FOUR_OF_A_KIND;
        case 2:   return Rank.THREE_OF_A_KIND;
        case 1:   return Rank.ONE_PAIR;
        default:  return Rank.HIGH_CARD;
      }
    }
    return Rank.FIVE_OF_A_KIND; //all jokers!
  }

  public String getHand(){
    return this.hand;
  }

  public Rank getHandRank(){
    return this.rank;
  }

  public int getValue(){
    return this.value;
  }

  public boolean hasJokers(){
    return this.hasJokers;
  }

  private String rankToString(Rank r){
    switch(r){
      case HIGH_CARD: return "high";
      case ONE_PAIR: return "pair";
      case TWO_PAIR: return "two";
      case THREE_OF_A_KIND: return "three";
      case FULL_HOUSE: return "house";
      case FOUR_OF_A_KIND: return "four";
      case FIVE_OF_A_KIND: return "five";
      default: return "";
    }
  }

  public String toString(){
    return this.hand + ":" + this.value + "->" + rankToString(this.rank);
  }

  @Override
  public int compareTo(CamelPokerHand other){
    if(this.getHandRank().compareTo(other.getHandRank()) != 0){
      return this.getHandRank().compareTo(other.getHandRank());
    }else{
      int index=0;
      while(this.getHand().charAt(index) == other.getHand().charAt(index)){
        index++;
      }
      return Integer.compare(getCardRankValue(this.getHand().charAt(index),this.hasJokers()),getCardRankValue(other.getHand().charAt(index),other.hasJokers()));
    }
  }
}
