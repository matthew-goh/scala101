import scala.util.Random

// character class with all attributes that can be asked about
case class GameCharacter(val name: String, val attributes: Map[String, Any])
val allAttributes = Seq("gender", "glasses", "moustache", "beard", "rosyCheeks", "hairColour", "eyeColour", "hat", "hatColour")

object Gender extends Enumeration{
  val Male, Female = Value
}
val genderValues = Gender.values.toList
object Colour extends Enumeration{
  val Black, Brown, Blue, Orange, White, Blonde, Pink, Grey = Value
}
val colourValues = Colour.values.toList
val colourOptionValues = colourValues.map(colour => Some(colour))

//sealed trait Gender
//case object Male extends Gender
//case object Female extends Gender
//
//sealed trait Colour
//case object Black extends Colour
//case object Brown extends Colour
//case object Blue extends Colour
//case object Orange extends Colour
//case object White extends Colour
//case object Blonde extends Colour
//case object Pink extends Colour
//case object Grey extends Colour

// instantiate the specific characters
val Alex = GameCharacter(name = "Alex", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Black, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

val Alfred = GameCharacter(name = "Alfred", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Blue, "hat" -> false, "hatColour" -> None))

val Anita = GameCharacter(name = "Anita", attributes = Map("gender" -> Gender.Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> true, "hairColour" -> Colour.White, "eyeColour" -> Colour.Blue, "hat" -> false, "hatColour" -> None))

val Anne = GameCharacter(name = "Anne", attributes = Map("gender" -> Gender.Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Black, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

val Bernard = GameCharacter(name = "Bernard", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Brown, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Orange)))

val Bill = GameCharacter(name = "Bill", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

val Charles = GameCharacter(name = "Charles", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

val Claire = GameCharacter(name = "Claire", attributes = Map("gender" -> Gender.Female, "glasses" -> true, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Orange, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Pink)))

val David = GameCharacter(name = "David", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))

val Eric = GameCharacter(name = "Eric", attributes = Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Colour.Blonde, "eyeColour" -> Colour.Brown, "hat" -> true, "hatColour" -> Some(Colour.Grey)))

val allCharacters: Seq[GameCharacter] = Seq(Alex, Alfred, Anita, Anne, Bernard, Bill, Charles, Claire, David, Eric)
def resetBoard() : Seq[GameCharacter] = allCharacters // returns list of all characters
val allNames = allCharacters.map {
  character => character.name
}


// Print names of characters remaining on board
def printRemainingNames(characterSeq: Seq[GameCharacter]) : Unit = {
  val nameSeq = characterSeq.map {
    character => character.name
  }
  // concatenate all name strings into a single string and print it
  println(nameSeq.mkString(", "))
}
//printRemainingNames(allCharacters)


// Randomly select character to be guessed
def selectCharacter(characterSeq: Seq[GameCharacter]) : GameCharacter =
  characterSeq(Random.between(0, characterSeq.length))
//val selectedChar = selectCharacter(allCharacters)
//println(selectedChar.name)


// Remove characters after a question
// -should specify the attribute and value,
// e.g. Does the person have brown hair? -> hairColour, Colour.Brown
// e.g. Does the person have a moustache? -> moustache, true

// input selected character, attribute, guess value and Seq of remaining characters
// return Seq of characters after filtering based on the question
def poseQuestion(selectedChar: GameCharacter, guessAttribute: String, guessValue: Any,
                 remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  val guessValueStr = guessValue.toString

  guessAttribute match {
    case "gender" => // if statement should be inside, otherwise will go to case _ exception
      if (!genderValues.contains(guessValue)){ // isInstanceOf[Colour.Value] accepts values from other enums
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

//  selectedChar.attributes(guessAttribute).toString match {
//    case guessValueStr =>
//      remainingChars.filter {
//        ch => ch.attributes(guessAttribute).toString == guessValueStr
//      }
//    case _ =>
//      remainingChars.filter {
//        ch => ch.attributes(guessAttribute).toString != guessValueStr
//      }
//  }

  if (selectedChar.attributes(guessAttribute).toString == guessValueStr){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.attributes(guessAttribute).toString == guessValueStr
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.attributes(guessAttribute).toString != guessValueStr
    }
  }
}
//printRemainingNames(poseQuestion(Bernard, "hairColour", Gender.Male, allCharacters))
//printRemainingNames(poseQuestion(Eric, "hatColour", "x", allCharacters))

printRemainingNames(poseQuestion(Alex, "hairColour", Colour.Orange, allCharacters))
printRemainingNames(poseQuestion(Anne, "gender", Gender.Male, allCharacters))
printRemainingNames(poseQuestion(Eric, "hatColour", Some(Colour.Grey), allCharacters))

// Guess a character to end the game
var gameOver = false
def guessCharacter(selectedChar: GameCharacter, guessedChar: String, guesser: Int, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  val guessedCharFormatted = guessedChar.toLowerCase.capitalize

  if(!allNames.contains(guessedCharFormatted)){
    throw new Exception("Invalid name")
  }

  guessedCharFormatted match {
    case selectedChar.name => {
      gameOver = true
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
guessCharacter(Bill, "biLL", 1, allCharacters)
guessCharacter(Bill, "Alfred", 2, allCharacters)
//guessCharacter(Bill, "Alice", 1, allCharacters)

///////////////////////
// Playing the game: start with all characters on board, randomly select target character
var charsOnBoardP1 = resetBoard()
var charsOnBoardP2 = resetBoard()

val selectedCharP1 = selectCharacter(allCharacters) // P1 must guess this
val selectedCharP2 = selectCharacter(allCharacters) // P2 must guess this
println("Selected characters: P1 " + selectedCharP1.name + ", P2 " + selectedCharP2.name)

charsOnBoardP1 = poseQuestion(selectedCharP1, "gender", Gender.Male, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "gender", Gender.Female, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

charsOnBoardP1 = poseQuestion(selectedCharP1, "hat", true, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "moustache", true, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

charsOnBoardP1 = poseQuestion(selectedCharP1, "hairColour", Colour.Black, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "rosyCheeks", true, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

// P1 guesses randomly from the remaining names
val nameToGuess = selectCharacter(charsOnBoardP1).name
charsOnBoardP1 = guessCharacter(selectedCharP1, nameToGuess, 1, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)

val stream = new java.io.ByteArrayOutputStream()
Console.withOut(stream) {
  //all printlns in this block will be redirected
  guessCharacter(Bill, "Alfred", 2, allCharacters)
}
stream.toString.strip
