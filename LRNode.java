public class LRNode{
  private String name;
  private LRNode left;
  private LRNode right;

  public LRNode(String str){
    this.name = str;
  }
  public void setLeft(LRNode other){
    this.left = other;
  }
  public void setRight(LRNode other){
    this.right = other;
  }
  public LRNode getLeft(){
    return this.left;
  }
  public LRNode getRight(){
    return this.right;
  }
  public String getName(){
    return this.name;
  }
}
