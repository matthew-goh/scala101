package guesswho

import javax.imageio.ImageTypeSpecifier
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object GuessWhoUserInput {
  def main(args: Array[String]): Unit = {
    val game = GuessWho()
    val selectedChars = List(game.selectCharacter(game.allCharacters), game.selectCharacter(game.allCharacters))
//    println(selectedChars(0).name + " " + selectedChars(1).name)
//    val selectedCharP1 = game.selectCharacter(game.allCharacters) // P1 must guess this
//    val selectedCharP2 = game.selectCharacter(game.allCharacters) // P2 must guess this

    val charsOnBoard = Array(game.allCharacters, game.allCharacters)
//    var charsOnBoardP1 = game.allCharacters
//    var charsOnBoardP2 = game.allCharacters
    var gameOver = false
    var guesser = 2
    println("START OF GAME")

    while (!gameOver) {
      guesser = guesser % 2 + 1 // 1 -> 2, 2 -> 1
      println(s"\nPlayer $guesser's turn!")
      println(s"Characters remaining on P$guesser's board:")
      game.printRemainingNames(charsOnBoard(guesser-1))

      val guessType: Int = chooseAttributeOrCharacter()
      guessType match {
        case 1 => { // guessing attribute and value
          val guessAttribute: Attribute = specifyGuessAttribute()
          guessAttribute match {
            case Gender => {
              val guessValue: GenderEnum.Value = specifyGenderValue()
              charsOnBoard(guesser-1) = game.poseQuestion(selectedChars(guesser-1), guessAttribute, guessValue, charsOnBoard(guesser-1))
            }
            case Glasses | Moustache | Beard | RosyCheeks | Hat => {
              val guessValue: Boolean = specifyBooleanValue()
              charsOnBoard(guesser-1) = game.poseQuestion(selectedChars(guesser-1), guessAttribute, guessValue, charsOnBoard(guesser-1))
            }
            case HairColour | EyeColour => {
              val guessValue: Colour.Value = specifyColourValue()
              charsOnBoard(guesser-1) = game.poseQuestion(selectedChars(guesser-1), guessAttribute, guessValue, charsOnBoard(guesser-1))
            }
            case HatColour => {
              val guessValue: Some[Colour.Value] = Some(specifyColourValue())
              charsOnBoard(guesser-1) = game.poseQuestion(selectedChars(guesser-1), guessAttribute, guessValue, charsOnBoard(guesser-1))
            }
          }

          println(s"Characters remaining on P$guesser's board:")
          game.printRemainingNames(charsOnBoard(guesser-1))
        }
        case 2 => { // guessing character
          val guessedChar: String = specifyCharacter()
          val guessResult = game.guessCharacter(selectedChars(guesser-1), guessedChar, guesser, charsOnBoard(guesser-1))
          charsOnBoard(guesser-1) = guessResult._1
          gameOver = guessResult._2
          if (!gameOver){
            println(s"Characters remaining on P$guesser's board:")
            game.printRemainingNames(charsOnBoard(guesser-1))
          }
        }
      }
    }

    println("END OF GAME")
  }

  def chooseAttributeOrCharacter(): Int = {
    var input: String = ""
    var validInput = false

    while (!validInput) {
      println("Enter [1] to guess an attribute value\n" +
        "Enter [2] to guess the character")
      input = StdIn.readLine()
      Try(input.toInt) match {
        case Success(x) if (x == 1 || x == 2) => validInput = true
        case _ => println("Invalid input. Please try again.\n")
      }
    }
    input.toInt
  }

  def specifyCharacter(): String = {
    val game = GuessWho()
    var characterName: String = ""
    var validInput = false

    while (!validInput) {
      println("Who do you think is the selected character?")
      characterName = StdIn.readLine()
      val charNameFormatted = characterName.toLowerCase.capitalize
      if (game.allNames.contains(charNameFormatted)) validInput = true
      else println(s"$characterName is not the name of a character in the game. Please try again.\n")
    }
    characterName.toLowerCase.capitalize
  }

  def specifyGuessAttribute(): Attribute = {
    var input: String = ""
    var validInput = false

    while (!validInput) {
      println("Select the attribute you would like to guess:\n" +
        "Enter [1] for Gender\n" +
        "Enter [2] for Glasses\n" +
        "Enter [3] for Moustache\n" +
        "Enter [4] for Beard\n" +
        "Enter [5] for RosyCheeks\n" +
        "Enter [6] for HairColour\n" +
        "Enter [7] for EyeColour\n" +
        "Enter [8] for Hat\n" +
        "Enter [9] for HatColour")
      input = StdIn.readLine()
      Try(input.toInt) match {
        case Success(x) if (x >= 1 && x <= 9) => validInput = true
        case _ => println("Invalid input. Please try again.\n")
      }
    }

    input.toInt match {
      case 1 => Gender
      case 2 => Glasses
      case 3 => Moustache
      case 4 => Beard
      case 5 => RosyCheeks
      case 6 => HairColour
      case 7 => EyeColour
      case 8 => Hat
      case 9 => HatColour
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
        case Failure(e) => println(s"$guessInput is not a Boolean. Please try again.\n")
      }
    }
    guessInput.toLowerCase.toBoolean
  }

  def specifyGenderValue(): GenderEnum.Value = {
    var guessInput: String = ""
    var validInput = false

    while (!validInput) {
      println("Enter the guess value ('Male' or 'Female'):")
      guessInput = StdIn.readLine()
      Try(GenderEnum.withName(guessInput.toLowerCase.capitalize)) match {
        case Success(_) => validInput = true
        case Failure(e) => println(s"$guessInput is not a valid gender. Please try again.\n")
      }
    }
    GenderEnum.withName(guessInput.toLowerCase.capitalize)
  }

  def specifyColourValue(): Colour.Value = {
    var guessInput: String = ""
    var validInput = false

    while (!validInput) {
      println("Enter the guess value (a colour):")
      guessInput = StdIn.readLine()
      Try(Colour.withName(guessInput.toLowerCase.capitalize)) match {
        case Success(_) => validInput = true
        case Failure(e) => println(s"$guessInput is not a valid colour in the game. Please try again.\n")
      }
    }
    Colour.withName(guessInput.toLowerCase.capitalize)
  }
}

//trait ValueSelector[T] {
//  def getGuessValue(exampleVal: T): T
//}
//
//object ValueSelector {
//  implicit object BooleanSelector extends ValueSelector[Boolean] {
//    override def getGuessValue(exampleVal: Boolean): Boolean = {
//      var guessInput: String = ""
//      var validInput = false
//
//      while (!validInput) {
//        println("Enter the guess value ('true' or 'false'):")
//        guessInput = StdIn.readLine()
//        Try(guessInput.toLowerCase.toBoolean) match {
//          case Success(_) => validInput = true
//          case Failure(e) => println(s"$guessInput is not a Boolean. Please try again.\n")
//        }
//      }
//      guessInput.toLowerCase.toBoolean
//    }
//  }
//
//  implicit object GenderSelector extends ValueSelector[GenderEnum.Value] {
//    override def getGuessValue(exampleVal: GenderEnum.Value): GenderEnum.Value = {
//      var guessInput: String = ""
//      var validInput = false
//
//      while (!validInput) {
//        println("Enter the guess value ('Male' or 'Female'):")
//        guessInput = StdIn.readLine()
//        Try(GenderEnum.withName(guessInput.toLowerCase.capitalize)) match {
//          case Success(_) => validInput = true
//          case Failure(e) => println(s"$guessInput is not a valid gender. Please try again.\n")
//        }
//      }
//      GenderEnum.withName(guessInput.toLowerCase.capitalize)
//    }
//  }
//
//  implicit object ColourSelector extends ValueSelector[Colour.Value] {
//    override def getGuessValue(exampleVal: Colour.Value): Colour.Value = {
//      var guessInput: String = ""
//      var validInput = false
//
//      while (!validInput) {
//        println("Enter the guess value (a colour):")
//        guessInput = StdIn.readLine()
//        Try(Colour.withName(guessInput.toLowerCase.capitalize)) match {
//          case Success(_) => validInput = true
//          case Failure(e) => println(s"$guessInput is not a valid colour in the game. Please try again.\n")
//        }
//      }
//      Colour.withName(guessInput.toLowerCase.capitalize)
//    }
//  }
//}