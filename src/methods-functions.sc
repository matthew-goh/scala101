//def nameOfMethod(<parameters, but this can be empty>): ReturnType = {
//  <expression 1...N> // steps in the method
//    <last expression results in a value that is of the same type as the ReturnType>
//}

// LAST EXPRESSION REACHED GIVES THE VALUE RETURNED

// parameter price is a BigDecimal; returns a BigDecimal
def priceIncludingVat(price: BigDecimal): BigDecimal = {
  val vatRate = BigDecimal(1.2) // expression
  price * vatRate // last expression - value returned
}

// method with only 1 expression
def priceIncludingVat2(price: BigDecimal): BigDecimal = price * BigDecimal(1.2)

val computerExclVat = BigDecimal(1000)
val computerIncVat = priceIncludingVat(computerExclVat) // results in BigDecimal(1200)


// Impure function: uses values of variables outside the function
var sentence: String = ""

def append1(word: String): String = {
  sentence = sentence + " " + word
  sentence.trim()
}

val a1 = append1("Allo") // "Allo"
val b1 = append1("Allo") // "Allo Allo"

// Pure function: any external info passed in as parameters
// Given the same input(s), the output will always be the same
def append2(sentence:String, word: String): String =
  sentence + " " + word.trim()

val a2 = append2(sentence, "Allo") // "Allo"
val b2 = append2(sentence, "Allo") // "Allo"
