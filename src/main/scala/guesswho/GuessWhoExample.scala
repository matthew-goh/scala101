package guesswho

object GuessWhoExample extends App{
  val game = GuessWho()

  val selectedCharP1 = game.selectCharacter(game.allCharacters) // P1 must guess this
  val selectedCharP2 = game.selectCharacter(game.allCharacters) // P2 must guess this
  println(s"Selected characters: P1 ${selectedCharP1.name}, P2 ${selectedCharP2.name}")

//  val initBoard = game.allCharacters
//  val P1QuestionList = List((Gender, GenderEnum.Male), (Hat, true), (HairColour, Colour.Black))
//  val P1QuestionListFolded = P1QuestionList.foldLeft(initBoard) {(accumulator, guess) =>
//    game.poseQuestion(selectedCharP1, guessAttribute = guess._1, guessValue = guess._2, accumulator)
//  }
//  game.printRemainingNames(P1QuestionListFolded)
//
//  val P2QuestionList = List((Gender, GenderEnum.Female), (Moustache, true), (RosyCheeks, true))
//  val P2QuestionListFolded = P2QuestionList.foldLeft(initBoard) {(accumulator, guess) =>
//    game.poseQuestion(selectedCharP2, guessAttribute = guess._1, guessValue = guess._2, accumulator)
//  }
//  game.printRemainingNames(P2QuestionListFolded)

  var charsOnBoardP1 = game.allCharacters
  var charsOnBoardP2 = game.allCharacters

  charsOnBoardP1 = game.poseQuestion(selectedCharP1, Gender, GenderEnum.Male, charsOnBoardP1)
  game.printRemainingNames(charsOnBoardP1)
  charsOnBoardP2 = game.poseQuestion(selectedCharP2, Gender, GenderEnum.Female, charsOnBoardP2)
  game.printRemainingNames(charsOnBoardP2)

  charsOnBoardP1 = game.poseQuestion(selectedCharP1, Hat, true, charsOnBoardP1)
  game.printRemainingNames(charsOnBoardP1)
  charsOnBoardP2 = game.poseQuestion(selectedCharP2, Moustache, true, charsOnBoardP2)
  game.printRemainingNames(charsOnBoardP2)

  charsOnBoardP1 = game.poseQuestion(selectedCharP1, HairColour, Colour.Black, charsOnBoardP1)
  game.printRemainingNames(charsOnBoardP1)
  charsOnBoardP2 = game.poseQuestion(selectedCharP2, RosyCheeks, true, charsOnBoardP2)
  game.printRemainingNames(charsOnBoardP2)

  // P1 guesses randomly from the remaining names
  val nameToGuess = game.selectCharacter(charsOnBoardP1).name
  val charsOnBoardP1Guess4 = game.guessCharacter(selectedCharP1, nameToGuess, 1, charsOnBoardP1)
  game.printRemainingNames(charsOnBoardP1Guess4)
}
