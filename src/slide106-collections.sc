val names: Seq[String] = Seq("Matthew", "Will", "Aashvin", "Simon", "Joe", "Connie")

// Create a Map of 1-“red”, 2-“yellow”, 3-“blue”, 4-“refrigerator” and play with accessing the elements
val colourMap: Map[Int, String] = Map(1 -> "red", 2 -> "yellow", 3 -> "blue", 4 -> "refrigerator")
val c1 = colourMap(1) // red
val c4 = colourMap(4) // refrigerator
val c2opt = colourMap.get(2) // Option[String] = Some(yellow)
val c8opt= colourMap.get(8) // Option[String] = None
val L1 = List(colourMap(2), colourMap(3)) // List[String] = List(yellow, blue)
val L2 = List(colourMap(2), colourMap.get(7)) // List[java.io.Serializable] = List(yellow, None)

// Write a function to add 1 to all numbers in a Seq
val s1 = Seq(32, 45, -98, 6, 0)
val s1add1 = s1.map {
  n => n + 1
}

// Write a function to remove all even numbers from a collection
val s1filtered = s1.filter {
  n => n % 2 == 1
}

// Write a function that returns true if a Seq has a String that contains the letter “t”
def checkForT(s: Seq[String]) : Boolean = {
  s.exists {
    str => str.contains("t")
  }
}
checkForT(names) // true
//names.exists {
//  str => str.contains("t")
//}