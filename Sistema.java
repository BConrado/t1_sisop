import java.util.*;
import java.io.*;

public class Sistema {
  public static VM vm;
  public static ArrayList<Word> memory;
  public static ArrayList<Variable> variables;
  public static int Politica = 0; // 0 preempção 1 round robin
  public static int quantum;
  public static ArrayList<HashMap<Variable, Word>> programas;
  public static int posi = 0; // quantidade de programas
  public static String[] progs = new String[100];
  public static int[] priority = new int[100];
  public static int[] startTime = new int[100];

  public Sistema() {

  }

  public static void Itinialize() {
    memory = new ArrayList<Word>();
    variables = new ArrayList<Variable>();
    programas = new ArrayList<HashMap<Variable, Word>>();

    terminal();
  }

  public static void loadFile(String name) throws FileNotFoundException {
    File f = new File(name);
    Scanner in = new Scanner(f);

    while (in.hasNextLine()) {

      String label = in.nextLine();
      if (label.equals(".code")) {
        boolean varAux = true;
        while (varAux) {
          String[] line = in.nextLine().split(" ");
          if (line[0].equals(".endcode")) {
            varAux = false;
            break;
          }
          String op = "";
          if (line[0].equals("")) {
            op = line[2];
          }
          if (!line[0].equals("")) {
            op = line[0];
          }
          switch (op) {
            case "add":
              memory.add(new Word(Opcode.add, line[3]));
              break;
            case "sub":
              memory.add(new Word(Opcode.sub, line[3]));
              break;
            case "mult":
              memory.add(new Word(Opcode.mult, line[3]));
              break;
            case "div":
              memory.add(new Word(Opcode.div, line[3]));
              break;
            case "load":
              memory.add(new Word(Opcode.load, line[3]));
              break;
            case "store":
              memory.add(new Word(Opcode.store, line[3]));
              break;
            case "BRANY":
              memory.add(new Word(Opcode.brany, line[3]));
              break;
            case "BRPOS":
              memory.add(new Word(Opcode.brpos, line[3]));
              break;
            case "BRZERO":
              memory.add(new Word(Opcode.brzero, line[3]));
              break;
            case "BRNEG":
              memory.add(new Word(Opcode.brneg, line[3]));
              break;
            case "syscall":
              memory.add(new Word(Opcode.syscall, line[3]));
              break;
            default:
              variables.add(new Variable(op.substring(0, op.length() - 1), memory.size() - 1));
              ;
              break;
          }
        }
      } else if (label.equals(".data")) {
        while (true) {
          String[] line = in.nextLine().split(" ");
          if (line[0].equals(".enddata")) {
            break;
          }
          variables.add(new Variable(line[2], Integer.parseInt(line[3])));
        }
      }

    }

    in.close();

  }

  public static void main(String[] args) throws FileNotFoundException {

    // loadFile("prog3.txt"); // TODO: LER NOMES PELO CONSOLE
    // ler mais de um arquivo
    // priorizar execucao

    Itinialize();
  }

  public static void terminal() {
    boolean cond = true;
    Scanner in = new Scanner(System.in);

    while (cond) {

      printTerminal();
      String key = in.nextLine();
      switch (key) {
        case "1":
          System.out.println("Digite 0 para preemptiva, e 1 para Round Robin");
          Politica = in.nextInt();
          if (Politica == 1) {
            System.out.println("Digite o quantum");
            quantum = in.nextInt();
          }
          break;
        case "2":
          System.out.println("Digite o nome do arquivo");
          String nomeArq = in.nextLine();
          progs[posi] = nomeArq;
          if (Politica == 0) {
            System.out.println("Digite a prioridade - 0 -> Alta / 1 -> Media / 2 -> Baixa");
            int prioridade = in.nextInt();
            priority[posi] = prioridade;
          }
          System.out.println("Digite o arrivel time (INT)");
          int arrivelTime = in.nextInt();
          startTime[posi] = arrivelTime;
          posi++;
          break;
        case "3":
          System.out.println("Running");
          cond = false;

          if (Politica == 0) {
            try {
              startPrograms();

            } catch (Exception e) {
              // TODO: handle exception
            }
          } else {
            try {
              startProgramasNormal();
            } catch (Exception e) {
              // TODO: handle exception
            }

          }
          break;

        default:
          System.out.println("Enter a Valid Number");
          break;
      }
    }

  }

  public static void printTerminal() {
    System.out.println("1- Escolher Politica");
    System.out.println("2- Add prog");
    System.out.println("3- Run");
  }

  public static void startPrograms() throws FileNotFoundException {
    HashMap<Integer, String> prioridades = new HashMap<>();

    for (int i = 0; i < posi; i++) {
      prioridades.put(priority[i], progs[i]);
    }

    TreeMap<Integer, String> orderedPriorities = new TreeMap<>(prioridades);

    ArrayList<String> programs = new ArrayList<>(orderedPriorities.values());

    for (String prog : programs) {
      System.out.println(" --------------------------- ");
      System.out.println(" ---------NEW PROG--------- ");
      System.out.println(" --------------------------- ");
      loadFile(prog);
      VM v = new VM(memory, variables);
      memory.removeAll(memory);
      variables.removeAll(variables);
      System.out.println(" --------------------------- ");
    }
  }

  public static void startProgramasNormal() throws FileNotFoundException {
    List<String> programs = Arrays.asList(progs);

    for (String prog : programs) {
      System.out.println(" --------------------------- ");
      System.out.println(" ---------NEW PROG--------- ");
      System.out.println(" --------------------------- ");
      loadFile(prog);
      VM v = new VM(memory, variables);
      memory.removeAll(memory);
      variables.removeAll(variables);
      System.out.println(" --------------------------- ");
    }
  }

}