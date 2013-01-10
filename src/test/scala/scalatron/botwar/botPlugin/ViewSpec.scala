package scalatron.botwar.botPlugin

import org.specs2.mutable._

class ViewSpec extends Specification with ViewHelper {

  val plant = 'P'

  def standardView = view(7)

  def view(rowLength: Int) = View(viewStr(rowLength))

  def plantAtIndex(index: Int) = viewStr(5).patch(index, "P", 1)

  "View size" should {
    "equal 7" in {
      standardView.size must beEqualTo(7)
    }
  }

  "View center" should {
    "equal (3, 3)" in {
      standardView.center must beEqualTo(Xy(3, 3))
    }
  }

  "View index" should {
    "equal 6" in {
      view(5).indexFromAbsPos(Xy(1, 1)) must beEqualTo(6)
    }
  }

  "Absolute position" should {
    "equal (2, 3)" in {
      view(5).absPosFromIndex(17) must beEqualTo(Xy(2, 3))
    }
    "equal (3, 1)" in {
      view(5).absPosFromRelPos(Xy(1, -1)) must beEqualTo(Xy(3, 1))
    }
  }

  "Cell at absolute position" should {
    "contain a plant" in {
      View(plantAtIndex(6)).cellAtAbsPos(Xy(1, 1)) must beEqualTo(plant)
    }
  }

  "View index" should {
    "equal 16" in {
      view(5).indexFromRelPos(Xy(-1, 1)) must beEqualTo(16)
    }
  }

  "Relative position" should {
    "equal (0, 2)" in {
      view(5).relPosFromAbsPos(Xy(2, 4)) must beEqualTo(Xy(0, 2))
    }
    "equal (2, 1)" in {
      view(5).relPosFromIndex(19) must beEqualTo(Xy(2, 1))
    }
  }

  "Cell at relative position" should {
    "contain a plant" in {
      View(plantAtIndex(6)).cellAtRelPos(Xy(-1, -1)) must beEqualTo(plant)
    }
  }

  "Offset to nearest plant" should {
    "be (-1, -1)" in {
      View(plantAtIndex(6)).offsetToNearest('P') must beEqualTo(Some(Xy(-1, -1)))
    }
  }
}