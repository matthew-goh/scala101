package guesswho

import scala.util.Random

case class GuessWho() {
  val genderValues: List[GenderEnum.Value] = GenderEnum.values.toList
  val colourValues: List[Colour.Value] = Colour.values.toList
  val colourOptionValues: List[Option[Colour.Value]] = colourValues.map(colour => Some(colour))

  // instantiate all characters with their attributes
  val Alex: GameCharacter = GameCharacter(name = "Alex", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> true, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Black, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))

  val Alfred: GameCharacter = GameCharacter(name = "Alfred", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> true, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Orange, EyeColour -> Colour.Blue, Hat -> false, HatColour -> None))

  val Anita: GameCharacter = GameCharacter(name = "Anita", attributes = Map(Gender -> GenderEnum.Female, Glasses -> false, Moustache -> false, Beard -> false,
    RosyCheeks -> true, HairColour -> Colour.White, EyeColour -> Colour.Blue, Hat -> false, HatColour -> None))

  val Anne: GameCharacter = GameCharacter(name = "Anne", attributes = Map(Gender -> GenderEnum.Female, Glasses -> false, Moustache -> false, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Black, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))

  val Bernard: GameCharacter = GameCharacter(name = "Bernard", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> false, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Brown, EyeColour -> Colour.Brown, Hat -> true, HatColour -> Some(Colour.Orange)))

  val Bill: GameCharacter = GameCharacter(name = "Bill", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> false, Beard -> true,
    RosyCheeks -> false, HairColour -> Colour.Orange, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))

  val Charles: GameCharacter = GameCharacter(name = "Charles", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> true, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Blonde, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))

  val Claire: GameCharacter = GameCharacter(name = "Claire", attributes = Map(Gender -> GenderEnum.Female, Glasses -> true, Moustache -> false, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Orange, EyeColour -> Colour.Brown, Hat -> true, HatColour -> Some(Colour.Pink)))

  val David: GameCharacter = GameCharacter(name = "David", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> false, Beard -> true,
    RosyCheeks -> false, HairColour -> Colour.Blonde, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))

  val Eric: GameCharacter = GameCharacter(name = "Eric", attributes = Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> false, Beard -> false,
    RosyCheeks -> false, HairColour -> Colour.Blonde, EyeColour -> Colour.Brown, Hat -> true, HatColour -> Some(Colour.Grey)))

  val allCharacters: Seq[GameCharacter] = Seq(Alex, Alfred, Anita, Anne, Bernard, Bill, Charles, Claire, David, Eric)
  val allNames: Seq[String] = allCharacters.map(character => character.name)
  // def resetBoard: Seq[GameCharacter] = allCharacters // returns list of all characters

  // Print names of characters remaining on board
  def printRemainingNames(characterSeq: Seq[GameCharacter]) : Unit = {
    val nameSeq = characterSeq.map(character => character.name)
    // concatenate all name strings into a single string and print it
    println(nameSeq.mkString(", "))
  }

  // Randomly select a character from the input list
  def selectCharacter(characterSeq: Seq[GameCharacter]) : GameCharacter =
    characterSeq(Random.between(0, characterSeq.length))

  // Ask a question and filter characters:
  // -question must specify the attribute and value,
  // e.g. Does the person have brown hair? -> HairColour, Colour.Brown
  // e.g. Does the person have a moustache? -> Moustache, true

  // input selected character, attribute being guessed, guessed value of attribute and Seq of remaining characters
  // return Seq of characters after filtering based on the question
// Scaladoc comments - good for code that doesn't change much
  /**
   *
   * @param selectedChar
   * @param guessAttribute
   * @param guessValue
   * @param remainingChars
   * @return
   */
  def poseQuestion[T](selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: T,
                   remainingChars: Seq[GameCharacter])(implicit guessChecker: GuessChecker[T]) : Seq[GameCharacter] = {
    // check that guessValue is of the correct type for the attribute
    guessChecker.validateGuess(guessAttribute, guessValue)

    // if selected character has the guessed attribute's value, keep characters with the guessed value
    def filterCharacters(selectedChar: GameCharacter, guessAttribute: Attribute, guessValue: T,
                         remainingChars: Seq[GameCharacter]): Seq[GameCharacter] = {
      if (selectedChar.attributes(guessAttribute) == guessValue){
        remainingChars.filter {
          ch => ch.attributes(guessAttribute) == guessValue
        }
      } else { // keep characters without the guessed value
        remainingChars.filter {
          ch => ch.attributes(guessAttribute) != guessValue
        }
      }
    }

    guessChecker.printFeedback(selectedChar, guessAttribute, guessValue)
    filterCharacters(selectedChar, guessAttribute, guessValue, remainingChars)
  }
  // Feedback:
  // - could have separate methods for validation and filtering
  // - can use specific types of exceptions if appropriate
  // - if prompting for user input, loops are ok for validation?
  // - but if running with a frontend, not so important since can restrict options for user to click

  // Guess a character to end the game
  // input selected character, guessed character and Seq of remaining characters
  // print the outcome of the guess and return a tuple containing
  // (i) Seq of characters after filtering (the guessed character only if correct; remove the guessed character if not)
  // (ii) Boolean for whether the guess was correct
  def guessCharacter(selectedChar: GameCharacter, guessedChar: String, guesser: Int,
                     remainingChars: Seq[GameCharacter]) : (Seq[GameCharacter], Boolean) = {
    val guessedCharFormatted = guessedChar.toLowerCase.capitalize

    if(!allNames.contains(guessedCharFormatted)){
      throw new Exception("Invalid name")
    }

    guessedCharFormatted match {
      case selectedChar.name => {
        println(s"Player $guesser wins! The character was $guessedCharFormatted.")
        (remainingChars.filter { ch => ch.name == guessedCharFormatted }, true)
      }
      case _ => {
        println(s"Player $guesser's guess is incorrect. The character is not $guessedCharFormatted.")
        (remainingChars.filter { ch => ch.name != guessedCharFormatted }, false)
      }
    }
  }
}
