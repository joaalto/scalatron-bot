package scalatron.botwar.botPlugin

/*
 * Example command string:
 * 
 * React(generation=int,name=string,time=int,
 * 	view=string,energy=string,master=int:int,collision=int:int,slaves=int,...)
 */
object CommandParser {
  def apply(command: String) = {
    val tokens = command.split('(')
    val opcode = tokens(0)
    val paramMap = tokens(1).dropRight(1).split(',')
      .map(_.split('='))
      .map(a => (a(0), a(1)))
      .toMap

    (opcode, paramMap)
  }
}
