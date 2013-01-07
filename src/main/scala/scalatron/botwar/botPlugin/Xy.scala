package scalatron.botwar.botPlugin

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