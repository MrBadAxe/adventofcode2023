public class WorkflowRule{
  private final char rating;
  private final boolean polarity;
  private final int threshold;
  private final String destination;

  public WorkflowRule(char c, boolean p, int t, String d){
    this.rating = c;
    this.polarity = p;
    this.threshold = t;
    this.destination = d;
  }
  public char getRating(){
    return this.rating;
  }
  public boolean getPolarity(){
    return this.polarity;
  }
  public int getThreshold(){
    return this.threshold;
  }
  public String getDestination(){
    return this.destination;
  }

  public boolean apply(Part p){
    return (polarity ? p.getRating(this.rating) > this.threshold : p.getRating(this.rating) < this.threshold);
  }
  public boolean apply(PartRange r){
    return (polarity ? (!(r.getMaxRating(this.rating) <= this.threshold)) : (!(r.getMinRating(this.rating) >= this.threshold)));
  }
  public String toString(){
    String z = "";
    z += "if " + rating + " ";
    z += (polarity ? ">" : "<") + " ";
    z += threshold + " ";
    z += "send to " + destination;
    return z;
  }
}
