package guessbook

import guessbook.BookInfo._
import guesswho.GenderEnum
import org.scalatest.matchers.should._
import org.scalatest.wordspec.AnyWordSpec

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class GuessBookUserInputSpec extends AnyWordSpec with Matchers {

  "chooseGameMode()" should {
    "return an Int 2" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "2"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.chooseGameMode() == 2)
        }
      }
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "aaa\n1"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.chooseAttributeOrTitle() == 1)
        }
      }
      outputStream.toString should include ("Invalid input. Please try again.")
    }
  }

  "selectBookForOpponent()" should {
    "print the correct player numbers and return the selected book" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "the decagon house murders"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.selectBookForOpponent(guesser = 1) == TheDecagonHouseMurders)
        }
      }

      val printedText = outputStream.toString
      printedText should include ("Player 1, select a book for Player 2 to guess.")
      printedText should include ("Player 2, please look away.")
      printedText should include ("Enter the title of the book for Player 2 to guess:")
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "NotABook\nthe mill house murders"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.selectBookForOpponent(guesser = 2) == TheMillHouseMurders)
        }
      }
      outputStream.toString should include ("NotABook is not the title of a book in the game. Please try again.")
    }
  }

  "chooseAttributeOrTitle()" should {
    "return an Int 1" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "1"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.chooseAttributeOrTitle() == 1)
        }
      }
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "3\n2" // user first enters 3, then 2 when prompted again
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.chooseAttributeOrTitle() == 2)
        }
      }
      outputStream.toString should include ("Invalid input. Please try again.")
    }
  }

  "specifyBookTitle()" should {
    "return the title of a book" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "the decagon house murders"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyBookTitle() == TheDecagonHouseMurders.title)
        }
      }
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "NotABook\nthe mill house murders"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyBookTitle() == TheMillHouseMurders.title)
        }
      }
      outputStream.toString should include ("NotABook is not the title of a book in the game. Please try again.")
    }
  }

  "specifyGuessAttribute()" should {
    "return a BookAttribute" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "7"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyGuessAttribute() == PushkinVertigo)
        }
      }
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "abc\n8"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyGuessAttribute() == SuspectPoolType)
        }
      }
      outputStream.toString should include ("Invalid input. Please try again.")
    }
  }

  "specifyBooleanValue()" should {
    "return a Boolean" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "True"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyBooleanValue())
        }
      }
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "T\nfalse"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(!GuessBookUserInput.specifyBooleanValue())
        }
      }
      outputStream.toString should include ("T is not a Boolean. Please try again.")
    }
  }

  "specifyEnumValue()" should {
    "print the options correctly and return a Gender value" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "2"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyEnumValue(GenderEnum) == GenderEnum.Female)
        }
      }
      outputStream.toString should include ("Enter [1] for Male\nEnter [2] for Female")
    }

    "print the options correctly and return a Country enum value" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "1"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyEnumValue(Country) == Country.Japan)
        }
      }
      outputStream.toString should include ("Enter [1] for Japan\n" +
        "Enter [2] for UK\n" +
        "Enter [3] for Taiwan\n" +
        "Enter [4] for India")
    }

    "print the options correctly and return a Detective enum value" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "4"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyEnumValue(Detective) == Detective.OrdinaryPerson)
        }
      }
      outputStream.toString should include ("Enter [1] for police\n" +
        "Enter [2] for private professional\n" +
        "Enter [3] for amateur\n" +
        "Enter [4] for ordinary person / none at all")
    }

    "print the options correctly and return a SuspectPool enum value" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "3"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)

      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyEnumValue(SuspectPool) == SuspectPool.OpenInvestigation)
        }
      }
      outputStream.toString should include ("Enter [1] for closed circle\n" +
        "Enter [2] for restricted community\n" +
        "Enter [3] for open investigation\n" +
        "Enter [4] for how-catch-em")
    }

    "prompt for input again if invalid input is received" in {
      val outputStream = new ByteArrayOutputStream()
      val userInput = "abc\n3"
      val inputStream = new ByteArrayInputStream(userInput.getBytes)
      Console.withIn(inputStream) {
        Console.withOut(outputStream) {
          assert(GuessBookUserInput.specifyEnumValue(Country) == Country.Taiwan)
        }
      }
      outputStream.toString should include ("Invalid input. Please try again.")
    }
  }
}
