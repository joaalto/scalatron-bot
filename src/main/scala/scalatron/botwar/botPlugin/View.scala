package scalatron.botwar.botPlugin

object View {
  val Zugar = 'P'
  val Toxifera = 'p'
  val Fluppet = 'B'
  val Zorg = 'b'
  val Wall = 'W'
  val EnemyBot = 'm'
  val EnemySlave = 's'
  val Master = 'M'
  val Slave = 'S'

  val food = Seq(Zugar, Fluppet)
  val obstacles = Seq(Zorg, Wall, EnemyBot, EnemySlave, Toxifera)
}

/**
 * The view string forms a square.
 * Absolute position is calculated relative to the top left corner,
 * and relative position is calculated similarly relative to the bot
 * in the center of the view.
 */
case class View(cells: String) extends Logging {
  val size = math.sqrt(cells.length()).intValue
  val center = Xy(size / 2, size / 2)

  def indexFromAbsPos(absPos: Xy) = absPos.x + absPos.y * size
  def absPosFromIndex(index: Int) = Xy(index % size, index / size)
  def absPosFromRelPos(relPos: Xy) = relPos + center

  def cellAtAbsPos(absPos: Xy) = cells.charAt(indexFromAbsPos(absPos))

  def indexFromRelPos(relPos: Xy) = indexFromAbsPos(absPosFromRelPos(relPos))
  def relPosFromAbsPos(absPos: Xy) = absPos - center
  def relPosFromIndex(index: Int) = relPosFromAbsPos(absPosFromIndex(index))
  def cellAtRelPos(relPos: Xy) = cells.charAt(indexFromRelPos(relPos))

  def cellContainsObstacle(relPos: Xy) =
    cellContainsDanger(relPos) //|| cellContainsMiniBot(relPos)

  def cellContainsDanger(relPos: Xy) =
    View.obstacles.exists(_ == cellAtRelPos(relPos))

  def cellContainsMiniBot(relPos: Xy) =
    isMiniBot && cellAtRelPos(relPos) == View.Slave

  def isMiniBot = cellAtAbsPos(center) == View.Slave

  def offsetToNearestFood: Option[Xy] = {
    val items = View.food.flatMap(offsetToNearest(_))
    //    log("Nearest visible food items: " + items)

    if (items.nonEmpty)
      Option(items.minBy(_.length))
    else None
  }

  def offsetToNearest(target: Char): Option[Xy] = {

    val relativePositions = for (
      cellWithIndex <- cells.view.zipWithIndex;
      if cellWithIndex._1 == target
    ) yield relPosFromIndex(cellWithIndex._2)

    if (relativePositions.isEmpty)
      None
    else
      Some(relativePositions.minBy(_.length))
  }

}
