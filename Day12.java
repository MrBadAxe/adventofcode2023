import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Day12{
  public static int[] parseSpringList(String str){
    String[] split = str.split(",");
    int[] z = new int[split.length];
    for(int k=0;k<z.length;k++){
      z[k] = Integer.parseInt(split[k]);
    }
    return z;
  }
  public static List<String> fillFirstWildcard(String str){
    ArrayList<String> z = new ArrayList<String>();
    String operational = str.replaceFirst("\\?",".");
    String damaged = str.replaceFirst ("\\?","#");
    z.add(operational);
    z.add(damaged);
    return z;
  }
  public static List<String> fillFirstWildcardGroup(String str){
    int start = str.indexOf('?');
    int end = start;
    while(end != str.length() && str.charAt(end) == '?'){
      end++;
    }
    int length = end - start;

    ArrayList<String> z = new ArrayList<String>();
    for(int k=0;k<Math.pow(2,length);k++){
      String replacement = "";
      int temp = k;
      while(replacement.length() < length){
        replacement += (temp % 2 == 1 ? "#" : ".");
        temp /= 2;
      }
      z.add(str.replaceFirst("\\?".repeat(length),replacement));
    }
    return z;
  }
  public static int countChars(String str, char c){
    int total = 0;
    for(int k=0;k<str.length();k++){
      if(str.charAt(k) == c){
        total++;
      }
    }
    return total;
  }
  public static List<String> expandWildcards(String str, int[] grouping){
    int countHashes = 0;
    for(int k=0;k<grouping.length;k++){
      countHashes += grouping[k];
    }
    int countDots = str.length() - countHashes;

    ArrayList<String> z = new ArrayList<String>();
    z.addAll(fillFirstWildcard(str));
    String next = z.get(0);
    while(next.indexOf('?') >= 0){
      System.out.println(z.size());
      next = z.remove(0);
      //System.out.println(next);
      List<String> candidates = fillFirstWildcardGroup(next);
      for(String candidate : candidates){
        //System.out.println(candidate);
        if(countChars(candidate,'#') > countHashes){
          continue;
        }
        if(countChars(candidate,'.') > countDots){
          continue;
        }
        String beginning = candidate.indexOf('?') != -1 ? candidate.substring(0,candidate.indexOf('?')) : candidate;
        //System.out.println(beginning);
        if(fuzzyMatch(grouping, parseGrouping(beginning))){
          //System.out.println(candidate);
          z.add(candidate);
        }
      }
      next = z.get(0);
    }
    return z;
  }
  public static int[] parseGrouping(String str){
    String[] groups = str.replaceAll("\\."," ").strip().split("\s+");
    int[] z = new int[groups.length];
    for(int k=0;k<z.length;k++){
      z[k] = groups[k].length();
    }
    return z;
  }
  public static boolean fuzzyMatch(int[] a, int[] b){
    int shorter = Math.min(a.length,b.length);
    for(int k=0;k<shorter-1;k++){
      if(a[k] != b[k]){
        return false;
      }
    }
    if(a[shorter-1] < b[shorter-1]){
      return false;
    }
    return true;
  }
  public static boolean strictMatch(int[] a, int[] b){
    if(a.length != b.length){
      return false;
    }
    for(int k=0;k<a.length;k++){
      if(a[k] != b[k]){
        return false;
      }
    }
    return true;
  }

  //I gave up, this solution by Teyegr
  //https://github.com/Teyegr/AoC-2023/blob/main/src/Day12.java
  static long countPatterns(int i, int j, int r, String pattern, int[] rule, HashMap<List<Integer>, Long> memo) {
		if (j == pattern.length()) {
			if (i == j && rule.length == r) {
				return 1;
			}
			if (rule.length - 1 == r && j - i == rule[r]) {
				return 1;
			}
			return 0;
		}
		if (i >= pattern.length() || j >= pattern.length() || r > rule.length) {
			return 0;
		}

		List<Integer> pos = new ArrayList<Integer>();
		pos.add(i);
		pos.add(j);
		pos.add(r);
		if (memo.containsKey(pos)) {
			return memo.get(pos);
		}

		char c = pattern.charAt(j);
		if (c == '#') {
			long result = countPatterns(i, j + 1, r, pattern, rule, memo);
			memo.put(pos, result);
			return result;
		}
		if (c == '.') {
			if (i != j) {
				if (r == rule.length || rule[r] != j - i) {
					return 0;
				}
				i = j;
				r++;
			}
			long result = countPatterns(i + 1, j + 1, r, pattern, rule, memo);
			memo.put(pos, result);
			return result;
		}

		long result = countPatterns(i, j + 1, r, pattern, rule, memo);
		if (i != j) {
			if (r == rule.length || rule[r] != j - i) {
				memo.put(pos, result);
				return result;
			}
			i = j;
			r++;
		}
		result += countPatterns(i + 1, j + 1, r, pattern, rule, memo);
		memo.put(pos, result);
		return result;
	}

  public static String getPart01(List<String> input){
    int total = 0;
    //String line = input.get(5);
    for(String line : input){
      String[] split = line.split("\s");
      String arrangement = split[0];
      String groupings = split[1];
      int[] springList = parseSpringList(groupings);

      /*
      System.out.println(arrangement + " : " + groupings);
      for(String str : expandWildcards(arrangement,springList)){
        int[] group = parseGrouping(str);
        if(strictMatch(springList,group)){
          total++;
        }
      }
      */
      total += countPatterns(0,0,0,arrangement,springList,new HashMap<List<Integer>,Long>());
    }
    return Integer.toString(total);
  }
  public static String getPart02(List<String> input){
    long total = 0;
    //String line = input.get(0);
    for(String line : input){
      String[] split = line.split("\s");
      String arrangement = split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0];
      String groupings = split[1] + "," + split[1] + "," + split[1] + "," + split[1] + "," + split[1];
      int[] springList = parseSpringList(groupings);

      /*
      System.out.println(arrangement + " : " + groupings);
      for(String str : expandWildcards(arrangement,springList)){
        int[] group = parseGrouping(str);
        if(strictMatch(springList,group)){
          total++;
        }
      }
      */
      total += countPatterns(0,0,0,arrangement,springList,new HashMap<List<Integer>,Long>());
    }
    return Long.toString(total);
  }
}
