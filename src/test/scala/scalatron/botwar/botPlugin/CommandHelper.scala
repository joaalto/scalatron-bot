package scalatron.botwar.botPlugin

trait CommandHelper extends ViewHelper {

  /*
   * React(generation=int,name=string,time=int,
   * 	view=string,energy=string,master=int:int,collision=int:int,slaves=int,...)
   */

  def command(viewRowLength: Int) = "React(generation=0,name=mybot,time=5,view=" +
    viewStr(viewRowLength) + ")"

  def command(viewStr: String) =
    "React(generation=0,energy=1001,name=mybot,time=5,view=" + viewStr + ")"

  def miniBotCommand(viewStr: String) = {
    val miniBotView = viewStr.patch(12, "S", 1)
    command(miniBotView).replace("generation=0", "generation=2").
      patch(6, "master=2:0,", 0)
  }
}