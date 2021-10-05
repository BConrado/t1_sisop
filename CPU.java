import java.util.ArrayList;
import java.util.Scanner;

public class CPU {
  private int pc;
  private Word currentInst;
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
      pc = i;
      currentInst = m.get(i);
      int op1;
      switch (currentInst.opc) {
        case add: 
          if (currentInst.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(currentInst.parametro.charAt(1)));
          }else {
            op1 = find(currentInst.parametro);        
          }
          acc+=op1; //ADD 
        break;

        case sub:
          if (currentInst.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(currentInst.parametro.charAt(1)));
          }else {
            op1 = find(currentInst.parametro);        
          }
          acc-=op1; //SUB
        break;

        case mult: 
          if (currentInst.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(currentInst.parametro.charAt(1)));
          }else {
            op1 = find(currentInst.parametro);        
          }
          acc*=op1; //MULT 
        break;

        case div: 
          if (currentInst.parametro.charAt(0) == '#' ){
            op1 = Integer.parseInt(String.valueOf(currentInst.parametro.charAt(1)));
          }else {
            op1 = find(currentInst.parametro);        
          }
          acc/=op1; //DIV
        break;

        case load:        
          acc = find(currentInst.parametro);
        break;

        case store: 
          m.set(i, new Word(currentInst.opc, ""+acc));
        break;

        case brany: 
          
        break;

        case brpos: 
          if(acc > 0) {
            int pos = find(currentInst.parametro);
            i = pos;
          }
        break;

        case brzero: 
          if(acc == 0) { 
            int pos = find(currentInst.parametro);
            i = pos;
          }
        break;

        case brneg: 
          if(acc < 0) {
            int pos = find(currentInst.parametro);
            i = pos;
          }
        break;

        case syscall:
          if (Integer.parseInt(currentInst.parametro) == 0){
            break;
          }else {
            syscall(Integer.parseInt(currentInst.parametro));
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
