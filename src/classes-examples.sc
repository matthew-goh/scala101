class SpaceStation{
  // class members
  val shape: String = "Sphere"
  val hasSignificantWeaknessThatCanBeExploitedByHero: Boolean = true
}

val deathStar: SpaceStation = new SpaceStation()
deathStar.shape

// Parameters for class members
class Apple(val brand: String, val colour: String)
val green: String = "Green" // string variable to pass into new instance of Apple class
val grannySmith: Apple = new Apple("Smith", green) // brand is "Smith", colour is "Green"
grannySmith.colour

// Only need 1 copy of an object (not multiple instances of a class)
// no params, globally available, no need to use new
object MathematicalConstants{
  val pi: Double = 3.1415926535
  val e: Double = 2.7182818284
  val goldenRatio: Double = 1.6180339
  val euler: Double = 0.57721
}
MathematicalConstants.goldenRatio
MathematicalConstants.euler


// Inheritance
class ChocolateBar{
  val colour: String = "Brown"
  val hasNuts: Boolean = false
  val isNice: Boolean = true
}

// Traits can be mixed into a class using the with keyword
// Can't create an instance of a trait
trait Filling{
  val filling: String // can have unimplemented members
}

trait Celebration{
  val hasMiniature: Boolean = true
}

// can extend only one class but have many traits
class Bounty extends ChocolateBar with Filling with Celebration{
  override val hasNuts: Boolean = true
  // colour and isNice inherited

  val filling = "Coconut"
  // hasMiniature = true
}
