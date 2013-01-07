package scalatron.botwar.botPlugin
import org.specs2.mutable.Specification

class XySpec extends Specification {

  "Adding" should {
    "produce Xy(3, 5)" in {
      Xy(2, 3) + Xy(1, 2) must beEqualTo(Xy(3, 5))
    }
  }

  "Subtracting" should {
    "produce Xy(2, 3)" in {
      Xy(3, 5) - Xy(1, 2) must beEqualTo(Xy(2, 3))
    }
  }

  "Length" should {
    "equal 5.656854249492381" in {
      Xy(4, 4).length must beEqualTo(5.656854249492381)
    }
  }

  "Distance" should {
    "equal 1" in {
      Xy(1, 1).distanceTo(Xy(2, 1)) must beEqualTo(1)
    }
    "equal 2.23606797749979" in {
      Xy(1, 1).distanceTo(Xy(3, 2)) must beEqualTo(2.23606797749979)
    }
    "equal 1.4142135623730951" in {
      Xy(0, 0).distanceTo(Xy(1, 1)) must beEqualTo(1.4142135623730951)
    }
  }

  "Signum" should {
    "equal Xy(1, 1)" in {
      Xy(2, 2).signum must beEqualTo(Xy(1, 1))
    }
    "equal Xy(-1, 0)" in {
      Xy(-3, 0).signum must beEqualTo(Xy(-1, 0))
    }
  }

}