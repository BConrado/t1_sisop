import java.util.ArrayList;
public class VM {
  public int prioridade; 
  public int arrivelTime;
  public CPU cpu;

  public VM (ArrayList<Word> m, ArrayList<Variable> v) {

    //ler arquivo e colocar na memoria
    cpu = new CPU(m, v);

  }
}
