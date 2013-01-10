package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification

class BotSpec extends Specification with CommandHelper {

  val mybot = new Bot

  "The bot" should {
    "respond by moving northwest" in {
      mybot.respond(command(plantAtIndex(6))) must
        beEqualTo("Move(direction=-1:-1)")
    }
    "respond by moving southeast" in {
      mybot.respond(command(plantAtIndex(18))) must
        beEqualTo("Move(direction=1:1)")
    }
    "respond by moving south" in {
      mybot.respond(command(5)) must
        beEqualTo("Move(direction=0:1)")
    }
  }

}