import org.scalatest.flatspec.AnyFlatSpec

class CalculateTaxSpec extends AnyFlatSpec {
  "calculateTax" should "calculate the correct percentage of the input" in {
    val tx = new CalculateTax()
    assert(tx.calculateTax(10000) === 1000)
    assert(tx.calculateTax(0) === 0)
    assert(tx.calculateTax(-100) === 0)
    assert(tx.calculateTax(5798) === 580)
  }
}