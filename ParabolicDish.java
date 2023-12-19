public class ParabolicDish extends CharGrid{
  public ParabolicDish(int height, int width){
    super(height, width, '.');
  }

  public char[] push(char[] stripe){
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
  public void tiltNorth(){
    for(int col=0;col<this.getWidth();col++){
      char[] stripe = new char[this.getHeight()];
      for(int row=0;row<this.getHeight();row++){
        stripe[row] = this.get(row,col);
      }
      stripe = push(stripe);
      for(int row=0;row<this.getHeight();row++){
        this.set(row,col,stripe[row]);
      }
    }
  }
  public void tiltSouth(){
    for(int col=0;col<this.getWidth();col++){
      char[] stripe = new char[this.getHeight()];
      for(int row=0;row<this.getHeight();row++){
        stripe[row] = this.get(this.getHeight() - (row+1),col);
      }
      stripe = push(stripe);
      for(int row=0;row<this.getHeight();row++){
        this.set(this.getHeight() - (row+1),col,stripe[row]);
      }
    }
  }
  public void tiltWest(){
    for(int row=0;row<this.getHeight();row++){
      char[] stripe = new char[this.getWidth()];
      for(int col=0;col<this.getWidth();col++){
        stripe[col] = this.get(row,col);
      }
      stripe = push(stripe);
      for(int col=0;col<this.getWidth();col++){
        this.set(row,col,stripe[col]);
      }
    }
  }
  public void tiltEast(){
    for(int row=0;row<this.getHeight();row++){
      char[] stripe = new char[this.getWidth()];
      for(int col=0;col<this.getWidth();col++){
        stripe[col] = this.get(row,this.getWidth() - (col+1));
      }
      stripe = push(stripe);
      for(int col=0;col<this.getWidth();col++){
        this.set(row,this.getWidth() - (col+1),stripe[col]);
      }
    }
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
