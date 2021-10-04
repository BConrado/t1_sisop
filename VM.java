public class VM {
  public int tamMem; 
  public Word[] m;
  public Variable[] v;
  public CPU cpu;

  public VM () {
    //ler tamanho da memoria de acordo com o arquivo
    tamMem = 1024; // TODO: MUDAR PARA DINAMICO
    //define cpu com tamanho da memoria
    m = new Word[tamMem];

    //adicionar valores ao M e V 


    //ler arquivo e colocar na memoria
    cpu = new CPU(m, v);

  }
}
