import java.util.ArrayList;
import java.util.Scanner;

public class CPU {
  private Word pc;
  private Integer acc;

  public int timeCount;

  public static ArrayList<Word> m;
  public static ArrayList<Variable> v;
  public static Scanner in;
  
  public CPU(ArrayList<Word> m, ArrayList<Variable> v){
    this.m = m;
    this.v = v;
    run();
  }

  public void printMemV(){
    System.out.println();
    System.out.println(".data apos execucao");
    for (Variable var : v) {
      System.out.println(var);
    }
  }

  public void run(){
    for (int i = 0; i<m.size(); i++) { 
      timeCount++;
      System.out.println("Valor ACC = "+acc);
      System.out.println("MEM POS "+i);
      pc = m.get(i);
      System.out.println(" INST = "+ pc.opc +" OP1 = "+ pc.parametro);
      int op1;
      switch (pc.opc) {
        case add: 
          if (pc.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(pc.parametro.charAt(1)));
          }else {
            op1 = find(pc.parametro);        
          }
          acc+=op1; //ADD 
        break;

        case sub:
          if (pc.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(pc.parametro.charAt(1)));
          }else {
            op1 = find(pc.parametro);        
          }
          acc-=op1; //SUB
        break;

        case mult: 
          if (pc.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(pc.parametro.charAt(1)));
          }else {
            op1 = find(pc.parametro);        
          }
          acc*=op1; //MULT 
        break;

        case div: 
          if (pc.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(pc.parametro.charAt(1)));
          }else {
            op1 = find(pc.parametro);        
          }
          acc/=op1; //DIV
        break;

        case load:        
          acc = find(pc.parametro);
        break;

        case store: 
          int index = getIndexVariable(pc.parametro);
          v.set(index, new Variable(pc.parametro, acc));
        break;

        case brany: 
          Integer labelPos = find(pc.parametro);
          i = labelPos;
        break;

        case brpos: 
          if(acc > 0) {
            int pos = find(pc.parametro);
            i = pos;
          }
        break;

        case brzero: 
          if(acc == 0) { 
            int pos = find(pc.parametro);
            i = pos;
          }
        break;

        case brneg: 
          if(acc < 0) {
            int pos = find(pc.parametro);
            i = pos;
          }
        break;

        case syscall:
          if (Integer.parseInt(pc.parametro) == 0){
            printMemV();
            break;
          }else {
            syscall(Integer.parseInt(pc.parametro));
          }
        break;

        default: 

        break;
      }
    }
  }

  private int getIndexVariable(String varName){
    for (int i = 0; i<v.size(); i++) {
      if(v.get(i).name.equals(varName)) {
        return i;
      }
    }
    return -1;
  }

  private Integer find (String label){
    for (Variable var : v) {
      if(var.name.equals(label)) {
        return var.parametro;
      }
    }
    return null;
  }

  private void syscall(int num) {
    switch (num) {
      case 1:
        System.out.println(acc);
      break;
      case 2:
        in = new Scanner(System.in);
        int i = in.nextInt();
        acc = i;
      break;
      default: break;
    }
  }

  private void printEverything(){

  }
}
