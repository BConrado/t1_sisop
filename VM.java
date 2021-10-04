import java.util.ArrayList;
public class VM {
  public int tamMem; 

  public CPU cpu;

  public VM (ArrayList<Word> m, ArrayList<Variable> v) {
    tamMem = m.size() + v.size();



    //ler arquivo e colocar na memoria
    cpu = new CPU(m, v);

  }
}
