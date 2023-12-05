public class MapTriple{
  private long dest;
  private long src;
  private long width;

  private long lowerBound;
  private long upperBound;
  private long offset;

  public MapTriple(long d, long s, long w){
    this.dest = d;
    this.src = s;
    this.width = w;

    this.lowerBound = s;
    this.upperBound = s + w - 1;
    this.offset = d - s;
  }

  public boolean inRange(long num){
    return (num >= lowerBound && num <= upperBound);
  }
  public long getOffset(){
    return this.offset;
  }

  public long map(long num){
    if(inRange(num)){
      return num + offset;
    }else{
      return num;
    }
  }

  public String toString(){
    return "[" + this.lowerBound + "," + this.upperBound + "]->" + this.offset;
  }
}
