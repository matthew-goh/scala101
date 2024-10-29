import org.scalatest.funsuite.AnyFunSuite

//import scala.runtime.stdLibPatches.Predef.assert

class CubeCalculatorTest extends AnyFunSuite{
  test("CubeCalculator.cube 3 should be 27") {
    assert(CubeCalculator.cube(3) === 27)
  }
  test("CubeCalculator.cube 0 should be 0") {
    assert(CubeCalculator.cube(0) === 0)
  }
}
