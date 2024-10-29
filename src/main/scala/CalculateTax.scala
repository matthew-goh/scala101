import scala.math._

class CalculateTax {
  def calculateTax(m: Int): Int = {
    if (m < 0) {
      0
    } else if (m <= 10000){
      round(m * 0.1).toInt
    } else if (m <= 50000){
      round(m * 0.15).toInt
    } else if (m <= 100000){
      round(m * 0.2).toInt
    } else {
      round(m * 0.4).toInt
    }
  }
}
