package scalatron.botwar.botPlugin
import scala.Array.canBuildFrom
import scala.collection.mutable.MutableList

class Bot() extends Logging {

  type Params = Map[String, String]

  def respond(input: String): String = {
    val (opcode, params) = CommandParser(input)
    if (opcode.equals("React")) {
      if (minibotFull(params))
        headHome(params)
      else
        seekTargets(params) + launchBot(params)
    } else ""
  }

  def headHome(params: Params) = {
    val masterPos = params("master")
    val view = View(params("view"))
    move(xy(masterPos).signum, view) +
      "|DrawLine(to=" + masterPos + ")"
  }

  def launchBot(params: Params) = {
    if (!isMiniBot(params) && energy(params) > 900)
      "|Spawn(direction=1:1,energy=100)"
    else ""
  }

  def minibotFull(params: Params) =
    isMiniBot(params) && energy(params) > 3000

  def energy(params: Params) = params("energy").toInt

  def isMiniBot(params: Params) =
    params("generation").toInt > 0

  def seekTargets(params: Params) = {
    val view = View(params("view"))
    view.offsetToNearestFood match {
      case Some(offset) => {
        log("offset: " + offset)
        move(offset.signum, view)
      }
      case None =>
        random(params)
    }
  }

  def xy(direction: String) = {
    val xy = direction.split(":").map(_.toInt)
    Xy(xy(0), xy(1))
  }

  def random(params: Params) = {
    val view = View(params("view"))
    params.get("dir") match {
      case Some(direction) =>
        move(xy(direction), view)
      case None =>
        move(Xy.random, view)
    }
  }

  def move(xy: Xy, view: View): String = {
    if (view.cellContainsObstacle(xy))
      move(Xy.random, view)
    else {
      "Move(direction=%1$s:%2$s)|Set(dir=%1$s:%2$s)".format(xy.x, xy.y)
    }
  }

}