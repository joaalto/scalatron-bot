package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification

class CommandParserSpec
  extends Specification
  with CommandHelper {

  val (opcode, paramMap) = CommandParser(command(5))

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