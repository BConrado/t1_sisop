import java.util.*;
import java.io.*;

public class Sistema {
  public VM vm;
  public static ArrayList<Word> aux;
  public static ArrayList<Variable> variables;

  public Sistema() {
    vm = new VM(aux, variables);
  }

  public void loadFile(String name) throws FileNotFoundException {
    File f = new File(name);
    Scanner in = new Scanner(f);
    aux = new ArrayList<Word>();
    variables = new ArrayList<Variable>();

    while (in.hasNextLine()) {

      String fl = in.nextLine();
      if (fl.equals(".code")) {
        boolean varAux = true;
        while (varAux) {
          String[] line = in.nextLine().split(" ");
          System.out.println(line);
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
            case "brany":
              aux.add(new Word(Opcode.brany, line[3]));
              break;
            case "brpos":
              aux.add(new Word(Opcode.brpos, line[3]));
              break;
            case "brzero":
              aux.add(new Word(Opcode.brzero, line[3]));
              break;
            case "brneg":
              aux.add(new Word(Opcode.brneg, line[3]));
              break;
            case "syscall":
              aux.add(new Word(Opcode.syscall, line[3]));
              break;
            default: varAux = false;
              break;
          }
        }     
      } else if (fl.equals(".data")) {
        while (true) {
          String[] line = in.nextLine().split(" ");
          if(line[0].equals(".enddata")){
            break;
          }
          variables.add(new Variable(line[2], Integer.parseInt(line[3])));
        }
      }

    }

    in.close();

  }

  public static void main(String[] args) throws FileNotFoundException {
    Sistema s = new Sistema();
    s.loadFile("prog1.txt"); // TODO: LER NOMES PELO CONSOLE 

  }

}