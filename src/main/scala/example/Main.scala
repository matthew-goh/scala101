package example

object Main {
  def run() = {
    val ages = Seq(42, 61, 29, 64)
    println(s"The oldest person is ${ages.max}")
  }
}
