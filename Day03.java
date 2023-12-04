import java.util.List;
import java.util.HashSet;
public class Day03{

  private static String SYMBOLS = "*#+$@/%&=-";

  private static char[][] parseSchematic(List<String> input){
    int height = input.size();
    int width = input.get(0).length();
    char[][] schematic = new char[height][width];
    for(int row=0;row<height;row++){
      String schematicRow = input.get(row);
      for(int col=0;col<width;col++){
        schematic[row][col] = schematicRow.charAt(col);
      }
    }
    return schematic;
  }

  private static int getNumberAt(char[][] schematic, int row, int col){
    if(!Character.isDigit(schematic[row][col])){ return -1; }
    int colStart = col;
    int colEnd = col;
    while(colStart-1>=0 && Character.isDigit(schematic[row][colStart-1])){ colStart--; }
    while(colEnd+1<schematic[row].length && Character.isDigit(schematic[row][colEnd+1])){ colEnd++; }
    int z = 0;
    for(int span=colStart; span<=colEnd; span++){
      z = z*10 + (schematic[row][span] - '0');
    }
    return z;
  }

  private static boolean isPartNumber(char[][] schematic, int row, int col){
    if(!Character.isDigit(schematic[row][col])){ return false; }
    int colStart = col;
    int colEnd = col;
    while(colStart-1>=0 && Character.isDigit(schematic[row][colStart-1])){ colStart--; }
    while(colEnd+1<schematic[row].length && Character.isDigit(schematic[row][colEnd+1])){ colEnd++; }

    if(colEnd+1<schematic[row].length && SYMBOLS.indexOf(schematic[row][colEnd+1])!=-1){ /*System.out.println("right");*/ return true; }
    if(colStart-1>=0 && SYMBOLS.indexOf(schematic[row][colStart-1])!=-1){ /*System.out.println("left");*/ return true; }

    int spanStart = (colStart - 1 >= 0 ? colStart - 1 : colStart);
    int spanEnd = (colEnd + 1 < schematic[row].length ? colEnd + 1 : colEnd);
    for(int span=spanStart; span<=spanEnd; span++){
      if(row-1 > 0 && SYMBOLS.indexOf(schematic[row-1][span])!=-1){ /*System.out.println("above");*/ return true;}
      if(row+1 < schematic.length && SYMBOLS.indexOf(schematic[row+1][span])!=-1){ /*System.out.println("below");*/ return true;}
    }
    return false;
  }

  public static String getPart01(List<String> input){
    char[][] schematic = parseSchematic(input);

    int total = 0;
    int height = schematic.length;
    int width = schematic[0].length;

    for(int row=0;row<height;row++){
      for(int col=0;col<width;col++){
        if(Character.isDigit(schematic[row][col]) && isPartNumber(schematic,row,col)){
          int partNumber = getNumberAt(schematic, row, col);
          total += partNumber;
          col += (int) Math.floor(Math.log10(partNumber)) + 1;
        }
      }
    }

    return Integer.toString(total);
  }

  public static String getPart02(List<String> input){
    char[][] schematic = parseSchematic(input);

    int height = schematic.length;
    int width = schematic[0].length;
    int total = 0;

    for(int row=0;row<height;row++){
      for(int col=0;col<width;col++){
        if(schematic[row][col] == '*'){
          HashSet<Integer> gears = new HashSet<Integer>();
          for(int x=-1;x<=1;x++){
            for(int y=-1;y<=1;y++){
              gears.add(getNumberAt(schematic,row+x,col+y));
            }
          }
          gears.remove(-1);
          if(gears.size() == 2){
            Integer i[] = new Integer[2];
            i = gears.toArray(i);
            int gearRatio = i[0]*i[1];
            total += gearRatio;
          }
        }
      }
    }

    return Integer.toString(total);
  }
}
