package guesswho

import org.scalatest.flatspec.AnyFlatSpec

class GuessWhoSpec extends AnyFlatSpec {
  "guessWho" should "instantiate character attributes correctly" in {
    val game = GuessWho()
    assert(game.Alex.attributes === Map(Gender -> GenderEnum.Male, Glasses -> false, Moustache -> true, Beard -> false,
      RosyCheeks -> false, HairColour -> Colour.Black, EyeColour -> Colour.Brown, Hat -> false, HatColour -> None))
    assert(game.Alfred.attributes(Moustache) === true)
    assert(game.Anita.name === "Anita")
    assert(game.Anne.attributes(RosyCheeks) === false)
    assert(game.Bernard.attributes(Gender) === GenderEnum.Male)
    assert(game.Bill.attributes(Beard) === true)
    assert(game.Charles.attributes(HairColour) === Colour.Blonde)
    assert(game.Claire.attributes(HatColour) === Some(Colour.Pink))
    assert(game.David.attributes(HatColour) === None)
    assert(game.Eric.attributes(EyeColour) === Colour.Brown)
  }

//  "guessWho" should "reset the game board with all characters" in {
//    val game = GuessWho()
//    assert(game.resetBoard() ===
//      Seq(game.Alex, game.Alfred, game.Anita, game.Anne, game.Bernard, game.Bill, game.Charles, game.Claire, game.David, game.Eric))
//  }

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

  // Feedback: description of test could be based on user's perspective,
  // e.g. filters to female characters if the player guesses that gender is female when the selected character is female
  // Then have separate tests for guesses with different attributes
  "poseQuestion" should "filter the list of characters based on the guessed attribute and value" in {
    val game = GuessWho()
    assert(game.poseQuestion(game.Anne, Gender, GenderEnum.Female, game.allCharacters) ===
      Seq(game.Anita, game.Anne, game.Claire)) // female characters

    val filteredChars1 = game.poseQuestion(game.Eric, HairColour, Colour.Orange, game.allCharacters)
    assert(filteredChars1 ===
      Seq(game.Alex, game.Anita, game.Anne, game.Bernard, game.Charles, game.David, game.Eric)) // without orange hair
    val filteredChars2 = game.poseQuestion(game.Eric, HatColour, Some(Colour.Grey), filteredChars1)
    assert(filteredChars2 === Seq(game.Eric)) // wearing a grey hat
  }

  "poseQuestion" should "throw an exception if the attribute is invalid for the guess value type" in {
    val game = GuessWho()

    val invalidGuess1 = intercept[Exception](game.poseQuestion(game.Bernard, Gender, true, game.allCharacters))
    assert(invalidGuess1.getMessage === "Invalid attribute for a Boolean guess value")

    val invalidGuess2 = intercept[Exception](game.poseQuestion(game.Bernard, Beard, Colour.Brown, game.allCharacters))
    assert(invalidGuess2.getMessage === "Invalid attribute for a colour guess value")

    val invalidGuess3 = intercept[Exception](game.poseQuestion(game.Bernard, HairColour, GenderEnum.Male, game.allCharacters))
    assert(invalidGuess3.getMessage === "Invalid attribute for a gender guess value")

    val invalidGuess4 = intercept[Exception](game.poseQuestion(game.Bernard, EyeColour, Some(Colour.Pink), game.allCharacters))
    assert(invalidGuess4.getMessage === "Invalid attribute for an Some(Colour) guess value")
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
    assert(invalidGuess.getMessage === "Invalid name")
  }
}
