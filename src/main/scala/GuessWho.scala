import scala.util.Random

case class GuessWho() {
  val genderValues: List[Gender.Value] = Gender.values.toList
  val colourValues: List[Colour.Value] = Colour.values.toList
  val colourOptionValues: List[Option[Colour.Value]] = colourValues.map(colour => Some(colour))

  // instantiate all characters with their attributes
  val Alex: GameCharacter = GameCharacter(name = "Alex", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Black, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

  val Alfred: GameCharacter = GameCharacter(name = "Alfred", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Blue, "hat" -> false, "hatColour" -> None))

  val Anita: GameCharacter = GameCharacter(name = "Anita", attributes = Map("gender" -> Gender.Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
    "rosyCheeks" -> true, "hairColour" -> Colour.White, "eyeColour" -> Colour.Blue, "hat" -> false, "hatColour" -> None))

  val Anne: GameCharacter = GameCharacter(name = "Anne", attributes = Map("gender" -> Gender.Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Black, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

  val Bernard: GameCharacter = GameCharacter(name = "Bernard", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Brown, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Orange)))

  val Bill: GameCharacter = GameCharacter(name = "Bill", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
    "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

  val Charles: GameCharacter = GameCharacter(name = "Charles", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

  val Claire: GameCharacter = GameCharacter(name = "Claire", attributes = Map("gender" -> Gender.Female, "glasses" -> true, "moustache" -> false, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Pink)))

  val David: GameCharacter = GameCharacter(name = "David", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
    "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

  val Eric: GameCharacter = GameCharacter(name = "Eric", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
    "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Grey)))

  val allCharacters: Seq[GameCharacter] = Seq(Alex, Alfred, Anita, Anne, Bernard, Bill, Charles, Claire, David, Eric)
  val allNames: Seq[String] = allCharacters.map(character => character.name)
  def resetBoard() : Seq[GameCharacter] = allCharacters // returns list of all characters

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
  // e.g. Does the person have brown hair? -> hairColour, Colour.Brown
  // e.g. Does the person have a moustache? -> moustache, true

  // input selected character, attribute, guess value and Seq of remaining characters
  // return Seq of characters after filtering based on the question
  def poseQuestion(selectedChar: GameCharacter, guessAttribute: String, guessValue: Any,
                   remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
    // check that guessValue is of the correct type for the attribute
    guessAttribute match {
      case "gender" => // if statement should be inside, otherwise will go to case _ exception
        if (!genderValues.contains(guessValue)){
          throw new Exception("Guess value must be a valid Gender")
        }
      case "glasses" | "moustache" | "beard" | "rosyCheeks" | "hat" =>
        if (!guessValue.isInstanceOf[Boolean]) {
          throw new Exception("Guess value must be a Boolean")
        }
      case "hairColour" | "eyeColour" =>
        if (!colourValues.contains(guessValue)) {
          throw new Exception("Guess value must be a valid Colour")
        }
      case "hatColour" =>
        if (!colourOptionValues.contains(guessValue)) {
          throw new Exception("Guess value type must be a valid Option[Colour]")
        }
      case _ =>
        throw new Exception("Invalid guess attribute")
    }

    val guessValueStr = guessValue.toString
    // if selected character has the guessed attribute's value, keep characters with the guessed value
    if (selectedChar.attributes(guessAttribute).toString == guessValueStr){
      remainingChars.filter {
        ch => ch.attributes(guessAttribute).toString == guessValueStr
      }
    } else { // keep characters without the guessed value
      remainingChars.filter {
        ch => ch.attributes(guessAttribute).toString != guessValueStr
      }
    }
  }


  // Guess a character to end the game
  // input selected character, guessed character and Seq of remaining characters
  // return Seq of characters after filtering (the guessed character only if correct; remove the guessed character if not)
  // and print the outcome of the guess
//  var gameOver = false
  def guessCharacter(selectedChar: GameCharacter, guessedChar: String, guesser: Int,
                     remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
    val guessedCharFormatted = guessedChar.toLowerCase.capitalize

    if(!allNames.contains(guessedCharFormatted)){
      throw new Exception("Invalid name")
    }

    guessedCharFormatted match {
      case selectedChar.name => {
//        gameOver = true
        println(s"Player $guesser wins! The character was $guessedCharFormatted.")
        remainingChars.filter {
          ch => ch.name == guessedCharFormatted
        }
      }
      case _ => {
        println(s"Player $guesser's guess is incorrect. The character is not $guessedCharFormatted.")
        remainingChars.filter {
          ch => ch.name != guessedCharFormatted
        }
      }
    }
  }
}
