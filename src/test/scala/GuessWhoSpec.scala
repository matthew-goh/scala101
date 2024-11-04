import org.scalatest.flatspec.AnyFlatSpec

import java.lang

class GuessWhoSpec extends AnyFlatSpec {
  "guessWho" should "instantiate character attributes correctly" in {
    val game = GuessWho()
    assert(game.Alex.attributes === Map("gender" -> Gender.Male, "glasses" -> false, "moustache" -> true, "beard" -> false,
      "rosyCheeks" -> false, "hairColour" -> Colour.Black, "eyeColour" -> Colour.Brown, "hat" -> false, "hatColour" -> None))
    assert(game.Alfred.attributes("moustache") === true)
    assert(game.Anita.name === "Anita")
    assert(game.Anne.attributes("rosyCheeks") === false)
    assert(game.Bernard.attributes("gender") === Gender.Male)
    assert(game.Bill.attributes("beard") === true)
    assert(game.Charles.attributes("hairColour") === Colour.Blonde)
    assert(game.Claire.attributes("hatColour") === Some(Colour.Pink))
    assert(game.David.attributes("hatColour") === None)
    assert(game.Eric.attributes("eyeColour") === Colour.Brown)
  }

  "guessWho" should "reset the game board with all characters" in {
    val game = GuessWho()
    assert(game.resetBoard() ===
      Seq(game.Alex, game.Alfred, game.Anita, game.Anne, game.Bernard, game.Bill, game.Charles, game.Claire, game.David, game.Eric))
  }

  "guessWho" should "create a list of all character names" in {
    val game = GuessWho()
    assert(game.allNames ===
      Seq("Alex", "Alfred", "Anita", "Anne", "Bernard", "Bill", "Charles", "Claire", "David", "Eric"))
  }

  "printRemainingNames" should "print the list of character names correctly" in {
    val game = GuessWho()
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      // all printlns in this block will be redirected
      game.printRemainingNames(game.allCharacters)
    }
    assert(stream.toString.strip === "Alex, Alfred, Anita, Anne, Bernard, Bill, Charles, Claire, David, Eric")

    stream.reset() // clear ByteArrayOutputStream, otherwise the next output will just be appended
    Console.withOut(stream) {
      game.printRemainingNames(Seq(game.Anita, game.Bill, game.Claire))
    }
    assert(stream.toString.strip === "Anita, Bill, Claire")
  }

  "poseQuestion" should "filter the list of characters based on the guessed attribute and value" in {
    val game = GuessWho()
    assert(game.poseQuestion(game.Anne, "gender", Gender.Female, game.allCharacters) ===
      Seq(game.Anita, game.Anne, game.Claire)) // female characters

    val filteredChars1 = game.poseQuestion(game.Eric, "hairColour", Colour.Orange, game.allCharacters)
    assert(filteredChars1 ===
      Seq(game.Alex, game.Anita, game.Anne, game.Bernard, game.Charles, game.David, game.Eric)) // without orange hair
    val filteredChars2 = game.poseQuestion(game.Eric, "hatColour", Some(Colour.Grey), filteredChars1)
    assert(filteredChars2 === Seq(game.Eric)) // wearing a grey hat
  }

  "poseQuestion" should "throw an exception if the guess value is invalid" in {
    val game = GuessWho()

    val invalidGuess1 = intercept[Exception](game.poseQuestion(game.Bernard, "gender", "female", game.allCharacters))
    assert(invalidGuess1.getMessage === "Guess value must be a valid Gender")
    val invalidGuess2 = intercept[Exception](game.poseQuestion(game.Bernard, "gender", true, game.allCharacters))
    assert(invalidGuess2.getMessage === "Guess value must be a valid Gender")
    val invalidGuess3 = intercept[Exception](game.poseQuestion(game.Bernard, "gender", Colour.Brown, game.allCharacters))
    assert(invalidGuess3.getMessage === "Guess value must be a valid Gender")

    val invalidGuess4 = intercept[Exception](game.poseQuestion(game.Bernard, "glasses", "false", game.allCharacters))
    assert(invalidGuess4.getMessage === "Guess value must be a Boolean")
    val invalidGuess5 = intercept[Exception](game.poseQuestion(game.Bernard, "beard", Colour.Black, game.allCharacters))
    assert(invalidGuess5.getMessage === "Guess value must be a Boolean")

    val invalidGuess6 = intercept[Exception](game.poseQuestion(game.Bernard, "hairColour", Gender.Male, game.allCharacters))
    assert(invalidGuess6.getMessage === "Guess value must be a valid Colour")
    val invalidGuess7 = intercept[Exception](game.poseQuestion(game.Bernard, "eyeColour", 24, game.allCharacters))
    assert(invalidGuess7.getMessage === "Guess value must be a valid Colour")
    val invalidGuess8 = intercept[Exception](game.poseQuestion(game.Bernard, "eyeColour", "blue", game.allCharacters))
    assert(invalidGuess8.getMessage === "Guess value must be a valid Colour")

    val invalidGuess9 = intercept[Exception](game.poseQuestion(game.Bernard, "hatColour", "None", game.allCharacters))
    assert(invalidGuess9.getMessage === "Guess value type must be a valid Option[Colour]")
    val invalidGuess10 = intercept[Exception](game.poseQuestion(game.Bernard, "hatColour", Colour.Pink, game.allCharacters))
    assert(invalidGuess10.getMessage === "Guess value type must be a valid Option[Colour]")
  }

  "poseQuestion" should "throw an exception if the guess attribute is invalid" in {
    val game = GuessWho()
    val invalidAttribute1 = intercept[Exception](game.poseQuestion(game.Bernard, "clothes", Colour.Black, game.allCharacters))
    assert(invalidAttribute1.getMessage === "Invalid guess attribute")
    val invalidAttribute2 = intercept[Exception](game.poseQuestion(game.Bernard, "HAT", true, game.allCharacters))
    assert(invalidAttribute2.getMessage === "Invalid guess attribute")
  }

  "guessCharacter" should "filter the list of characters and print the correct result" in {
    val game = GuessWho()
    val stream = new java.io.ByteArrayOutputStream()

    assert(game.guessCharacter(game.Bill, "biLL", 1, game.allCharacters) === Seq(game.Bill))
    Console.withOut(stream) {
      game.guessCharacter(game.Bill, "biLL", 1, game.allCharacters)
    }
    assert(stream.toString.strip === "Player 1 wins! The character was Bill.")

    stream.reset()
    assert(game.guessCharacter(game.Bill, "Alfred", 2, game.allCharacters) ===
      Seq(game.Alex, game.Anita, game.Anne, game.Bernard, game.Bill, game.Charles, game.Claire, game.David, game.Eric))
    Console.withOut(stream) {
      game.guessCharacter(game.Bill, "Alfred", 2, game.allCharacters)
    }
    assert(stream.toString.strip === "Player 2's guess is incorrect. The character is not Alfred.")

    stream.reset()
    assert(game.guessCharacter(game.Bill, "anita", 1, Seq(game.Anita, game.Bill, game.Eric)) ===
      Seq(game.Bill, game.Eric))
    Console.withOut(stream) {
      game.guessCharacter(game.Bill, "anita", 1, Seq(game.Anita, game.Bill, game.Eric))
    }
    assert(stream.toString.strip === "Player 1's guess is incorrect. The character is not Anita.")
  }

  "guessCharacter" should "throw an exception if an invalid name is input" in {
    val game = GuessWho()
    //    assertThrows[Exception] { // Result type: Assertion
    //      game.guessCharacter(game.Bill, "Angela", 2, game.allCharacters)
    //    }

    val invalidGuess = intercept[Exception](game.guessCharacter(game.Bill, "Angela", 2, game.allCharacters))
    //    assert(invalidGuess.isInstanceOf[Exception])
    assert(invalidGuess.getMessage === "Invalid name")
  }
}
