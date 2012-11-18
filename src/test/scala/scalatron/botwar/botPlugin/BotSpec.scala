package scalatron.botwar.botPlugin

import org.specs2.mutable._

class BotSpec extends Specification {

  /*
   * React(generation=int,name=string,time=int,view=string,energy=string,master=int:int,collision=int:int,slaves=int,...)
   */
  def getViewStr(rowLength: Int) = "_" * rowLength * rowLength

  def getView(rowLength: Int) = View(getViewStr(rowLength))

  def getStandardView = getView(7)

  def getCommand(viewRowLength: Int) = "React(generation=0,name=mybot,time=5,view=" +
    getViewStr(viewRowLength) + ",energy=1000,collision=int:int,slaves=int,...)"

  step {
  }

  "View center" should {
    "equal (3, 3)" in {
      getStandardView.center must beEqualTo(Xy(3, 3))
    }
  }

  "View index" should {
    "equal 6" in {
      getView(5).indexFromAbsPos(Xy(1, 1)) must beEqualTo(6)
    }
  }

  "Absolute position" should {
    "equal (2, 3)" in {
      getView(5).absPosFromIndex(17) must beEqualTo(Xy(2, 3))
    }
  }
}