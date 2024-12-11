package guessbook

import guesswho.GenderEnum
import org.scalatest.wordspec.AnyWordSpec

class GuessBookSpec extends AnyWordSpec {
  private val game = GuessBook

  "guessBook" should {
    "instantiate book attributes correctly" in {
      assert(game.TheDecagonHouseMurders.attributes === Map(CountryOfOrigin -> Country.Japan, Honkaku -> true, AuthorGender -> GenderEnum.Male,
        SchoolOrUniversityStudents -> true, DetectiveType -> Detective.Amateur, Series -> true, PushkinVertigo -> true, SuspectPoolType -> SuspectPool.ClosedCircle))
      assert(game.TheHonjinMurders.title === "The Honjin Murders")
      assert(game.TheVillageOfEightGraves.attributes(CountryOfOrigin) === Country.Japan)
      assert(game.TheTokyoZodiacMurders.attributes(Honkaku) === true)
      assert(game.TheNohMaskMurder.attributes(AuthorGender) === GenderEnum.Male)
      assert(game.Malice.attributes(SchoolOrUniversityStudents) === false)
      assert(game.SalvationOfASaint.attributes(DetectiveType) === Detective.PrivateProfessional)
      assert(game.Hyouka.attributes(Series) === true)
      assert(game.TheSevenDeathsOfEvelynHardcastle.attributes(PushkinVertigo) === false)
      assert(game.DanganronpaKirigiri.attributes(SuspectPoolType) === SuspectPool.ClosedCircle)
    }

    "create a sorted list of all book titles" in {
      assert(game.allTitles ===
        Seq("Danganronpa Kirigiri", "Death Among the Undead", "Death in the House of Rain", "Hyouka", "Lending the Key to the Locked Room",
          "Malice", "One By One", "Praying Mantis", "Salvation of a Saint", "The Aosawa Murders", "The Dark Maidens",
          "The Decagon House Murders", "The Examiner", "The Honjin Murders", "The Kubishime Romanticist", "The Mill House Murders",
          "The Noh Mask Murder", "The Paris Apartment", "The Seven Deaths of Evelyn Hardcastle", "The Tokyo Zodiac Murders",
          "The Village of Eight Graves", "The Word is Murder", "Tokyo Express")
      )
    }
  }

  "printRemainingBooks()" should {
    "print the list of book titles, with the correct number of titles on each line" in {
      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        // all printlns in this block will be redirected
        game.printRemainingBooks(game.allBooks, 5)
      }
      assert(stream.toString.strip ===
        "Danganronpa Kirigiri, Death Among the Undead, Death in the House of Rain, Hyouka, Lending the Key to the Locked Room,\n" +
          "Malice, One By One, Praying Mantis, Salvation of a Saint, The Aosawa Murders,\n" +
          "The Dark Maidens, The Decagon House Murders, The Examiner, The Honjin Murders, The Kubishime Romanticist,\n" +
          "The Mill House Murders, The Noh Mask Murder, The Paris Apartment, The Seven Deaths of Evelyn Hardcastle, The Tokyo Zodiac Murders,\n" +
          "The Village of Eight Graves, The Word is Murder, Tokyo Express"
      )

      stream.reset() // clear ByteArrayOutputStream, otherwise the next output will just be appended
      Console.withOut(stream) {
        game.printRemainingBooks(Seq(game.SalvationOfASaint, game.TheDarkMaidens, game.TheParisApartment, game.TheWordIsMurder), 3)
      }
      assert(stream.toString.strip === "Salvation of a Saint, The Dark Maidens, The Paris Apartment,\n" +
        "The Word is Murder")
    }
  }

  "poseQuestion()" should {
    "filter to books written by a female author if the player correctly guesses that the author's gender is female" in {
      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        assert(game.poseQuestion(game.TheParisApartment, AuthorGender, GenderEnum.Female, game.allBooks) ===
          Seq(game.OneByOne, game.TheAosawaMurders, game.TheDarkMaidens, game.TheExaminer, game.TheParisApartment))
      }
      assert(stream.toString.strip === "Yes, the author of the selected book is female")
    }

    "filter to non-Japanese books if the player incorrectly guesses that the book is Japanese" in {
      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        assert(game.poseQuestion(game.TheParisApartment, CountryOfOrigin, Country.Japan, game.allBooks) ===
          Seq(game.DeathInTheHouseOfRain, game.OneByOne, game.PrayingMantis, game.TheExaminer, game.TheParisApartment,
            game.TheSevenDeathsOfEvelynHardcastle, game.TheWordIsMurder))
      }
    }

    "filter to closed circle mysteries if the player correctly guesses that the type of suspect pool is closed circle" in {
      val stream = new java.io.ByteArrayOutputStream()
      Console.withOut(stream) {
        assert(game.poseQuestion(game.TheDecagonHouseMurders, SuspectPoolType, SuspectPool.ClosedCircle,
          Seq(game.DeathAmongTheUndead, game.DeathInTheHouseOfRain, game.Hyouka, game.TheVillageOfEightGraves)) ===
          Seq(game.DeathAmongTheUndead, game.DeathInTheHouseOfRain))
      }
      assert(stream.toString.strip === "Yes, the selected book's type of suspect pool is closed circle")
    }

    "throw an exception if the attribute is invalid for the guess value type" in {
      val invalidGuess1 = intercept[Exception](game.poseQuestion(game.Malice, CountryOfOrigin, true, game.allBooks))
      assert(invalidGuess1.getMessage === "Invalid attribute for a Boolean guess value")

      val invalidGuess2 = intercept[Exception](game.poseQuestion(game.Malice, DetectiveType, Country.Japan, game.allBooks))
      assert(invalidGuess2.getMessage === "Invalid attribute for a country guess value")

      val invalidGuess3 = intercept[Exception](game.poseQuestion(game.Malice, Honkaku, GenderEnum.Male, game.allBooks))
      assert(invalidGuess3.getMessage === "Invalid attribute for a gender guess value")

      val invalidGuess4 = intercept[Exception](game.poseQuestion(game.Malice, SuspectPoolType, Detective.Police, game.allBooks))
      assert(invalidGuess4.getMessage === "Invalid attribute for a detective type guess value")

      val invalidGuess5 = intercept[Exception](game.poseQuestion(game.Malice, AuthorGender, SuspectPool.ClosedCircle, game.allBooks))
      assert(invalidGuess5.getMessage === "Invalid attribute for a suspect pool guess value")
    }
  }

  "guessBookTitle()" should {
    "filter the list of books and print the correct result" in {
      val stream = new java.io.ByteArrayOutputStream()

      Console.withOut(stream) {
        assert(game.guessBookTitle(game.TheDecagonHouseMurders, "the decagon house murders", 1, game.allBooks)
          === (Seq(game.TheDecagonHouseMurders), true))
      }
      assert(stream.toString.strip === "Player 1 wins! The book was The Decagon House Murders.")

      stream.reset()
      Console.withOut(stream) {
        assert(game.guessBookTitle(game.TheDecagonHouseMurders, "Death Among the Undead", 2,
          Seq(game.DeathAmongTheUndead, game.DeathInTheHouseOfRain, game.TheDecagonHouseMurders, game.OneByOne)) ===
          (Seq(game.DeathInTheHouseOfRain, game.TheDecagonHouseMurders, game.OneByOne), false))
      }
      assert(stream.toString.strip === "Player 2's guess is incorrect. The book is not Death Among the Undead.")
    }

    "throw an exception if an invalid title is input" in {
      val invalidGuess = intercept[Exception](game.guessBookTitle(game.TheDecagonHouseMurders, "The Labyrinth House Murders", 2, game.allBooks))
      assert(invalidGuess.getMessage === "Invalid title")
    }
  }
}
