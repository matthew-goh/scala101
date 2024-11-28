package guesswho

trait GuessChecker[T] {
  def validateGuess(guessAttribute: Attribute, guessValue: T) : Unit
}

object GuessChecker {
  implicit object BooleanGuess extends GuessChecker[Boolean] {
    def validateGuess(guessAttribute: Attribute, guessValue: Boolean) : Unit = {
      guessAttribute match {
        case Glasses | Moustache | Beard | RosyCheeks | Hat =>
        case _ => throw new Exception("Invalid attribute for a Boolean guess value")
      }
    }
  }

  implicit object GenderGuess extends GuessChecker[GenderEnum.Value] {
    def validateGuess(guessAttribute: Attribute, guessValue: GenderEnum.Value) : Unit = {
      guessAttribute match {
        case Gender =>
        case _ => throw new Exception("Invalid attribute for a gender guess value")
      }
    }
  }

  implicit object ColourGuess extends GuessChecker[Colour.Value] {
    def validateGuess(guessAttribute: Attribute, guessValue: Colour.Value) : Unit = {
      guessAttribute match {
        case HairColour | EyeColour =>
        case _ => throw new Exception("Invalid attribute for a colour guess value")
      }
    }
  }

  // Scala expects an exact match for the type Some[Colour.Value]
  // The compiler does not automatically widen Some[Colour.Value] to Option[Colour.Value] when looking up implicits
  implicit object OptColourGuess extends GuessChecker[Some[Colour.Value]] {
    def validateGuess(guessAttribute: Attribute, guessValue: Some[Colour.Value]) : Unit = {
      guessAttribute match {
        case HatColour =>
        case _ => throw new Exception("Invalid attribute for an Some(Colour) guess value")
      }
    }
  }
}
