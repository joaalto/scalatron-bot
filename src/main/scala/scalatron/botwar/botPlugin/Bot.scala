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

class Bot() {
  // this method is invoked by the game server to interact with the plug-in.
  // The input will be a string of the format "Opcode(param=value,param=value,...)"
  // The output must be a string that is empty or also "Opcode(param=value,param=value,...)"

  def n = 0

  def respond(input: String): String = {
    val (opcode, paramMap) = CommandParser(input)
    if (opcode.equals("React")) {
      val view = View(paramMap("view"))
      view.offsetToNearest('P') match {
        case Some(offset) =>
          val unitOffset = offset.signum
          "Move(direction=" + unitOffset.x + ":" + unitOffset.y + ")"
        case None =>
          ""
      }
    } else ""
  }

  def parse(command: String) = {
  }

}

//"React(entity=string,time=int,view=string,energy=int,dx=int,dy=int)"
object CommandParser {
  def apply(command: String) = {
    val tokens = command.split('(')
    val opcode = tokens(0)
    val paramMap = tokens(1).dropRight(1).split(',')
      .map(_.split('='))
      .map(a => (a(0), a(1)))
      .toMap

    (opcode, paramMap)
  }
}

case class Xy(x: Int, y: Int) {
  def +(other: Xy) = new Xy(x + other.x, y + other.y)
  def -(other: Xy) = new Xy(x - other.x, y - other.y)

  def distanceTo(pos: Xy): Double = (this - pos).length
  def length: Double = math.sqrt(x * x + y * y)

  def signum = Xy(x.signum, y.signum)

}

object Xy {
  val zero = Xy(0, 0)

  val nortEast = Xy(1, -1)
  val east = Xy(1, 0)
  val southEast = Xy(1, 1)
  val south = Xy(0, 1)
  val southWest = Xy(-1, 1)
  val west = Xy(-1, 0)
  val northWest = Xy(-1, -1)
}
