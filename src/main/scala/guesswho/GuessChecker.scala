package guesswho

trait GuessChecker[T] {
  def validateGuess(guessAttribute: Attribute, guessValue: T) : Unit
  def printFeedback(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: T): Unit
}

object GuessChecker {
  implicit object BooleanGuessChecker extends GuessChecker[Boolean] {
    def validateGuess(guessAttribute: Attribute, guessValue: Boolean) : Unit = {
      guessAttribute match {
        case Glasses | Moustache | Beard | RosyCheeks | Hat =>
        case _ => throw new Exception("Invalid attribute for a Boolean guess value")
      }
    }

    def printFeedback(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: Boolean): Unit = {
      if (selectedChar.attributes(guessAttribute) == guessValue && guessValue) { // guessed true, actual true
        println(s"Yes, the selected character has ${guessAttribute.attributeText}")
      } else if (selectedChar.attributes(guessAttribute) != guessValue && guessValue) { // guessed true, actual false
        println(s"No, the selected character does not have ${guessAttribute.attributeText}")
      } else if (selectedChar.attributes(guessAttribute) == guessValue && !guessValue) { // guessed false, actual false
        println(s"Yes, the selected character does not have ${guessAttribute.attributeText}")
      } else { // guessed false, actual true
        println(s"No, the selected character does have ${guessAttribute.attributeText}")
      }
    }
  }

  implicit object GenderGuessChecker extends GuessChecker[GenderEnum.Value] {
    def validateGuess(guessAttribute: Attribute, guessValue: GenderEnum.Value) : Unit = {
      guessAttribute match {
        case Gender =>
        case _ => throw new Exception("Invalid attribute for a gender guess value")
      }
    }

    def printFeedback(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: GenderEnum.Value): Unit = {
      if (selectedChar.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected character's ${guessAttribute.attributeText} is $guessValue")
      } else {
        println(s"No, the selected character's' ${guessAttribute.attributeText} is not $guessValue")
      }
    }
  }

  implicit object ColourGuessChecker extends GuessChecker[Colour.Value] {
    def validateGuess(guessAttribute: Attribute, guessValue: Colour.Value) : Unit = {
      guessAttribute match {
        case HairColour | EyeColour =>
        case _ => throw new Exception("Invalid attribute for a colour guess value")
      }
    }

    def printFeedback(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: Colour.Value): Unit = {
      if (selectedChar.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected character's ${guessAttribute.attributeText} is $guessValue")
      } else {
        println(s"No, the selected character's' ${guessAttribute.attributeText} is not $guessValue")
      }
    }
  }

  // Scala expects an exact match for the type Some[Colour.Value]
  // The compiler does not automatically widen Some[Colour.Value] to Option[Colour.Value] when looking up implicits
  implicit object OptColourGuessChecker extends GuessChecker[Some[Colour.Value]] {
    def validateGuess(guessAttribute: Attribute, guessValue: Some[Colour.Value]) : Unit = {
      guessAttribute match {
        case HatColour =>
        case _ => throw new Exception("Invalid attribute for an Some(Colour) guess value")
      }
    }

    def printFeedback(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: Some[Colour.Value]): Unit = {
      if (selectedChar.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected character's ${guessAttribute.attributeText} is ${guessValue.getOrElse("???")}")
      } else {
        println(s"No, the selected character's' ${guessAttribute.attributeText} is not ${guessValue.getOrElse("???")}")
      }
    }
  }
}
