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
