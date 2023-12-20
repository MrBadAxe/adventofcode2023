import java.util.ArrayList;

public class MirrorGrid extends CharGrid{
  public MirrorGrid(int height, int width){
    super(height,width,'.');
  }

  private static CharGrid compose(CharGrid a, CharGrid b){
    if(a.getHeight() != b.getHeight() || a.getWidth() != b.getWidth()){
      return null;
    }
    CharGrid z = new CharGrid(a.getHeight(), a.getWidth(), '.');
    for(int row=0;row<z.getHeight();row++){
      for(int col=0;col<z.getWidth();col++){
        z.set(row,col,((a.get(row,col) == '#' || b.get(row,col) == '#') ? '#' : '.'));
      }
    }
    return z;
  }
  public CharGrid getEnergizeMap(Beam start){
    CharGrid energized = new CharGrid(this.getHeight(),this.getWidth(),'.');

    ArrayList<Beam> beams = new ArrayList<Beam>();
    beams.add(start);
    int index = 0;
    while(index < beams.size()){
      Beam b = beams.get(index);
      int row = b.getStartX();
      int col = b.getStartY();
      int dRow = b.getVelX();
      int dCol = b.getVelY();
      boolean stopped = false;
      while(!stopped){
        energized.set(row,col,'#');
        if((row != b.getStartX() || col != b.getStartY()) || index == 0){
          char c = this.get(row,col);
          if(c == '\\'){
            stopped = true;
            Beam reflected = new Beam(row,col,dCol,dRow);
            if(!beams.contains(reflected)){
              beams.add(reflected);
            }
          }else if(c == '/'){
            stopped = true;
            Beam reflected = new Beam(row,col,-1*dCol,-1*dRow);
            if(!beams.contains(reflected)){
              beams.add(reflected);
            }
          }else if(c == '|' && dRow == 0 && dCol != 0){
            stopped = true;
            Beam split1 = new Beam(row,col,-1,0);
            Beam split2 = new Beam(row,col,1,0);
            if(!beams.contains(split1)){  beams.add(split1);  }
            if(!beams.contains(split2)){  beams.add(split2);  }
          }else if(c == '-' && dCol == 0 && dRow != 0){
            stopped = true;
            Beam split1 = new Beam(row,col,0,-1);
            Beam split2 = new Beam(row,col,0,1);
            if(!beams.contains(split1)){  beams.add(split1);  }
            if(!beams.contains(split2)){  beams.add(split2);  }
          }
        }
        row += dRow;
        col += dCol;
        if(row < 0 || row >= this.getHeight() || col < 0 || col >= this.getWidth()){
          stopped = true;
        }
      }
      index++;
    }
    return energized;
  }

  public CharGrid getEnergizeMap(int x, int y, int dx, int dy){
    int row = x;
    int col = y;
    int dRow = dx;
    int dCol = dy;
    System.out.println("?(" + row + "," + col + "," + dRow + "," + dCol + ")");
    CharGrid energized = new CharGrid(this.getHeight(),this.getWidth(),'.');

    while(dRow != 0 || dCol != 0){
      System.out.println(row + "," + col);
      energized.set(row,col,'#');
      if(this.get(row,col) == '/'){
        int newDRow = -dCol;
        int newDCol = -dRow;
        dRow = newDRow;
        dCol = newDCol;
      }
      if(this.get(row,col) == '\\'){
        int newDRow = dCol;
        int newDCol = dRow;
        dRow = newDRow;
        dCol = newDCol;
      }
      if(this.get(row,col) == '-' && dRow != 0 && dCol == 0){
        dCol = -1;
        dRow = 0;
        energized = compose(energized, getEnergizeMap(row,col,0,1));
      }
      if(this.get(row,col) == '|' && dRow == 0 && dCol != 0){
        dRow = -1;
        dCol = 0;
        energized = compose(energized, getEnergizeMap(row,col,1,0));
      }
      row += dRow;
      col += dCol;
      if(row < 0 || row > this.getHeight()-1 || col < 0 || col > this.getWidth()-1){
        dRow = 0;
        dCol = 0;
      }
    }
    System.out.println("!");
    return energized;
  }
}
