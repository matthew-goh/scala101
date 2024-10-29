val age: Int = 13
var highestFilmRating = "TBD"
var acceptableRatings: List[String] = Nil // empty list

if (age < 4){
  highestFilmRating = "None"
} else if (age < 8){
  highestFilmRating = "U"
} else if (age < 12){
  highestFilmRating = "PG"
} else if (age < 15){
  highestFilmRating = "12A"
} else if (age < 18){
  highestFilmRating = "15"
} else {
  highestFilmRating = "18"
}

println(highestFilmRating)

if (age >= 4){
  acceptableRatings = "U" :: acceptableRatings
}
if (age >= 8){
  acceptableRatings = "PG" :: acceptableRatings
}
if (age >= 12){
  acceptableRatings = "12A" :: acceptableRatings
}
if (age >= 15){
  acceptableRatings = "15" :: acceptableRatings
}
if (age >= 18){
  acceptableRatings = "18" :: acceptableRatings
}

println(acceptableRatings)