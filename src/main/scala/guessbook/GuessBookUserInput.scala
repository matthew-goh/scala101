package guessbook

import guesswho.GenderEnum

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object GuessBookUserInput {
  def main(args: Array[String]): Unit = {
    val game = GuessBook
    println("START OF GAME\n")

    println("First, select a game mode.")
    val gameMode: Int = chooseGameMode()
    val selectedBooks = gameMode match {
      case 1 => { // Randomly select a book for each player
        println("Books have been selected randomly.")
        List(game.selectBook(game.allBooks), game.selectBook(game.allBooks))
      }
      case 2 => { // Each player selects the book that the other one must guess
        println()
        val selectedBookP2: Book = selectBookForOpponent(1)
        val selectedBookP1: Book = selectBookForOpponent(2)
        List(selectedBookP1, selectedBookP2)
      }
    }

    val booksOnBoard = Array(game.allBooks, game.allBooks)
    var gameOver = false
    var guesser = 2

    while (!gameOver) {
      guesser = guesser % 2 + 1 // 1 -> 2, 2 -> 1
      val guesserIndex = guesser - 1
      println(s"\nPlayer $guesser's turn!")
      println(s"Books remaining on P$guesser's board:")
      game.printRemainingBooks(booksOnBoard(guesserIndex))

      val guessType: Int = chooseAttributeOrTitle()
      guessType match {
        case 1 => { // guessing attribute and value
          val guessAttribute: BookAttribute = specifyGuessAttribute()
          guessAttribute match {
            case Honkaku | SchoolOrUniversityStudents | Series | PushkinVertigo => {
              val guessValue: Boolean = specifyBooleanValue()
              booksOnBoard(guesserIndex) = game.poseQuestion(selectedBooks(guesserIndex), guessAttribute, guessValue, booksOnBoard(guesserIndex))
            }
            case AuthorGender => {
              val guessValue: GenderEnum.Value = specifyEnumValue(GenderEnum)
              booksOnBoard(guesserIndex) = game.poseQuestion(selectedBooks(guesserIndex), guessAttribute, guessValue, booksOnBoard(guesserIndex))
            }
            case CountryOfOrigin => {
              val guessValue: Country.Value = specifyEnumValue(Country)
              booksOnBoard(guesserIndex) = game.poseQuestion(selectedBooks(guesserIndex), guessAttribute, guessValue, booksOnBoard(guesserIndex))
            }
            case DetectiveType => {
              val guessValue: Detective.Value = specifyEnumValue(Detective)
              booksOnBoard(guesserIndex) = game.poseQuestion(selectedBooks(guesserIndex), guessAttribute, guessValue, booksOnBoard(guesserIndex))
            }
            case SuspectPoolType => {
              val guessValue: SuspectPool.Value = specifyEnumValue(SuspectPool)
              booksOnBoard(guesserIndex) = game.poseQuestion(selectedBooks(guesserIndex), guessAttribute, guessValue, booksOnBoard(guesserIndex))
            }
          }

          println(s"Books remaining on P$guesser's board:")
          game.printRemainingBooks(booksOnBoard(guesserIndex))
        }
        case 2 => { // guessing book title
          val guessedTitle: String = specifyBookTitle()
          val guessResult: (Seq[Book], Boolean) = game.guessBookTitle(selectedBooks(guesserIndex), guessedTitle, guesser, booksOnBoard(guesserIndex))
          booksOnBoard(guesserIndex) = guessResult._1
          gameOver = guessResult._2
          if (!gameOver){
            println(s"Books remaining on P$guesser's board:")
            game.printRemainingBooks(booksOnBoard(guesserIndex))
          }
        }
      }
    }

    println("END OF GAME")
  }

  def selectBookForOpponent(guesser: Int, numBlankLines: Int = 25): Book = {
    val game = GuessBook
    val nonGuesser = guesser % 2 + 1

    println(s"Player $guesser, select a book for Player $nonGuesser to guess.")
    println(s"Player $nonGuesser, please look away.\n")
    println("The available books are:")
    game.printRemainingBooks(game.allBooks)
    println()

    @tailrec
    def getBookInGame(nonGuesser: Int): Book = {
      println(s"Enter the title of the book for Player $nonGuesser to guess:")
      val titleInput = StdIn.readLine()
      val matchingBook = game.allBooks.find(book => book.title.toLowerCase == titleInput.toLowerCase)
      matchingBook match {
        case Some(book) => {
          println("Book selected." + "\n" * numBlankLines)
          book
        }
        case None => {
          println(s"$titleInput is not the title of a book in the game. Please try again.\n")
          getBookInGame(nonGuesser)
        }
      }
    }

    getBookInGame(nonGuesser)
  }

  @tailrec
  def chooseGameMode(): Int = {
    println("Enter [1] for each player's selected book to be drawn randomly\n" +
      "Enter [2] for each player to choose the book for their opponent to guess")

    val input = StdIn.readLine()
    Try(input.toInt) match {
      case Success(x) if (x == 1 || x == 2) => x
      case _ => {
        println("Invalid input. Please try again.\n")
        chooseGameMode()
      }
    }
  }

  @tailrec
  def chooseAttributeOrTitle(): Int = {
    println("Enter [1] to guess an attribute value\n" +
      "Enter [2] to guess the title")

    val input = StdIn.readLine()
    Try(input.toInt) match {
      case Success(x) if (x == 1 || x == 2) => x
      case _ => {
        println("Invalid input. Please try again.\n")
        chooseAttributeOrTitle()
      }
    }
  }

  def specifyBookTitle(): String = {
    val game = GuessBook
    println("Which do you think is the selected book?")

    val titleInput = StdIn.readLine()
    val matchingTitle: Option[String] = game.allTitles.find(title => title.toLowerCase == titleInput.toLowerCase)
    matchingTitle match {
      case Some(title) => title
      case None => {
        println(s"$titleInput is not the title of a book in the game. Please try again.\n")
        specifyBookTitle()
      }
    }
  }

  @tailrec
  def specifyGuessAttribute(): BookAttribute = {
    println("Select the attribute you would like to guess:\n" +
      s"Enter [1] for: ${CountryOfOrigin.attributeText}\n" +
      s"Enter [2] for: whether the book is ${Honkaku.attributeText}\n" +
      s"Enter [3] for: ${AuthorGender.attributeText}\n" +
      s"Enter [4] for: whether the book is ${SchoolOrUniversityStudents.attributeText}\n" +
      s"Enter [5] for: ${DetectiveType.attributeText}\n" +
      s"Enter [6] for: whether the book is ${Series.attributeText}\n" +
      s"Enter [7] for: whether the book is ${PushkinVertigo.attributeText}\n" +
      s"Enter [8] for: ${SuspectPoolType.attributeText}")

    val input = StdIn.readLine()
    Try(input.toInt) match {
      case Success(num) if (num >= 1 && num <= 8) => {
        num match {
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
      case _ => {
        println("Invalid input. Please try again.\n")
        specifyGuessAttribute()
      }
    }
  }

  @tailrec
  def specifyBooleanValue(): Boolean = {
    println("Enter the guess value ('true' or 'false'):")
    val guessInput = StdIn.readLine()
    Try(guessInput.toLowerCase.toBoolean) match {
      case Success(b) => b
      case Failure(_) => {
        println(s"$guessInput is not a Boolean. Please try again.\n")
        specifyBooleanValue()
      }
    }
  }

  @tailrec
  def specifyEnumValue[E <: Enumeration with BookAttributeEnum](attributeEnum: E): attributeEnum.Value = {
    // println(attributeEnum.values.zipWithIndex)
    val selectOptionStr: String = attributeEnum.values.zipWithIndex.map {
      case (value, index) => s"Enter [${index + 1}] for $value"
    }.mkString("\n")

    println(s"Select a value to guess:\n$selectOptionStr")
    val input = StdIn.readLine()
    Try(input.toInt) match {
      case Success(x) if (x >= 1 && x <= attributeEnum.values.size) =>
        attributeEnum.values.toList(input.toInt - 1)
      case _ => {
        println("Invalid input. Please try again.\n")
        specifyEnumValue(attributeEnum)
      }
    }
  }
}
