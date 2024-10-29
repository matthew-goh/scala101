import org.scalatest.flatspec.AnyFlatSpec

class TwoNumbersSpec extends AnyFlatSpec {
  "add" should "add numbers" in {
    val nums = new TwoNumbers(1, 2)
    assert(nums.add === 3)
  }
}