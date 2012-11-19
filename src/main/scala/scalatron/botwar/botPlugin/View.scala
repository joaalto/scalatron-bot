package scalatron.botwar.botPlugin

case class View(cells: String) {
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

  def offsetToNearest(c: Char) = {
    val relativePositions =
      cells
        .view
        .zipWithIndex
        .filter(_._1 == c)
        .map(p => relPosFromIndex(p._2))
    if (relativePositions.isEmpty)
      None
    else
      Some(relativePositions.minBy(_.length))
  }
}
