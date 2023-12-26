import java.util.HashMap;
public class Part{
  private HashMap<Character,Integer> ratings;

  public Part(){
    ratings = new HashMap<Character,Integer>();
  }
  public Part(int x, int m, int a, int s){
    this();
    setRating('x',x);
    setRating('m',m);
    setRating('a',a);
    setRating('s',s);
  }
  public void setRating(char key, int value){
    ratings.put(key,value);
  }
  public int getRating(char key){
    return ratings.get(key);
  }
  public String toString(){
    String z = "";
    z += "(X,M,A,S)=(";
    z += getRating('x') + ",";
    z += getRating('m') + ",";
    z += getRating('a') + ",";
    z += getRating('s') + ")";
    return z;
  }
}
