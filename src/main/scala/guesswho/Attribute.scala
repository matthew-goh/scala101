package guesswho

// class Attribute, each attribute extends this class, then guessAttribute must be of type Attribute (extra layer of type safety)
abstract class Attribute {
  val attributeText: String
}

object Gender extends Attribute {
  val attributeText = "gender"
}
object Glasses extends Attribute {
  val attributeText = "glasses"
}
object Moustache extends Attribute {
  val attributeText = "a moustache"
}
object Beard extends Attribute {
  val attributeText = "a beard"
}
object RosyCheeks extends Attribute {
  val attributeText = "rosy cheeks"
}
object HairColour extends Attribute {
  val attributeText = "hair colour"
}
object EyeColour extends Attribute {
  val attributeText = "eye colour"
}
object Hat extends Attribute {
  val attributeText = "a hat"
}
object HatColour extends Attribute {
  val attributeText = "hat colour"
}
