public class CPU {
  private int pc;
  private Integer acc;
  private Word ir;

  private Word[] m;
  private Variable[] v;

  public CPU(Word[] m, Variable[] v){
    this.m = m;
    this.v = v;
  }
  

  public void run(){
    
  }
}
