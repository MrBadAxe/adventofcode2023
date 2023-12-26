import java.util.HashMap;
public class PartRange{
  private HashMap<Character,Integer> ratingsMin;
  private HashMap<Character,Integer> ratingsMax;

  public PartRange(){
    ratingsMin = new HashMap<Character,Integer>();
    ratingsMax = new HashMap<Character,Integer>();
    ratingsMin.put('x',1);
    ratingsMin.put('m',1);
    ratingsMin.put('a',1);
    ratingsMin.put('s',1);
    ratingsMax.put('x',4000);
    ratingsMax.put('m',4000);
    ratingsMax.put('a',4000);
    ratingsMax.put('s',4000);
  }
  public void setMinRating(char key, int value){
    ratingsMin.put(key,value);
  }
  public int getMinRating(char key){
    return ratingsMin.get(key);
  }
  public void setMaxRating(char key, int value){
    ratingsMax.put(key,value);
  }
  public int getMaxRating(char key){
    return ratingsMax.get(key);
  }
  public String toString(){
    String z = "";
    z += "(X,M,A,S)=(";
    z += getMinRating('x') + "-" + getMaxRating('x') + ",";
    z += getMinRating('m') + "-" + getMaxRating('m') + ",";
    z += getMinRating('a') + "-" + getMaxRating('a') + ",";
    z += getMinRating('s') + "-" + getMaxRating('s') + ")";
    return z;
  }
  public PartRange clone(){
    PartRange z = new PartRange();
    for(char rating : ratingsMin.keySet()){
      z.setMinRating(rating,this.getMinRating(rating));
    }
    for(char rating : ratingsMax.keySet()){
      z.setMaxRating(rating,this.getMaxRating(rating));
    }
    return z;
  }
  public long getRangeQuantity(){
    long rangeValue = (getMaxRating('x') - getMinRating('x') + 1);
    rangeValue *= (getMaxRating('m') - getMinRating('m') + 1);
    rangeValue *= (getMaxRating('a') - getMinRating('a') + 1);
    rangeValue *= (getMaxRating('s') - getMinRating('s') + 1);
    return rangeValue;
  }

}
