object GuessWhoExample extends App{
  val game = GuessWho()
  val initBoard = game.resetBoard()

  val selectedCharP1 = game.selectCharacter(game.allCharacters) // P1 must guess this
  val selectedCharP2 = game.selectCharacter(game.allCharacters) // P2 must guess this
  println("Selected characters: P1 " + selectedCharP1.name + ", P2 " + selectedCharP2.name)

  val P1QuestionList = List(("gender", Gender.Male), ("hat", true), ("hairColour", Colour.Black))
  val P1QuestionListFolded = P1QuestionList.foldLeft(initBoard) {(accumulator, guess) =>
    game.poseQuestion(selectedCharP1, guessAttribute = guess._1, guessValue = guess._2, accumulator)
  }
  game.printRemainingNames(P1QuestionListFolded)

  val P2QuestionList = List(("gender", Gender.Female), ("moustache", true), ("rosyCheeks", true))
  val P2QuestionListFolded = P2QuestionList.foldLeft(initBoard) {(accumulator, guess) =>
    game.poseQuestion(selectedCharP2, guessAttribute = guess._1, guessValue = guess._2, accumulator)
  }
  game.printRemainingNames(P2QuestionListFolded)

  // P1 guesses randomly from the remaining names
  val nameToGuess = game.selectCharacter(P1QuestionListFolded).name
  val charsOnBoardP1Guess4 = game.guessCharacter(selectedCharP1, nameToGuess, 1, P1QuestionListFolded)
  game.printRemainingNames(charsOnBoardP1Guess4)
}
