package scalatron.botwar.botPlugin
import org.specs2.matcher._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mock._
import org.specs2.mutable._
import org.specs2.execute.PendingUntilFixed
import org.specs2.execute.Pending
import org.specs2.execute.PendingUntilFixed

@RunWith(classOf[JUnitRunner])
class BotSpec extends Specification
  with Mockito
  with CommandHelper
  with Logging {

  val mybot = new Bot

  //  val view = mock[View]
  //  view.offsetToNearestFood returns Some(Xy.north)

  "The bot" should {
    "respond by moving northwest" in {
      //      val view = mock[View]
      //      view.offsetToNearestFood returns Some(Xy.north)

      mybot.respond(command(plantAtIndex(6))) must
        contain("Move(direction=-1:-1)|Set(dir=-1:-1)")

    }
    "not move north" in {

      val view = mock[View]
      val xy = Xy.north
      //      val xy = mock[Xy]
      //      view.offsetToNearestFood returns Some(Xy.north)
      view.cellContainsObstacle(xy) returns true

      view.cellContainsObstacle(xy)
      //      mybot.move(Xy.north, view) must not
      mybot.move(xy, view) must not
      contain("Move(direction=0:-1)|Set(dir=0:-1)")
      //      there was one(view).cellContainsObstacle(xy)
    }.pendingUntilFixed

    "respond by moving southeast" in {
      mybot.respond(command(plantAtIndex(18))) must
        contain("Move(direction=1:1)|Set(dir=1:1)")
    }
    "avoid a wall" in {
      val view = viewStr(5).patch(16, "WWWWWW", 6)
      mybot.move(Xy(0, 1), View(view)) must not contain ("Move(direction=0:1)")
    }
    "avoid an enemy" in {
      val view = viewStr(5).patch(16, "bbbbbb", 6)
      mybot.move(Xy(0, 1), View(view)) must not contain ("Move(direction=0:1)")
    }
  }

  "A mini-bot" should {

    "avoid another mini-bot" in {
      val view = View(miniBotAtIndex(7))
      mybot.move(Xy(0, -1), view) must not contain ("Move(direction=0:-1)")
    }.pendingUntilFixed

    "move towards master" in {
      val view = miniBotView.patch(2, "M", 1)
      log("command: " + miniBotCommand(view))
      mybot.respond(miniBotCommand(view)) must
        contain("Move(direction=1:0)|Set(dir=1:0)")
    }
  }
}