def getBigVal(input1: Int, input2: Int): String = {
  if (input1 > input2) {
    "first"
  } else if (input1 < input2){
    "second"
  } else
    "same"
}

getBigVal(12, 14)
getBigVal(12, 11)
getBigVal(10, 10)


// Return the length of the name which is greater
// If both names are the same length, return 0
def nameLength(firstName: String, surname: String): Int = {
  val fNameLen = firstName.length()
  val lNameLen = surname.length()

  if (fNameLen > lNameLen){
    fNameLen
  } else if (fNameLen < lNameLen){
    lNameLen
  } else{
    0
  }
}

nameLength("Arnold", "Schwarzenegger") // 14
nameLength("Bruce", "Lee") // 5
nameLength("Ethan", "Hawke") // 0
