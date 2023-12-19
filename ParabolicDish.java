public class ParabolicDish extends CharGrid{
  public ParabolicDish(int height, int width){
    super(height, width, '.');
  }

  public static ParabolicDish fromString(String str){
    String[] lines = str.split("\n");
    int height = lines.length;
    int width = lines[0].length();
    ParabolicDish dish = new ParabolicDish(height,width);
    for(int row=0;row<dish.getHeight();row++){
      for(int col=0;col<dish.getWidth();col++){
        dish.set(row,col,lines[row].charAt(col));
      }
    }
    return dish;
  }

  private char[] push(char[] stripe){
    char[] z = new char[stripe.length];
    int start = 0;
    int end = 0;
    int spheres = 0;
    while(end < stripe.length){
      while(end < stripe.length && stripe[end] != '#'){
        if(stripe[end] == 'O'){
          spheres++;
        }
        end++;
      }
      for(int k=start;k<end;k++){
        z[k] = (k-start<spheres) ? 'O' : '.';
      }
      if(end < stripe.length){
        z[end] = '#';
      }
      start = end+1;
      end = start;
      spheres = 0;
    }
    return z;
  }
  public ParabolicDish tiltNorth(){
    ParabolicDish other = new ParabolicDish(this.getHeight(),this.getWidth());
    for(int col=0;col<this.getWidth();col++){
      char[] stripe = new char[this.getHeight()];
      for(int row=0;row<this.getHeight();row++){
        stripe[row] = this.get(row,col);
      }
      stripe = push(stripe);
      for(int row=0;row<this.getHeight();row++){
        other.set(row,col,stripe[row]);
      }
    }
    return other;
  }
  public ParabolicDish tiltSouth(){
    ParabolicDish other = new ParabolicDish(this.getHeight(),this.getWidth());
    for(int col=0;col<this.getWidth();col++){
      char[] stripe = new char[this.getHeight()];
      for(int row=0;row<this.getHeight();row++){
        stripe[row] = this.get(this.getHeight() - (row+1),col);
      }
      stripe = push(stripe);
      for(int row=0;row<this.getHeight();row++){
        other.set(this.getHeight() - (row+1),col,stripe[row]);
      }
    }
    return other;
  }
  public ParabolicDish tiltWest(){
    ParabolicDish other = new ParabolicDish(this.getHeight(),this.getWidth());
    for(int row=0;row<this.getHeight();row++){
      char[] stripe = new char[this.getWidth()];
      for(int col=0;col<this.getWidth();col++){
        stripe[col] = this.get(row,col);
      }
      stripe = push(stripe);
      for(int col=0;col<this.getWidth();col++){
        other.set(row,col,stripe[col]);
      }
    }
    return other;
  }
  public ParabolicDish tiltEast(){
    ParabolicDish other = new ParabolicDish(this.getHeight(),this.getWidth());
    for(int row=0;row<this.getHeight();row++){
      char[] stripe = new char[this.getWidth()];
      for(int col=0;col<this.getWidth();col++){
        stripe[col] = this.get(row,this.getWidth() - (col+1));
      }
      stripe = push(stripe);
      for(int col=0;col<this.getWidth();col++){
        other.set(row,this.getWidth() - (col+1),stripe[col]);
      }
    }
    return other;
  }

  public int getLoad(){
    int total = 0;
    for(int row=0;row<this.getHeight();row++){
      for(int col=0;col<this.getWidth();col++){
        if(this.get(row,col) == 'O'){
          total += this.getHeight() - row;
        }
      }
    }
    return total;
  }
}
