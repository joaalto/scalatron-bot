package scalatron.botwar.botPlugin

trait ViewHelper {

  def viewStr(rowLength: Int) = "_" * rowLength * rowLength

  def plantAtIndex(index: Int) = viewStr(5).patch(index, "P", 1)
}