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

      view.offsetToNearest(View.Zugar) match {
        case Some(offset) =>
          val unitOffset = offset.signum
          move(unitOffset)
        case None =>
          move(Xy.south)
      }
    } else ""
  }

  def move(xy: Xy) = "Move(direction=" + xy.x + ":" + xy.y + ")"

  def parse(command: String) = {
  }

}

//"React(entity=string,time=int,view=string,energy=int,dx=int,dy=int)"

