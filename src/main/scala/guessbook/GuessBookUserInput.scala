package guessbook

import guesswho.GenderEnum

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object GuessBookUserInput {
  def main(args: Array[String]): Unit = {
    val game = GuessBook
    println("START OF GAME\n")

    // Randomly select a book for each player
//    val selectedBooks = List(game.selectBook(game.allBooks), game.selectBook(game.allBooks))

    // Each player selects the book that the other one must guess
    val selectedBookP2: Book = selectBookForOpponent(1)
    val selectedBookP1: Book = selectBookForOpponent(2)
    val selectedBooks = List(selectedBookP1, selectedBookP2)

    val booksOnBoard = Array(game.allBooks, game.allBooks)
    var gameOver = false
    var guesser = 2

    while (!gameOver) {
      guesser = guesser % 2 + 1 // 1 -> 2, 2 -> 1
      println(s"\nPlayer $guesser's turn!")
      println(s"Books remaining on P$guesser's board:")
      game.printRemainingBooks(booksOnBoard(guesser-1), maxBooksPerLine = 5)

      val guessType: Int = chooseAttributeOrTitle()
      guessType match {
        case 1 => { // guessing attribute and value
          val guessAttribute: BookAttribute = specifyGuessAttribute()
          guessAttribute match {
            case Honkaku | SchoolOrUniversityStudents | Series | PushkinVertigo => {
              val guessValue: Boolean = specifyBooleanValue()
              booksOnBoard(guesser-1) = game.poseQuestion(selectedBooks(guesser-1), guessAttribute, guessValue, booksOnBoard(guesser-1))
            }
            case AuthorGender => {
              val guessValue: GenderEnum.Value = specifyEnumValue(GenderEnum)
              booksOnBoard(guesser-1) = game.poseQuestion(selectedBooks(guesser-1), guessAttribute, guessValue, booksOnBoard(guesser-1))
            }
            case CountryOfOrigin => {
              val guessValue: Country.Value = specifyEnumValue(Country)
              booksOnBoard(guesser-1) = game.poseQuestion(selectedBooks(guesser-1), guessAttribute, guessValue, booksOnBoard(guesser-1))
            }
            case DetectiveType => {
              val guessValue: Detective.Value = specifyEnumValue(Detective)
              booksOnBoard(guesser-1) = game.poseQuestion(selectedBooks(guesser-1), guessAttribute, guessValue, booksOnBoard(guesser-1))
            }
            case SuspectPoolType => {
              val guessValue: SuspectPool.Value = specifyEnumValue(SuspectPool)
              booksOnBoard(guesser-1) = game.poseQuestion(selectedBooks(guesser-1), guessAttribute, guessValue, booksOnBoard(guesser-1))
            }
          }

          println(s"Books remaining on P$guesser's board:")
          game.printRemainingBooks(booksOnBoard(guesser-1), maxBooksPerLine = 5)
        }
        case 2 => { // guessing book title
          val guessedTitle: String = specifyBookTitle()
          val guessResult = game.guessBookTitle(selectedBooks(guesser-1), guessedTitle, guesser, booksOnBoard(guesser-1))
          booksOnBoard(guesser-1) = guessResult._1
          gameOver = guessResult._2
          if (!gameOver){
            println(s"Books remaining on P$guesser's board:")
            game.printRemainingBooks(booksOnBoard(guesser-1), maxBooksPerLine = 5)
          }
        }
      }
    }

    println("END OF GAME")
  }

  def selectBookForOpponent(guesser: Int, numBlankLines: Int = 25): Book = {
    val game = GuessBook
    val nonGuesser = guesser % 2 + 1
    var matchingBook: Option[Book] = None

    println(s"Player $guesser, select a book for Player $nonGuesser to guess.")
    println(s"Player $nonGuesser, please look away.\n")
    println("The available books are:")
    game.printRemainingBooks(game.allBooks, maxBooksPerLine = 5)
    println()

    while (matchingBook.isEmpty) {
      println(s"Enter the title of the book for Player $nonGuesser to guess:")
      val titleInput = StdIn.readLine()
      matchingBook = game.allBooks.find(book => book.title.toLowerCase == titleInput.toLowerCase)
      if (matchingBook.isEmpty)
        println(s"$titleInput is not the title of a book in the game. Please try again.\n")
    }

    println("Book selected." + "\n" * numBlankLines)
    matchingBook.getOrElse(throw new Exception("Book should have been selected already"))
  }

  def chooseAttributeOrTitle(): Int = {
    var input: String = ""
    var validInput = false

    while (!validInput) {
      println("Enter [1] to guess an attribute value\n" +
        "Enter [2] to guess the title")
      input = StdIn.readLine()
      Try(input.toInt) match {
        case Success(x) if (x == 1 || x == 2) => validInput = true
        case _ => println("Invalid input. Please try again.\n")
      }
    }
    input.toInt
  }

  def specifyBookTitle(): String = {
    val game = GuessBook
    var titleInput: String = ""
    var validInput = false

    while (!validInput) {
      println("Which do you think is the selected book?")
      titleInput = StdIn.readLine()
      val matchingTitle: Option[String] = game.allTitles.find(title => title.toLowerCase == titleInput.toLowerCase)
      matchingTitle match {
        case Some(title) => {
          titleInput = title
          validInput = true
        }
        case None => println(s"$titleInput is not the title of a book in the game. Please try again.\n")
      }
    }
    titleInput
  }

  def specifyGuessAttribute(): BookAttribute = {
    var input: String = ""
    var validInput = false

    while (!validInput) {
      println("Select the attribute you would like to guess:\n" +
        s"Enter [1] for: ${CountryOfOrigin.attributeText}\n" +
        s"Enter [2] for: whether the book is ${Honkaku.attributeText}\n" +
        s"Enter [3] for: ${AuthorGender.attributeText}\n" +
        s"Enter [4] for: whether the book is ${SchoolOrUniversityStudents.attributeText}\n" +
        s"Enter [5] for: ${DetectiveType.attributeText}\n" +
        s"Enter [6] for: whether the book is ${Series.attributeText}\n" +
        s"Enter [7] for: whether the book is ${PushkinVertigo.attributeText}\n" +
        s"Enter [8] for: ${SuspectPoolType.attributeText}")
      input = StdIn.readLine()
      Try(input.toInt) match {
        case Success(x) if (x >= 1 && x <= 8) => validInput = true
        case _ => println("Invalid input. Please try again.\n")
      }
    }

    input.toInt match {
      case 1 => CountryOfOrigin
      case 2 => Honkaku
      case 3 => AuthorGender
      case 4 => SchoolOrUniversityStudents
      case 5 => DetectiveType
      case 6 => Series
      case 7 => PushkinVertigo
      case 8 => SuspectPoolType
    }
  }

  def specifyBooleanValue(): Boolean = {
    var guessInput: String = ""
    var validInput = false

    while (!validInput) {
      println("Enter the guess value ('true' or 'false'):")
      guessInput = StdIn.readLine()
      Try(guessInput.toLowerCase.toBoolean) match {
        case Success(_) => validInput = true
        case Failure(_) => println(s"$guessInput is not a Boolean. Please try again.\n")
      }
    }
    guessInput.toLowerCase.toBoolean
  }

  def specifyEnumValue[E <: Enumeration with BookAttributeEnum](attributeEnum: E): attributeEnum.Value = {
    var input: String = ""
    var validInput = false

    // println(attributeEnum.values.zipWithIndex)
    val selectOptionStr: String = attributeEnum.values.zipWithIndex.map {
      case (value, index) => s"Enter [${index + 1}] for $value"
    }.mkString("\n")

    while (!validInput) {
      println(s"Select a value to guess:\n$selectOptionStr")
      input = StdIn.readLine()
      Try(input.toInt) match {
        case Success(x) if (x >= 1 && x <= attributeEnum.values.size) => validInput = true
        case _ => println("Invalid input. Please try again.\n")
      }
    }

    val selectedIndex = input.toInt - 1
//    println(attributeEnum.values.toList)
    attributeEnum.values.toList(selectedIndex)
  }
}
