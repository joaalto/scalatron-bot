package scalatron.botwar.botPlugin
import scala.Array.canBuildFrom
import scala.collection.mutable.MutableList

class Bot() extends Logging {

  def respond(input: String): String = {
    val (opcode, paramMap) = CommandParser(input)

    if (opcode.equals("React")) {
      approachTarget(paramMap).mkString("|")
    } else ""
  }

  def approachTarget(paramMap: Map[String, String]) = {
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

  def move(xy: Xy, view: View): MutableList[String] = {
    log("Move: " + xy)
    val commands = new MutableList[String]

    if (view.cellContainsObstacle(xy) || xy == Xy.zero)
      move(Xy.random, view)
    else {
      commands += "Move(direction=%s:%s)".format(xy.x, xy.y)
      commands += "Set(dir=%s:%s)".format(xy.x, xy.y)
    }
  }

}