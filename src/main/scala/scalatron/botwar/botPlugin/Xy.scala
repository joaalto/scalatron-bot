package scalatron.botwar.botPlugin
import scala.util.Random

case class Xy(x: Int, y: Int) {
  def +(other: Xy) = new Xy(x + other.x, y + other.y)
  def -(other: Xy) = new Xy(x - other.x, y - other.y)

  def distanceTo(pos: Xy): Double = (this - pos).length
  def length: Double = math.sqrt(x * x + y * y)

  def signum = Xy(x.signum, y.signum)
}

object Xy {
  val zero = Xy(0, 0)

  val north = Xy(0, -1)
  val northEast = Xy(1, -1)
  val east = Xy(1, 0)
  val southEast = Xy(1, 1)
  val south = Xy(0, 1)
  val southWest = Xy(-1, 1)
  val west = Xy(-1, 0)
  val northWest = Xy(-1, -1)

  val directions = Seq(
    north,
    east,
    south,
    west)

  def random() = {
    val random = new Random()
    val x = random.nextInt(3) - 1
    val y = random.nextInt(3) - 1
    Xy(x, y)
  }

  def next(xy: Xy) = {
    println("  " + xy + " " + directions.indexOf(xy))
    if (xy.equals(west) || directions.indexOf(xy) == -1)
      north
    else {
      directions(directions.indexOf(xy) + 1)
    }
  }

}