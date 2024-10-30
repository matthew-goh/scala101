import org.scalatest.flatspec.AnyFlatSpec

class CalculateTaxSpec extends AnyFlatSpec {
  "calculateTax" should "output zero for negative inputs" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(-896879) === 0)
    assert(tx.calculateTax(-100) === 0)
  }

  "calculateTax" should "calculate 10% of the input" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(0) === 0)
    assert(tx.calculateTax(10000) === 1000)
    assert(tx.calculateTax(5798) === 580)
    assert(tx.calculateTax(35) === 4)
  }

  "calculateTax" should "calculate 15% of the input" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(18000) === 2700)
    assert(tx.calculateTax(37856) === 5678)
    assert(tx.calculateTax(45925) === 6889)
  }

  "calculateTax" should "calculate 20% of the input" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(50001) === 10000)
    assert(tx.calculateTax(99728) === 19946)
  }

  "calculateTax" should "calculate 40% of the input" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(500000000) === 200000000)
    assert(tx.calculateTax(999999) === 400000)
    assert(tx.calculateTax(105096) === 42038)
  }
}