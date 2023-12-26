import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
public class Day19{
  public static HashMap<String,Workflow> parseWorkflows(List<String> input, int start, int end){
    int index = start;
    HashMap<String,Workflow> workflows = new HashMap<String,Workflow>();
    while(index < end){
      String line = input.get(index);
      String[] tokens = line.split("\\{");
      String workflowName = tokens[0];
      String workflowRuleList = tokens[1].substring(0,tokens[1].length()-1);
      Workflow w = new Workflow();
      String[] ruleListSplit = workflowRuleList.split(",");
      for(int k=0;k<ruleListSplit.length;k++){
        WorkflowRule rule;
        if(ruleListSplit[k].contains(":")){
          String[] colonSplit = ruleListSplit[k].split(":");
          char rating = colonSplit[0].charAt(0);
          boolean polarity = colonSplit[0].charAt(1) == '>';
          int threshold = Integer.parseInt(colonSplit[0].substring(2));
          String destination = colonSplit[1];
          rule = new WorkflowRule(rating,polarity,threshold,destination);
        }else{
          rule = new WorkflowRule('x',true,0,ruleListSplit[k]);
        }
        w.addRule(rule);
      }
      workflows.put(workflowName,w);

      index++;
    }
    return workflows;
  }
  public static ArrayList<Part> parseParts(List<String> input, int start, int end){
    ArrayList<Part> parts = new ArrayList<Part>();
    int index = start;
    while(index < end){
      String line = input.get(index);
      line = line.substring(1,line.length()-1);
      Part p = new Part();
      String[] ratings = line.split(",");
      for(int k=0;k<ratings.length;k++){
        String[] ratingSplit = ratings[k].split("=");
        p.setRating(ratingSplit[0].charAt(0),Integer.parseInt(ratingSplit[1]));
      }
      parts.add(p);

      index++;
    }
    return parts;
  }
  public static String getPart01(List<String> input){
    int index = 0;
    while(!input.get(index).equals("")){
      index++;
    }

    HashMap<String,Workflow> workflows = parseWorkflows(input,0,index);
    ArrayList<Part> parts = parseParts(input, index+1, input.size());

    int total = 0;

    for(Part part : parts){
      String currentWorkflow = "in";
      while(!currentWorkflow.equals("A") && !currentWorkflow.equals("R")){
        String newWorkflow = workflows.get(currentWorkflow).process(part);
        currentWorkflow = newWorkflow;
      }
      if(currentWorkflow.equals("A")){
        total += (part.getRating('x') + part.getRating('m') + part.getRating('a') + part.getRating('s'));
      }
    }
    return Integer.toString(total);
  }
  public static String getPart02(List<String> input){
    int index = 0;
    while(!input.get(index).equals("")){
      index++;
    }
    HashMap<String,Workflow> workflows = parseWorkflows(input,0,index);

    long total = 0;

    ArrayList<PartRange> ranges = new ArrayList<PartRange>();
    ranges.add(new PartRange());
    while(ranges.size() > 0){
      String currentWorkflow = "in";
      PartRange range = ranges.remove(0);
      while(!currentWorkflow.equals("A") && !currentWorkflow.equals("R") && !currentWorkflow.equals("multiple")){
        String newWorkflow = workflows.get(currentWorkflow).processAll(range);
        if(newWorkflow.equals("multiple")){
          ArrayList<PartRange> newRanges = workflows.get(currentWorkflow).partition(range);
          ranges.addAll(newRanges);
        }
        currentWorkflow = newWorkflow;
      }
      if(currentWorkflow.equals("A")){
        total += range.getRangeQuantity();
      }
    }

    return Long.toString(total);
  }

}
