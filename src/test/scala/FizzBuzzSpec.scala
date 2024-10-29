import org.scalatest.flatspec.AnyFlatSpec

class FizzBuzzSpec extends AnyFlatSpec {
  "fizzBuzz" should "check divisibility" in {
    val fb = new FizzBuzz()
    assert(fb.fizzBuzz(3) === "Fizz")
    assert(fb.fizzBuzz(96) === "Fizz")
    assert(fb.fizzBuzz(-5) === "Buzz")
    assert(fb.fizzBuzz(845) === "Buzz")
    assert(fb.fizzBuzz(15) === "FizzBuzz")
    assert(fb.fizzBuzz(780) === "FizzBuzz")
    assert(fb.fizzBuzz(2) === "2")
    assert(fb.fizzBuzz(-7) === "-7")
    assert(fb.fizzBuzz(88) === "88")
    assert(fb.fizzBuzz(0) === "FizzBuzz")
  }
}
