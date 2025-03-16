package guessbook

abstract class BookAttribute {
  val attributeText: String
}

trait BookAttributeEnum

object CountryOfOrigin extends BookAttribute { // enum
  val attributeText = "country of origin"
}
object Honkaku extends BookAttribute { // bool
  val attributeText = "honkaku"
}
object AuthorGender extends BookAttribute { // enum
  val attributeText = "author's gender"
}
object SchoolOrUniversityStudents extends BookAttribute { // bool
  val attributeText = "focused on school or university students"
}
object DetectiveType extends BookAttribute { // enum
  val attributeText = "type of detective"
}
object Series extends BookAttribute { // bool
  val attributeText = "part of a series"
}
object PushkinVertigo extends BookAttribute { // bool
  val attributeText = "published by Pushkin Vertigo"
}
object SuspectPoolType extends BookAttribute { // bool
  val attributeText = "type of suspect pool"
}
