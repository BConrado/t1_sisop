import java.util.ArrayList;

public class CPU {
  private int pc;
  private Integer acc;
  private Word ir;

  
  public static ArrayList<Word> m;
  public static ArrayList<Variable> v;
  
  public CPU(ArrayList<Word> m, ArrayList<Variable> v){
    this.m = m;
    this.v = v;
    run();
  }

  public void run(){

  }

  private Integer find (String variableName){
    return 1;
  }
}
