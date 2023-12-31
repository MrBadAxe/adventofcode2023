import java.util.ArrayList;
import java.util.HashMap;
public class Workflow{
  ArrayList<WorkflowRule> rules;

  public Workflow(){
    this.rules = new ArrayList<WorkflowRule>();
  }
  public void addRule(WorkflowRule rule){
    this.rules.add(rule);
  }
  public String process(Part p){
    for(int k=0;k<rules.size();k++){
      if(rules.get(k).apply(p)){
        return rules.get(k).getDestination();
      }
    }
    return "";
  }
  private ArrayList<PartRange> partition(PartRange range, WorkflowRule rule){
    ArrayList<PartRange> z = new ArrayList<PartRange>();
    if(rule.getThreshold() > range.getMinRating(rule.getRating()) && rule.getThreshold() < range.getMaxRating(rule.getRating())){
      PartRange r1 = range.clone();
      PartRange r2 = range.clone();
      if(rule.getPolarity()){
        r1.setMaxRating(rule.getRating(),rule.getThreshold());
        r2.setMinRating(rule.getRating(),rule.getThreshold()+1);
      }else{
        r1.setMaxRating(rule.getRating(),rule.getThreshold()-1);
        r2.setMinRating(rule.getRating(),rule.getThreshold());
      }
      z.add(r1);
      z.add(r2);
    }else{
      z.add(range);
    }
    return z;
  }
  public ArrayList<PartRange> partition(PartRange range){
    ArrayList<PartRange> z = new ArrayList<PartRange>();
    z.add(range);
    for(int k=0;k<rules.size() && z.size()<2;k++){
      WorkflowRule rule = rules.get(k);
      z = partition(z.get(0),rule);
    }
    return z;
  }
  public String process(PartRange range, WorkflowRule rule){
    if(partition(range,rule).size() > 1){
      return "multiple";
    }else if(rule.apply(range)){
      return rule.getDestination();
    }else{
      return "";
    }
  }
  public String processAll(PartRange range){
    for(int k=0;k<rules.size();k++){
      if(rules.get(k).apply(range)){
        return process(range,rules.get(k));
      }
    }
    return "";
  }
  public String toString(){
    String z = "";
    for(int k=0;k<rules.size();k++){
      if(k > 0){
        z += ", else ";
      }
      z += rules.get(k).toString();
    }
    z += "\n";
    return z;
  }
}
