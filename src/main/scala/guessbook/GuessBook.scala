package guessbook

import guessbook.BookInfo._

import scala.util.Random

object GuessBook {

  val allBooks: Seq[Book] = Seq(TheHonjinMurders, TheVillageOfEightGraves, TheDecagonHouseMurders, TheMillHouseMurders, TheTokyoZodiacMurders,
    DeathAmongTheUndead, TheNohMaskMurder, DeathInTheHouseOfRain, Malice, SalvationOfASaint, PrayingMantis, Hyouka,
    TheSevenDeathsOfEvelynHardcastle, TheParisApartment, TheExaminer, TheWordIsMurder, DanganronpaKirigiri, TokyoExpress,
    TheDarkMaidens, TheAosawaMurders, LendingTheKeyToTheLockedRoom, TheKubishimeRomanticist, OneByOne)
    .sortBy(_.title)
  val allTitles: Seq[String] = allBooks.map(book => book.title)

  // Print titles of books remaining on board
  def printRemainingBooks(bookSeq: Seq[Book], maxBooksPerLine: Int = 5): Unit = {
    val titleSeq = bookSeq.map(book => book.title)

    // concatenate 5 titles to be printed on each line
    val titleGroups = titleSeq.grouped(maxBooksPerLine).toList
    def concatenatedLines(groupedStrings: Seq[Seq[String]]): String = {
      groupedStrings.length match {
        case 0 => ""
        case 1 => groupedStrings.head.mkString(", ")
        case _ => groupedStrings.head.mkString(", ") + ",\n" + concatenatedLines(groupedStrings.tail)
      }
    }
    println(concatenatedLines(titleGroups))
  }

  // Randomly select a book from the input list
  def selectBook(bookSeq: Seq[Book]): Book =
    bookSeq(Random.between(0, bookSeq.length))


  // input selected book, attribute being guessed, guessed value of attribute and Seq of remaining books
  // return Seq of books after filtering based on the question
  def poseQuestion[T](selectedBook: Book, guessAttribute: BookAttribute, guessValue: T,
                   remainingBooks: Seq[Book])(implicit guessChecker: BookGuessChecker[T]) : Seq[Book] = {
    // check that guessAttribute is valid given the guessValue type
    guessChecker.validateGuess(guessAttribute, guessValue)

    // if selected book has the guessed attribute's value, keep books with the guessed value
    def filterBooks(selectedBook: Book, guessAttribute: BookAttribute, guessValue: T,
                    remainingBooks: Seq[Book]): Seq[Book] = {
      if (selectedBook.attributes(guessAttribute) == guessValue){
        remainingBooks.filter {
          book => book.attributes(guessAttribute) == guessValue
        }
      } else { // keep books without the guessed value
        remainingBooks.filter {
          book => book.attributes(guessAttribute) != guessValue
        }
      }
    }

    guessChecker.printFeedback(selectedBook, guessAttribute, guessValue)
    filterBooks(selectedBook, guessAttribute, guessValue, remainingBooks)
  }

  // Guess a character to end the game
  // input selected character, guessed character and Seq of remaining characters
  // print the outcome of the guess and return a tuple containing
  // (i) Seq of characters after filtering (the guessed character only if correct; remove the guessed character if not)
  // (ii) Boolean for whether the guess was correct
  def guessBookTitle(selectedBook: Book, guessedTitle: String, guesser: Int, remainingBooks: Seq[Book]): (Seq[Book], Boolean) = {
    val guessedTitleFormatted =
      allTitles.find(title => title.toLowerCase == guessedTitle.toLowerCase)
        .getOrElse(throw new Exception("Invalid title"))

    guessedTitleFormatted match {
      case selectedBook.title => {
        println(s"Player $guesser wins! The book was $guessedTitleFormatted.")
        (remainingBooks.filter { book => book.title == guessedTitleFormatted }, true)
      }
      case _ => {
        println(s"Player $guesser's guess is incorrect. The book is not $guessedTitleFormatted.")
        (remainingBooks.filter { book => book.title != guessedTitleFormatted }, false)
      }
    }
  }
}
