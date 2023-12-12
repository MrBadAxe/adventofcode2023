public class Point{
  private final long x;
  private final long y;

  public Point(long x, long y){
    this.x = x;
    this.y = y;
  }

  public Point(){
    this(0,0);
  }

  public long getX(){
    return this.x;
  }

  public long getY(){
    return this.y;
  }

  public long taxicabDistance(Point other){
    return Math.abs(other.getX() - this.getX()) + Math.abs(other.getY() - this.getY());
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof Point)){ return false; }
    Point other = (Point)o;
    return (this.getX() == other.getX() && this.getY() == other.getY());
  }

  public String toString(){
    return "(" + this.getX() + "," + this.getY() + ")";
  }
}
