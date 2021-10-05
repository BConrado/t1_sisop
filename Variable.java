public class Variable {
  public String name; 
  public Integer parametro;	//can be label, data, number

  public Variable(String name, Integer parametro) {  
    this.name = name;
    this.parametro = parametro;
  }

  public Integer getVar(){
    return this.parametro;
  }

  public String toString(){
    return name+"="+parametro;
  }
}
