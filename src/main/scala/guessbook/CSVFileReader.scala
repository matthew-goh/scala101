package guessbook

import com.github.tototoshi.csv.CSVReader
import guesswho.GenderEnum

import scala.util.{Failure, Success, Try}

class CSVFileReader {

  // Read book info from csv file
  def getBooksFromCSV(path: String): Seq[Book] = {
    Try {
      val reader: CSVReader = CSVReader.open(path)
      val csvData: List[Map[String, String]] = reader.allWithHeaders()
      reader.close()
      csvData
    } match {
      case Success(csvRows) => {
        // convert each list item (row in the csv) into a Book
        csvRows.map { row =>
          val bookFromRow: Try[Book] = for {
            title <- Try(row("title")) match {
              case Success("") => Failure(new Exception("Blank title found"))
              case t @ _ => t
            }
            countryValue <- Try(Country.withName(row("CountryOfOrigin")))
            honkakuValue <- Try(row("Honkaku").toBoolean)
            genderValue <- Try(GenderEnum.withName(row("AuthorGender")))
            studentsValue <- Try(row("SchoolOrUniversityStudents").toBoolean)
            detectiveValue <- Try(Detective.withName(row("DetectiveType")))
            seriesValue <- Try(row("Series").toBoolean)
            pushkinValue <- Try(row("PushkinVertigo").toBoolean)
            suspectPoolValue <- Try(SuspectPool.withName(row("SuspectPoolType")))
          } yield Book(title, Map(CountryOfOrigin -> countryValue, Honkaku -> honkakuValue, AuthorGender -> genderValue,
            SchoolOrUniversityStudents -> studentsValue, DetectiveType -> detectiveValue, Series -> seriesValue,
            PushkinVertigo -> pushkinValue, SuspectPoolType -> suspectPoolValue))

          bookFromRow match {
            case Success(book) => book
            case Failure(e) => throw new Exception(s"Error reading book info from csv file: ${e.getMessage}")
          }
        }
      }
      case Failure(e) => throw new Exception(s"Could not read csv file from given path: ${e.getMessage}")
    }
  }

}
