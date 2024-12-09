package guessbook

import guesswho.GenderEnum

import scala.util.Random

case class GuessBook() {
  // instantiate all books
  val TheHonjinMurders: Book = Book(title = "The Honjin Murders", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val TheVillageOfEightGraves: Book = Book(title = "The Village of Eight Graves", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val TheDecagonHouseMurders: Book = Book(title = "The Decagon House Murders", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.ClosedCircle))

  val TheMillHouseMurders: Book = Book(title = "The Mill House Murders", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.ClosedCircle))

  val TheTokyoZodiacMurders: Book = Book(title = "The Tokyo Zodiac Murders", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.OpenInvestigation))

  val DeathAmongTheUndead: Book = Book(title = "Death Among the Undead", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.ClosedCircle))

  val TheNohMaskMurder: Book = Book(title = "The Noh Mask Murder", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.Amateur, Series -> false, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val DeathInTheHouseOfRain: Book = Book(title = "Death in the House of Rain", attributes = Map(CountryOfOrigin -> Country.Taiwan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.ClosedCircle))

  val Malice: Book = Book(title = "Malice", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.Police, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.HowCatchEm))

  val SalvationOfASaint: Book = Book(title = "Salvation of a Saint", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.HowCatchEm))

  val PrayingMantis: Book = Book(title = "Praying Mantis", attributes = Map(CountryOfOrigin -> Country.India, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.ClosedCircle))

  val Hyouka: Book = Book(title = "Hyouka", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.OpenInvestigation))

  val TheSevenDeathsOfEvelynHardcastle: Book = Book(title = "The Seven Deaths of Evelyn Hardcastle", attributes = Map(CountryOfOrigin -> Country.UK, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.ClosedCircle))

  val TheParisApartment: Book = Book(title = "The Paris Apartment", attributes = Map(CountryOfOrigin -> Country.UK, Honkaku -> false, AuthorGender -> GenderEnum.Female,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val TheExaminer: Book = Book(title = "The Examiner", attributes = Map(CountryOfOrigin -> Country.UK, Honkaku -> false, AuthorGender -> GenderEnum.Female,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val TheWordIsMurder: Book = Book(title = "The Word is Murder", attributes = Map(CountryOfOrigin -> Country.UK, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.OpenInvestigation))

  val DanganronpaKirigiri: Book = Book(title = "Danganronpa Kirigiri", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.PrivateProfessional, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.ClosedCircle))

  val TokyoExpress: Book = Book(title = "Tokyo Express", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.Police, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.HowCatchEm))

  val TheDarkMaidens: Book = Book(title = "The Dark Maidens", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Female,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val TheAosawaMurders: Book = Book(title = "The Aosawa Murders", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> false, AuthorGender -> GenderEnum.Female,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.HowCatchEm))

  val LendingTheKeyToTheLockedRoom: Book = Book(title = "Lending the Key to the Locked Room", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.PrivateProfessional, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.OpenInvestigation))

  val TheKubishimeRomanticist: Book = Book(title = "The Kubishime Romanticist", attributes = Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
    SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.RestrictedCommunity))

  val allBooks: Seq[Book] = Seq(TheHonjinMurders, TheVillageOfEightGraves, TheDecagonHouseMurders, TheMillHouseMurders, TheTokyoZodiacMurders, DeathAmongTheUndead,
    TheNohMaskMurder, DeathInTheHouseOfRain, Malice, SalvationOfASaint, PrayingMantis, Hyouka, TheSevenDeathsOfEvelynHardcastle, TheParisApartment,
    TheExaminer, TheWordIsMurder, DanganronpaKirigiri, TokyoExpress, TheDarkMaidens, TheAosawaMurders, LendingTheKeyToTheLockedRoom, TheKubishimeRomanticist)
    .sortBy(_.title)
  val allTitles: Seq[String] = allBooks.map(book => book.title)

  // Print titles of books remaining on board
  def printRemainingBooks(bookSeq: Seq[Book], maxBooksPerLine: Int): Unit = {
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
    // check that guessValue is of the correct type for the attribute
    guessChecker.validateGuess(guessAttribute, guessValue)

    // if selected character has the guessed attribute's value, keep characters with the guessed value
    def filterCharacters(selectedBook: Book, guessAttribute: BookAttribute, guessValue: T,
                         remainingBooks: Seq[Book]): Seq[Book] = {
      if (selectedBook.attributes(guessAttribute) == guessValue){
        remainingBooks.filter {
          ch => ch.attributes(guessAttribute) == guessValue
        }
      } else { // keep characters without the guessed value
        remainingBooks.filter {
          ch => ch.attributes(guessAttribute) != guessValue
        }
      }
    }

    guessChecker.printFeedback(selectedBook, guessAttribute, guessValue)
    filterCharacters(selectedBook, guessAttribute, guessValue, remainingBooks)
  }

  // Guess a character to end the game
  // input selected character, guessed character and Seq of remaining characters
  // print the outcome of the guess and return a tuple containing
  // (i) Seq of characters after filtering (the guessed character only if correct; remove the guessed character if not)
  // (ii) Boolean for whether the guess was correct
  def guessCharacter(selectedBook: Book, guessedTitle: String, guesser: Int, remainingBooks: Seq[Book]): (Seq[Book], Boolean) = {
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
