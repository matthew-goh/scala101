val x: List[Int] = List(1, 2, 3)
val y: Seq[Int] = Seq(1, 2, 3)
val z: Map[String, Int] = Map("num1" -> 3, "num2" -> 4, "num43" -> 30123) // like a dictionary, but types are fixed

// indexing a Seq
val testSeq: Seq[String] = Seq("a","b","c")
val letter = testSeq(0) // "a"
//val failedLetter = testSeq(10) // java.lang.IndexOutOfBoundsException: 10

// accessing values in a Map
val testMap: Map[Int, String] = Map(10 -> "dog", 11 -> "cat", 12 -> "leopard")
val animal = testMap(10) // "dog"
//val failedAnimal = testMap(13) // java.util.NoSuchElementException: key not found: 13
val animalOpt = testMap.get(13) // Option[String] = None

// map: iterate through every item in a collection and perform a function on it
val testSeq = Seq(1, 2, 3)
val doubledSeq = testSeq.map {
  num => num * 2
}
// val doubledSeq: Seq[Int] = List(2, 4, 6)

// filter: iterate through every item in a collection and remove it if a condition is not met
val testSeq = Seq(1, 2, 3, 4, 5)
val filteredSeq = testSeq.filter {
  num => num > 3
}
// val filteredSeq: Seq[Int] = List(4, 5)

// exists: iterate through every item in a collection and return true if a condition is met at least once
val testSeq = Seq(1, 3, 5)
val outcome = testSeq.exists {
  num => num > 3
}
// outcome: Boolean = true