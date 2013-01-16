package scalatron.botwar.botPlugin
import scala.Array.canBuildFrom
import scala.collection.mutable.MutableList

class Bot() extends Logging {

  def respond(input: String): String = {
    val (opcode, paramMap) = CommandParser(input)
    if (opcode.equals("React")) {
      seekTargets(paramMap) + launchBot(paramMap)
    } else ""
  }

  def launchBot(paramMap: Map[String, String]) = {
    paramMap.get("time") match {
      case Some(time) =>
        if (time.toInt > 0 && time.toInt % 100 == 0) {
          "|Spawn(direction=1:1,energy=100)"
        } else ""
      case None => ""
    }
  }

  def seekTargets(paramMap: Map[String, String]) = {
    val view = View(paramMap("view"))
    view.offsetToNearestFood match {
      case Some(offset) =>
        move(offset.signum, view)
      case None =>
        random(paramMap)
    }
  }

  def random(paramMap: Map[String, String]) = {
    val view = View(paramMap("view"))
    paramMap.get("dir") match {
      case Some(direction) =>
        log("Direction: " + direction)
        val xy = direction.split(":").map(_.toInt)
        move(Xy(xy(0), xy(1)), view)
      case None =>
        move(Xy.random, view)
    }
  }

  def move(xy: Xy, view: View): String = {
    log("Move: " + xy)
    if (view.cellContainsObstacle(xy))
      move(Xy.random, view)
    else {
      "Move(direction=%1$s:%2$s)|Set(dir=%1$s:%2$s)".format(xy.x, xy.y)
    }
  }

}