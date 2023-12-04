import java.util.List;
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
          int partNumber = (schematic[row][col] - '0');
          while(col+1<schematic[row].length && Character.isDigit(schematic[row][col+1])){
            col++;
            partNumber = (partNumber * 10) + (schematic[row][col] - '0');
          }
          total += partNumber;
        }
      }
    }

    return Integer.toString(total);
  }
}
