package scalatron.botwar.botPlugin

trait ViewHelper {

  def viewStr(rowLength: Int) = "_" * rowLength * rowLength

  def plantAtIndex(index: Int) = viewStr(5).patch(index, "P", 1)

  def miniBotAtIndex(index: Int) = miniBotView.patch(index, "S", 1)

  def miniBotView = viewStr(5).patch(12, "S", 1)

}