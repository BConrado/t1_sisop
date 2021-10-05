import java.util.ArrayList;
import java.util.Scanner;

public class CPU {
  private Word pc;
  private Integer acc;

  public static ArrayList<Word> m;
  public static ArrayList<Variable> v;
  public static Scanner in;
  
  public CPU(ArrayList<Word> m, ArrayList<Variable> v){
    this.m = m;
    this.v = v;
    run();
  }

  public void run(){
    for (int i = 0; i<m.size(); i++) { 
      pc = m.get(i);
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
          m.set(i, new Word(pc.opc, ""+acc));
        break;

        case brany: 
          
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

  private Integer find (String label){
    for (Variable var : v) {
      if(var.name.equals(label)) {
        return var.parametro;
      }
    }
    return null;
  }
  
  private void jump() {

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
