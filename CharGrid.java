public class CharGrid implements Cloneable{
  protected final int HEIGHT;
  protected final int WIDTH;
  protected char[][] grid;

  public CharGrid(int x, int y, char init){
    this.HEIGHT = x;
    this.WIDTH = y;
    this.grid = new char[HEIGHT][WIDTH];
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        grid[row][col] = init;
      }
    }
  }
  public int getHeight(){
    return this.HEIGHT;
  }
  public int getWidth(){
    return this.WIDTH;
  }
  public char get(int row, int col){
    return grid[row][col];
  }
  public void set(int row, int col, char c){
    grid[row][col] = c;
  }
  public String toString(){
    String z = "";
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z += grid[row][col];
      }
      z += "\n";
    }
    return z;
  }
  public CharGrid clone(){
    CharGrid z = new CharGrid(this.getHeight(),this.getWidth(),'.');
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z.set(row,col,this.get(row,col));
      }
    }
    return z;
  }
  public CharGrid transpose(){
    CharGrid z = new CharGrid(this.getWidth(),this.getHeight(),'.');
    for(int row=0;row<HEIGHT;row++){
      for(int col=0;col<WIDTH;col++){
        z.set(col,row,this.get(row,col));
      }
    }
    return z;
  }

}
