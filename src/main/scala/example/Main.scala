package example

object Main extends App{
  def run(): Unit = {
    val ages = Seq(42, 61, 29, 64)
    println(s"The oldest person is ${ages.max}")
  }

  run()
}
