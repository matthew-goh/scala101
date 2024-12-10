package guessbook

import guesswho.GenderEnum

trait BookGuessChecker[T] {
  def validateGuess(guessAttribute: BookAttribute, guessValue: T) : Unit
  def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: T): Unit
}

object BookGuessChecker {
  implicit object BooleanGuessChecker extends BookGuessChecker[Boolean] {
    def validateGuess(guessAttribute: BookAttribute, guessValue: Boolean) : Unit = {
      guessAttribute match {
        case Honkaku | SchoolOrUniversityStudents | Series | PushkinVertigo =>
        case _ => throw new Exception("Invalid attribute for a Boolean guess value")
      }
    }

    def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: Boolean): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue && guessValue) { // guessed true, actual true
        println(s"Yes, the selected book is ${guessAttribute.attributeText}")
      } else if (selectedBook.attributes(guessAttribute) != guessValue && guessValue) { // guessed true, actual false
        println(s"No, the selected book is not ${guessAttribute.attributeText}")
      } else if (selectedBook.attributes(guessAttribute) == guessValue && !guessValue) { // guessed false, actual false
        println(s"Yes, the selected book is not ${guessAttribute.attributeText}")
      } else { // guessed false, actual true
        println(s"No, the selected book is ${guessAttribute.attributeText}")
      }
    }
  }

  implicit object GenderGuessChecker extends BookGuessChecker[GenderEnum.Value] {
    def validateGuess(guessAttribute: BookAttribute, guessValue: GenderEnum.Value) : Unit = {
      guessAttribute match {
        case AuthorGender =>
        case _ => throw new Exception("Invalid attribute for a gender guess value")
      }
    }

    def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: GenderEnum.Value): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the author of the selected book is ${guessValue.toString.toLowerCase}")
      } else {
        println(s"No, the author of the selected book is not ${guessValue.toString.toLowerCase}")
      }
    }
  }

  implicit object CountryGuessChecker extends BookGuessChecker[Country.Value] {
    def validateGuess(guessAttribute: BookAttribute, guessValue: Country.Value) : Unit = {
      guessAttribute match {
        case CountryOfOrigin =>
        case _ => throw new Exception("Invalid attribute for a country guess value")
      }
    }

    def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: Country.Value): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected book's ${guessAttribute.attributeText} is $guessValue")
      } else {
        println(s"No, the selected book's ${guessAttribute.attributeText} is not $guessValue")
      }
    }
  }

  implicit object DetectiveGuessChecker extends BookGuessChecker[Detective.Value] {
    def validateGuess(guessAttribute: BookAttribute, guessValue: Detective.Value) : Unit = {
      guessAttribute match {
        case DetectiveType =>
        case _ => throw new Exception("Invalid attribute for a detective type guess value")
      }
    }

    def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: Detective.Value): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected book's ${guessAttribute.attributeText} is $guessValue")
      } else {
        println(s"No, the selected book's ${guessAttribute.attributeText} is not $guessValue")
      }
    }
  }

  implicit object SuspectPoolGuessChecker extends BookGuessChecker[SuspectPool.Value] {
    def validateGuess(guessAttribute: BookAttribute, guessValue: SuspectPool.Value) : Unit = {
      guessAttribute match {
        case SuspectPoolType =>
        case _ => throw new Exception("Invalid attribute for a suspect pool guess value")
      }
    }

    def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: SuspectPool.Value): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the selected book's ${guessAttribute.attributeText} is $guessValue")
      } else {
        println(s"No, the selected book's ${guessAttribute.attributeText} is not $guessValue")
      }
    }
  }
}
