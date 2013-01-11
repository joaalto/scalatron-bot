package scalatron.botwar.botPlugin
import scala.collection.immutable.StringOps
import scala.util.parsing.combinator.RegexParsers
import scala.collection.Seq

/**
 * This is the factory class that the server attempts to instantiate after the plugin JAR
 *  file was loaded via the class loader. This class must only exist in the plugin, not on
 *  the server.
 */
class ControlFunctionFactory {
  def create: (String => String) = new Bot().respond _
}

class Bot() extends Logging {

  def respond(input: String): String = {
    val (opcode, paramMap) = CommandParser(input)

    if (opcode.equals("React"))
      approachTarget(View(paramMap("view")))
    else ""
  }

  def approachTarget(view: View) =
    view.offsetToNearestFood match {
      case Some(offset) =>
        log("Approaching.")
        move(offset.signum, view)
      case None =>
        move(Xy.random, view)
    }

  def move(xy: Xy, view: View): String = {
    log("Move: " + xy)

    if (view.cellContainsObstacle(xy))
      move(Xy.random, view)
    else
      "Move(direction=%s:%s)".format(xy.x, xy.y)
  }

}