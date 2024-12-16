package guessbook

import guesswho.GenderEnum

trait BookGuessChecker[T] {
  def validateGuessAttribute(guessAttribute: BookAttribute) : Unit

  // default implementation, overridden for certain guessValue types
  def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: T): Unit = {
    if (selectedBook.attributes(guessAttribute) == guessValue) {
      println(s"Yes, the selected book's ${guessAttribute.attributeText} is $guessValue")
    } else {
      println(s"No, the selected book's ${guessAttribute.attributeText} is not $guessValue")
    }
  }
}

object BookGuessChecker {
  implicit object BooleanGuessChecker extends BookGuessChecker[Boolean] {
    def validateGuessAttribute(guessAttribute: BookAttribute) : Unit = {
      guessAttribute match {
        case Honkaku | SchoolOrUniversityStudents | Series | PushkinVertigo => ()
        case _ => throw new Exception(s"Invalid attribute for a Boolean guess value")
      }
    }

    override def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: Boolean): Unit = {
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
    def validateGuessAttribute(guessAttribute: BookAttribute) : Unit = {
      guessAttribute match {
        case AuthorGender => ()
        case _ => throw new Exception(s"Invalid attribute for a gender guess value")
      }
    }

    override def printFeedback(selectedBook: Book, guessAttribute: BookAttribute, guessValue: GenderEnum.Value): Unit = {
      if (selectedBook.attributes(guessAttribute) == guessValue) {
        println(s"Yes, the author of the selected book is ${guessValue.toString.toLowerCase}")
      } else {
        println(s"No, the author of the selected book is not ${guessValue.toString.toLowerCase}")
      }
    }
  }

  implicit object CountryGuessChecker extends BookGuessChecker[Country.Value] {
    def validateGuessAttribute(guessAttribute: BookAttribute) : Unit = {
      guessAttribute match {
        case CountryOfOrigin => ()
        case _ => throw new Exception(s"Invalid attribute for a country guess value")
      }
    }
  }

  implicit object DetectiveGuessChecker extends BookGuessChecker[Detective.Value] {
    def validateGuessAttribute(guessAttribute: BookAttribute) : Unit = {
      guessAttribute match {
        case DetectiveType => ()
        case _ => throw new Exception(s"Invalid attribute for a detective type guess value")
      }
    }
  }

  implicit object SuspectPoolGuessChecker extends BookGuessChecker[SuspectPool.Value] {
    def validateGuessAttribute(guessAttribute: BookAttribute) : Unit = {
      guessAttribute match {
        case SuspectPoolType => ()
        case _ => throw new Exception(s"Invalid attribute for a suspect pool guess value")
      }
    }
  }
}
