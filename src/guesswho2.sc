import scala.util.Random

// character class with all attributes that can be asked about
class GameCharacter(val name: String, val attributes: Map[String, Any])
val allAttributes = Seq("gender", "glasses", "moustache", "beard", "rosyCheeks", "hairColour", "eyeColour", "hat", "hatColour")

// instantiate the specific characters
val Alex = new GameCharacter(name = "Alex", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "black", "eyeColour" -> "brown", "hat" -> false, "hatColour" -> "none"))

val Alfred = new GameCharacter(name = "Alfred", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "orange", "eyeColour" -> "blue", "hat" -> false, "hatColour" -> "none"))

val Anita = new GameCharacter(name = "Anita", attributes = Map("gender" -> "Female", "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> true, "hairColour" -> "white", "eyeColour" -> "blue", "hat" -> false, "hatColour" -> "none"))

val Anne = new GameCharacter(name = "Anne", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "black", "eyeColour" -> "brown", "hat" -> false, "hatColour" -> "none"))

val Bernard = new GameCharacter(name = "Bernard", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "brown", "eyeColour" -> "brown", "hat" -> true, "hatColour" -> "orange"))

val Bill = new GameCharacter(name = "Bill", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> "orange", "eyeColour" -> "brown", "hat" -> false, "hatColour" -> "none"))

val Charles = new GameCharacter(name = "Charles", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> true, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "blonde", "eyeColour" -> "brown", "hat" -> false, "hatColour" -> "none"))

val Claire = new GameCharacter(name = "Claire", attributes = Map("gender" -> "Female", "glasses" -> true, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "orange", "eyeColour" -> "brown", "hat" -> true, "hatColour" -> "pink"))

val David = new GameCharacter(name = "David", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> false, "beard" -> true,
  "rosyCheeks" -> false, "hairColour" -> "blonde", "eyeColour" -> "brown", "hat" -> false, "hatColour" -> "none"))

val Eric = new GameCharacter(name = "Eric", attributes = Map("gender" -> "Male", "glasses" -> false, "moustache" -> false, "beard" -> false,
  "rosyCheeks" -> false, "hairColour" -> "blonde", "eyeColour" -> "brown", "hat" -> true, "hatColour" -> "grey"))

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
  characterSeq(Random.between(0, allCharacters.length))
//val selectedChar = selectCharacter(allCharacters)
//println(selectedChar.name)


// Remove characters after a question
// -should specify the attribute and value,
// e.g. Does the person have brown hair? -> hairColour, "brown"
// e.g. Does the person have a moustache? -> moustache, true

// input selected character, attribute, guess value and seq of remaining characters
// return seq of characters after filtering based on the question
def poseQuestion(selectedChar: GameCharacter, guessAttribute: String, guessValue: Any, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (!allAttributes.contains(guessAttribute)){
    throw new Exception("Invalid guess attribute")
  }

  if (guessAttribute == "gender"){
    if (guessValue != "Male" && guessValue != "Female"){
      throw new Exception("Guess value must be Male or Female")
    }
  }
  else if (Seq("glasses", "moustache", "beard", "rosyCheeks", "hat").contains(guessAttribute)){
    if (!guessValue.isInstanceOf[Boolean]){
      throw new Exception("Guess value must be a Boolean")
    }
  }

  if (selectedChar.attributes(guessAttribute) == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.attributes(guessAttribute) == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.attributes(guessAttribute) != guessValue
    }
  }
}
printRemainingNames(poseQuestion(Anne, "hairColour", "orange", allCharacters))


// Guess a character to end the game
var gameOver = false
def guessCharacter(selectedChar: GameCharacter, guessedChar: String) : Unit = {
  if(!allNames.contains(guessedChar)){
    throw new Exception("Invalid name")
  }

  gameOver = true
  if (selectedChar.name == guessedChar){
    println("You win")
  }
  else {
    print("You lose")
  }
}
//guessCharacter(Bill, "Bill")
//guessCharacter(Bill, "Alfred")
//guessCharacter(Bill, "Alice")

///////////////////////
// Playing the game: start with all characters on board, randomly select target character
var charactersOnBoard = resetBoard()

val selectedChar = selectCharacter(allCharacters)
println("Selected character: " + selectedChar.name)
charactersOnBoard = poseQuestion(selectedChar, "gender", "Male", charactersOnBoard)
printRemainingNames(charactersOnBoard)
charactersOnBoard = poseQuestion(selectedChar, "hat", true, charactersOnBoard)
printRemainingNames(charactersOnBoard)
charactersOnBoard = poseQuestion(selectedChar, "hairColour", "black", charactersOnBoard)
printRemainingNames(charactersOnBoard)
