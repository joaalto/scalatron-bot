package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification
import org.specs2.matcher.MustMatchers

class BotSpec extends Specification
  with CommandHelper
  with Logging {

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
    "avoid a wall" in {
      val view = viewStr(5).patch(16, "WWWWWW", 6)
      mybot.move(Xy(0, 1), View(view)) !== ("Move(direction=0:1)")
    }
    "avoid an enemy" in {
      val view = viewStr(5).patch(16, "bbbbbb", 6)
      mybot.move(Xy(0, 1), View(view)) !== ("Move(direction=0:1)")
    }
  }

}