trait Artillery{
  var numGuns: Int
  var range: Double
}
//trait Artillery(val numGuns: Int, val range: Double)

trait RenownedDesigner{
  var name: String
  var location: String
}

class Boat{
  var length: Double = 20
  var width: Double = 5
  var topSpeed: Double = 30

  def isFasterThan(otherBoat: Boat): Boolean = this.topSpeed > otherBoat.topSpeed
}
val boat1 = new Boat()
boat1.topSpeed = 40
val boat2 = new Boat()
boat2.topSpeed = 30
boat1.isFasterThan(boat2)
//class Boat(val length: Double, val width: Double, val topSpeed: Double)

class SailBoat extends Boat{
  var numSails: Int = 1
  var hasOars: Boolean = true
  var canTrack: Boolean = true
}

class LuxurySailBoat extends SailBoat with RenownedDesigner {
  var hasJacuzzi: Boolean = true
  var hasBooze: Boolean = true

  var name = "Michelin"
  var location = "France"

  def luxuryScore(): Int = {
    var score = 0
    if (this.hasJacuzzi) {
      score += 3
    }
    if (this.hasBooze) {
      score += 1
    }
    score
  }

  def isMoreLuxuriousThan(other: LuxurySailBoat): Boolean = {
    this.luxuryScore() > other.luxuryScore()
  }
}

val l1 = new LuxurySailBoat()
l1.hasBooze = false
val l2 = new LuxurySailBoat()
l2.hasJacuzzi = false
l1.isMoreLuxuriousThan(l2) // true

class PirateShip extends SailBoat with Artillery {
  var numGangPlanks: Int = 1

  var numGuns = 4
  var range = 25

  def canOutgun(opponent: PirateShip, oppDist: Double): Boolean = {
    if (this.range >= oppDist && opponent.range >= oppDist){ // both can hit each other
      this.numGuns > opponent.numGuns
    } else if (this.range >= oppDist && opponent.range < oppDist){
      true
    } else { // this ship can't hit opponent
      false
    }
  }
}
val p1 = new PirateShip()
val p2 = new PirateShip()
p2.numGuns = 7
p2.range = 16
p1.canOutgun(p2, 20) // true
p1.canOutgun(p2, 15) // false
p1.canOutgun(p2, 30) // false

class MotorBoat extends Boat{
  var engineSize: Double = 10
  var fuelType: String = "oil"
}

//class WarShip extends MotorBoat{
//  val country: String = "UK"
//}
class Warship (val country: String) extends MotorBoat with Artillery{
  var numGuns = 10
  var range = 50
}

class PacerBoat extends MotorBoat{
  var sponsor: String = "Michelin"
  var quarterMileTime: Double = 10

  def timeToCover(miles: Double): Double = this.quarterMileTime * miles / 0.25
}
val pb = new PacerBoat()
pb.quarterMileTime = 8
pb.timeToCover(36.5) // 1168