import scala.util.Random

// character class with all attributes that can be asked about
case class GameCharacter(val name: String, val attributes: Map[String, Any])
val allAttributes = Seq("gender", "glasses", "moustache", "beard", "rosyCheeks", "hairColour", "eyeColour", "hat", "hatColour")

trait Gender
object Male extends Gender
object Female extends Gender
//Male.toString

trait Colour
object Black extends Colour
object Brown extends Colour
object Blue extends Colour
object Orange extends Colour
object White extends Colour
object Blonde extends Colour
object Pink extends Colour
object Grey extends Colour
object None extends Colour

// instantiate the specific characters
val Alex = GameCharacter(name = "Alex", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Black, "eyeColour" -> Brown, "hat" -> false, "hatColour" -> None))

val Alfred = GameCharacter(name = "Alfred", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Orange, "eyeColour" -> Blue, "hat" -> false, "hatColour" -> None))

val Anita = GameCharacter(name = "Anita", attributes = Map("gender" -> Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> true, "hairColour" -> White, "eyeColour" -> Blue, "hat" -> false, "hatColour" -> None))

val Anne = GameCharacter(name = "Anne", attributes = Map("gender" -> Female, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Black, "eyeColour" -> Brown, "hat" -> false, "hatColour" -> None))

val Bernard = GameCharacter(name = "Bernard", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Brown, "eyeColour" -> Brown, "hat" -> true, "hatColour" -> Orange))

val Bill = GameCharacter(name = "Bill", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> Orange, "eyeColour" -> Brown, "hat" -> false, "hatColour" -> None))

val Charles = GameCharacter(name = "Charles", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Blonde, "eyeColour" -> Brown, "hat" -> false, "hatColour" -> None))

val Claire = GameCharacter(name = "Claire", attributes = Map("gender" -> Female, "glasses" -> true, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Orange, "eyeColour" -> Brown, "hat" -> true, "hatColour" -> Pink))

val David = GameCharacter(name = "David", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> Blonde, "eyeColour" -> Brown, "hat" -> false, "hatColour" -> None))

val Eric = GameCharacter(name = "Eric", attributes = Map("gender" -> Male, "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> Blonde, "eyeColour" -> Brown, "hat" -> true, "hatColour" -> Grey))

val allCharacters: Seq[GameCharacter] = Seq(Alex, Alfred, Anita, Anne, Bernard, Bill, Charles, Claire, David, Eric)
def resetBoard() : Seq[GameCharacter] = allCharacters // returns list of all characters
val allNames = allCharacters.map {
  character => character.name
}

// Remaining characters on game board
//var charactersOnBoard = resetBoard()
//var charactersOnBoard: Seq[GameCharacter] = Seq()

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
// e.g. Does the person have brown hair? -> hairColour, Brown
// e.g. Does the person have a moustache? -> moustache, true

// input selected character, attribute, guess value and seq of remaining characters
// return seq of characters after filtering based on the question
def poseQuestion(selectedChar: GameCharacter, guessAttribute: String, guessValue: Any, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  guessAttribute match {
    case "gender" => // if statement should be inside, otherwise will go to case _ exception
      if (!guessValue.isInstanceOf[Gender]){
        throw new Exception("Guess value must be an object extending the Gender trait")
      }
    case "glasses" | "moustache" | "beard" | "rosyCheeks" | "hat" =>
      if (!guessValue.isInstanceOf[Boolean]) {
        throw new Exception("Guess value must be a Boolean")
      }
    case "hairColour" | "eyeColour" | "hatColour" =>
      if (!guessValue.isInstanceOf[Colour]) {
        throw new Exception("Guess value must be an object extending the Colour trait")
      }
    case _ =>
      throw new Exception("Invalid guess attribute")
  }

  val guessValueStr = guessValue.toString

//  selectedChar.attributes(guessAttribute).toString match {
//    case guessValueLower =>
//      remainingChars.filter {
//        ch => ch.attributes(guessAttribute).toString == guessValueLower
//      }
//    case _ =>
//      remainingChars.filter {
//        ch => ch.attributes(guessAttribute).toString != guessValueLower
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
printRemainingNames(poseQuestion(Alex, "hairColour", Orange, allCharacters))
printRemainingNames(poseQuestion(Anne, "gender", Male, allCharacters))

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
      println(s"Player $guesser's guess is incorrect. The character is not $guessedCharFormatted.'")
      remainingChars.filter {
        ch => ch.name != guessedCharFormatted
      }
    }
  }
}
guessCharacter(Bill, "biLL", 1, allCharacters)
guessCharacter(Bill, "Alfred", 2, allCharacters)
//guessCharacter(Bill, "Alice", 1)

///////////////////////
// Playing the game: start with all characters on board, randomly select target character
var charsOnBoardP1 = resetBoard()
var charsOnBoardP2 = resetBoard()

val selectedCharP1 = selectCharacter(allCharacters) // P1 must guess this
val selectedCharP2 = selectCharacter(allCharacters) // P2 must guess this
println("Selected characters: P1 " + selectedCharP1.name + ", P2 " + selectedCharP2.name)

charsOnBoardP1 = poseQuestion(selectedCharP1, "gender", Male, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "gender", Female, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

charsOnBoardP1 = poseQuestion(selectedCharP1, "hat", true, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "moustache", true, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

charsOnBoardP1 = poseQuestion(selectedCharP1, "hairColour", Black, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
charsOnBoardP2 = poseQuestion(selectedCharP2, "rosyCheeks", true, charsOnBoardP2)
printRemainingNames(charsOnBoardP2)

// P1 guesses randomly from the remaining names
val nameToGuess = selectCharacter(charsOnBoardP1).name
charsOnBoardP1 = guessCharacter(selectedCharP1, nameToGuess, 1, charsOnBoardP1)
printRemainingNames(charsOnBoardP1)
