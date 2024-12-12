package guessbook

object SuspectPool extends Enumeration with BookAttributeEnum {
  val ClosedCircle = Value("closed circle")
  val RestrictedCommunity = Value("restricted community")
  val OpenInvestigation = Value("open investigation")
  val HowCatchEm = Value("how-catch-em")
}
