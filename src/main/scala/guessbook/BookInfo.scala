package guessbook

import guesswho.GenderEnum

object BookInfo {
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

  val OneByOne: Book = Book(title = "One By One", attributes = Map(CountryOfOrigin -> Country.UK, Honkaku -> false, AuthorGender -> GenderEnum.Female,
    SchoolOrUniversityStudents -> false, DetectiveType -> Detective.OrdinaryPerson, Series -> false, PushkinVertigo -> false, SuspectPoolType -> SuspectPool.ClosedCircle))
}
