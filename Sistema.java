import java.util.*;
import java.io.*;

public class Sistema {
  public static VM vm;
  public static ArrayList<Word> aux;
  public static ArrayList<Variable> variables;

  public Sistema() {
    aux = new ArrayList<Word>();
    variables = new ArrayList<Variable>();
    
  }
  public static void Itinialize(){
    vm = new VM(aux, variables);
  }

  public void loadFile(String name) throws FileNotFoundException {
    File f = new File(name);
    Scanner in = new Scanner(f);
    

    while (in.hasNextLine()) {

      String label = in.nextLine();
      if (label.equals(".code")) {
        boolean varAux = true;
        while (varAux) {
          String[] line = in.nextLine().split(" ");
          if(line[0].equals(".endcode")){
            varAux = false;
            break;
          }
          switch (line[2]) {
            case "add":
              aux.add(new Word(Opcode.add, line[3]));
              break;
            case "sub":
              aux.add(new Word(Opcode.sub, line[3]));
              break;
            case "mult":
              aux.add(new Word(Opcode.mult, line[3]));
              break;
            case "div":
              aux.add(new Word(Opcode.div, line[3]));
              break;
            case "load":
              aux.add(new Word(Opcode.load, line[3]));
              break;
            case "store":
              aux.add(new Word(Opcode.store, line[3]));
              break;
            case "BRANY":
              aux.add(new Word(Opcode.brany, line[3]));
              break;
            case "BRPOS":
              aux.add(new Word(Opcode.brpos, line[3]));
              break;
            case "BRZERO":
              aux.add(new Word(Opcode.brzero, line[3]));
              break;
            case "BRNEG":
              aux.add(new Word(Opcode.brneg, line[3]));
              break;
            case "syscall":
              aux.add(new Word(Opcode.syscall, line[3]));
              break;
            default: varAux = false;
              break;
          }
        }     
      } else if (label.equals(".data")) {
        while (true) {
          String[] line = in.nextLine().split(" ");
          if(line[0].equals(".enddata")){
            break;
          }
          variables.add(new Variable(line[2], Integer.parseInt(line[3])));
        }
      } else if (label.charAt(label.length()-1) == ':') {
        aux.add(new Word(Opcode.label, aux.size()-1+""));
      }

    }

    in.close();

  }

  public static void main(String[] args) throws FileNotFoundException {
    Sistema s = new Sistema();
    s.loadFile("prog1.txt"); // TODO: LER NOMES PELO CONSOLE 
                             // ler mais de um arquivo 
                            // priorizar execucao 

    Itinialize();                       
  }

}