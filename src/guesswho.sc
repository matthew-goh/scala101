import scala.util.Random

// character class with all attributes that can be asked about
class GameCharacter(val name: String, val gender: String, val glasses: Boolean, val moustache: Boolean,
                    val beard: Boolean, val rosyCheeks: Boolean, val hairColour: String, val eyeColour: String,
                    val hat: Boolean, val hatColour: String)

// instantiate the specific characters
val Alex = new GameCharacter(name = "Alex", gender = "Male", glasses = false, moustache = true, beard = false,
  rosyCheeks = false, hairColour = "black", eyeColour = "brown", hat = false, hatColour = "none")

val Alfred = new GameCharacter(name = "Alfred", gender = "Male", glasses = false, moustache = true, beard = false,
  rosyCheeks = false, hairColour = "orange", eyeColour = "blue", hat = false, hatColour = "none")

val Anita = new GameCharacter(name = "Anita", gender = "Female", glasses = false, moustache = false, beard = false,
  rosyCheeks = true, hairColour = "white", eyeColour = "blue", hat = false, hatColour = "none")

val Anne = new GameCharacter(name = "Anne", gender = "Female", glasses = false, moustache = false, beard = false,
  rosyCheeks = false, hairColour = "black", eyeColour = "brown", hat = false, hatColour = "none")

val Bernard = new GameCharacter(name = "Bernard", gender = "Male", glasses = false, moustache = false, beard = false,
  rosyCheeks = false, hairColour = "brown", eyeColour = "brown", hat = true, hatColour = "orange")

val Bill = new GameCharacter(name = "Bill", gender = "Male", glasses = false, moustache = false, beard = true,
  rosyCheeks = false, hairColour = "orange", eyeColour = "brown", hat = false, hatColour = "none")

val Charles = new GameCharacter(name = "Charles", gender = "Male", glasses = false, moustache = true, beard = false,
  rosyCheeks = false, hairColour = "blonde", eyeColour = "brown", hat = false, hatColour = "none")

val Claire = new GameCharacter(name = "Claire", gender = "Female", glasses = true, moustache = false, beard = false,
  rosyCheeks = false, hairColour = "orange", eyeColour = "brown", hat = true, hatColour = "pink")

val David = new GameCharacter(name = "David", gender = "Male", glasses = false, moustache = false, beard = true,
  rosyCheeks = false, hairColour = "blonde", eyeColour = "brown", hat = false, hatColour = "none")

val Eric = new GameCharacter(name = "Eric", gender = "Male", glasses = false, moustache = false, beard = false,
  rosyCheeks = false, hairColour = "blonde", eyeColour = "brown", hat = true, hatColour = "grey")

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
// e.g. Does the person have brown hair? -> hairColour, "brown"
// e.g. Does the person have a moustache? -> moustache, true

def guessGender(selectedChar: GameCharacter, guessValue: String, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.gender == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.gender == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.gender != guessValue
    }
  }
}
def guessHairColour(selectedChar: GameCharacter, guessValue: String, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.hairColour == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.hairColour == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.hairColour != guessValue
    }
  }
}
def guessEyeColour(selectedChar: GameCharacter, guessValue: String, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.eyeColour == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.eyeColour == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.eyeColour != guessValue
    }
  }
}
def guessHatColour(selectedChar: GameCharacter, guessValue: String, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.hatColour == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.hatColour == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.hatColour != guessValue
    }
  }
}

def guessGlasses(selectedChar: GameCharacter, guessValue: Boolean, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.glasses == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.glasses == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.glasses != guessValue
    }
  }
}
def guessMoustache(selectedChar: GameCharacter, guessValue: Boolean, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.moustache == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.moustache == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.moustache != guessValue
    }
  }
}
def guessBeard(selectedChar: GameCharacter, guessValue: Boolean, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.beard == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.beard == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.beard != guessValue
    }
  }
}
def guessRosyCheeks(selectedChar: GameCharacter, guessValue: Boolean, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.rosyCheeks == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.rosyCheeks == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.rosyCheeks != guessValue
    }
  }
}
def guessHat(selectedChar: GameCharacter, guessValue: Boolean, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (selectedChar.hat == guessValue){ // keep characters with the guessed value
    remainingChars.filter {
      ch => ch.hat == guessValue
    }
  } else { // keep characters without the guessed value
    remainingChars.filter {
      ch => ch.hat != guessValue
    }
  }
}

// input selected character, attribute, guess value and seq of remaining characters
// return seq of characters after filtering based on the question
def convertGuessValueToBool(guessValue: String) : Boolean = guessValue == "true"
def poseQuestion(selectedChar: GameCharacter, guessAttribute: String, guessValue: String, remainingChars: Seq[GameCharacter]) : Seq[GameCharacter] = {
  if (guessAttribute == "gender"){
    if (guessValue != "Male" && guessValue != "Female"){
      throw new Exception("Guess value must be Male or Female")
    }
    guessGender(selectedChar, guessValue, remainingChars)
  }
  else if (guessAttribute == "glasses"){
    if (guessValue != "true" && guessValue != "false"){
      throw new Exception("Guess value must be true or false")
    }
    guessGlasses(selectedChar, convertGuessValueToBool(guessValue), remainingChars)
  }
  else if (guessAttribute == "moustache") {
    if (guessValue != "true" && guessValue != "false"){
      throw new Exception("Guess value must be true or false")
    }
    guessMoustache(selectedChar, convertGuessValueToBool(guessValue), remainingChars)
  }
  else if (guessAttribute == "beard") {
    if (guessValue != "true" && guessValue != "false"){
      throw new Exception("Guess value must be true or false")
    }
    guessBeard(selectedChar, convertGuessValueToBool(guessValue), remainingChars)
  }
  else if (guessAttribute == "rosyCheeks"){
    if (guessValue != "true" && guessValue != "false"){
      throw new Exception("Guess value must be true or false")
    }
    guessRosyCheeks(selectedChar, convertGuessValueToBool(guessValue), remainingChars)
  }
  else if (guessAttribute == "hairColour"){
    guessHairColour(selectedChar, guessValue, remainingChars)
  }
  else if (guessAttribute == "eyeColour"){
    guessEyeColour(selectedChar, guessValue, remainingChars)
  }
  else if (guessAttribute == "hat"){
    if (guessValue != "true" && guessValue != "false"){
      throw new Exception("Guess value must be true or false")
    }
    guessHat(selectedChar, convertGuessValueToBool(guessValue), remainingChars)
  }
  else if (guessAttribute == "hatColour"){
    guessHatColour(selectedChar, guessValue, remainingChars)
  }
  else {
    throw new Exception("Invalid guess attribute")
  }
}
//printRemainingNames(poseQuestion(Anne, "hairColour", "orange", allCharacters))


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
charactersOnBoard = poseQuestion(selectedChar, "hat", "true", charactersOnBoard)
printRemainingNames(charactersOnBoard)
charactersOnBoard = poseQuestion(selectedChar, "hairColour", "black", charactersOnBoard)
printRemainingNames(charactersOnBoard)
