package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification

class CommandParserSpec extends Specification with ViewHelper {

  /*
   * React(generation=int,name=string,time=int,
   * 	view=string,energy=string,master=int:int,collision=int:int,slaves=int,...)
   */

  def getCommand(viewRowLength: Int) = "React(generation=0,name=mybot,time=5,view=" +
    viewStr(viewRowLength) + ")"

  val (opcode, paramMap) = CommandParser(getCommand(5))

  "Server input" should {
    "contain opcode" in {
      opcode must beEqualTo("React")
    }
    "contain bot name" in {
      paramMap("name") must beEqualTo("mybot")
    }
    "contain time" in {
      paramMap("time") must beEqualTo("5")
    }
    "contain view" in {
      paramMap("view") must beEqualTo(viewStr(5))
    }
  }
}