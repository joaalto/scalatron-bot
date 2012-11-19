package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification

class CommandParserSpec extends Specification with ViewHelper {

  /*
   * React(generation=int,name=string,time=int,
   * 	view=string,energy=string,master=int:int,collision=int:int,slaves=int,...)
   */

  def getCommand(viewRowLength: Int) = "React(generation=0,name=mybot,time=5,view=" +
    viewStr(viewRowLength) + ",energy=1000,collision=int:int,slaves=int,...)"

}