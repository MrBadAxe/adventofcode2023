public class Beam{
  private final int startX;
  private final int startY;
  private final int velX;
  private final int velY;

  public Beam(int x, int y, int dx, int dy){
    startX = x;
    startY = y;
    velX = dx;
    velY = dy;
  }

  public int getStartX(){
    return this.startX;
  }
  public int getStartY(){
    return this.startY;
  }
  public int getVelX(){
    return this.velX;
  }
  public int getVelY(){
    return this.velY;
  }
  public String toString(){
    return "(" + this.getStartX() + "," + this.getStartY() + "->" + this.getVelX() + "," + this.getVelY() + ")";
  }

  @Override
  public boolean equals(Object o){
    if(o == this){ return true; }
    if(!(o instanceof Beam)){ return false; }
    Beam other = (Beam)o;
    return (this.getStartX() == other.getStartX() && this.getStartY() == other.getStartY() && this.getVelX() == other.getVelX() && this.getVelY() == other.getVelY() );
  }
}
