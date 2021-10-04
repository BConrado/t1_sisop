public class Word { 	
  public Opcode opc; 
  public String parametro;	//can be label, data, number

  public Word(Opcode opc, String parametro) {  
    this.opc = opc;
    this.parametro = parametro;
  }
}