public enum Opcode {
  add, sub, mult, div, //aritimetic 
  load, store,         //Memory
  brany, brpos, brzero, brneg, //jumps
  syscall;  //system call
}