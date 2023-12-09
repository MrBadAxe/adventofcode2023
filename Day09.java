import java.util.List;
public class Day09{
  private static int[] calculateDiffs(int[] sequence){
    int[] z = new int[sequence.length-1];
    for(int k=0;k<z.length;k++){
      z[k] = sequence[k+1] - sequence[k];
      System.out.print(z[k] + " ");
    }
    System.out.println("");
    return z;
  }
  private static int extrapolate(int[] sequence){
    if(sequence[0] == 0 && sequence[sequence.length-1] == 0){
      return 0;
    }else{
      return sequence[sequence.length-1] + extrapolate(calculateDiffs(sequence));
    }
  }

  public static String getPart01(List<String> input){
    int total = 0;
    for(String str : input){
      String[] splitOnWhitespace = str.split("\s");
      int sequenceLength = splitOnWhitespace.length;
      int[] sequence = new int[sequenceLength];
      for(int k=0;k<sequenceLength;k++){
        sequence[k] = Integer.parseInt(splitOnWhitespace[k]);
      }
      total += extrapolate(sequence);
    }
    return Integer.toString(total);
  }
}
