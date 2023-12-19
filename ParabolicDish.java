public class ParabolicDish extends CharGrid{
  public ParabolicDish(int height, int width){
    super(height, width, '.');
  }

  public void tiltNorth(){
    char[] stripe;
    for(int col=0;col<this.getWidth();col++){
      stripe = new char[this.getHeight()];
      for(int row=0;row<this.getHeight();row++){
        stripe[row] = this.get(row,col);
      }
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
          if(k-start<spheres){
            stripe[k] = 'O';
          }else{
            stripe[k] = '.';
          }
        }
        start = end+1;
        end = start;
        spheres = 0;
      }
      for(int row=0;row<this.getHeight();row++){
        this.set(row,col,stripe[row]);
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
