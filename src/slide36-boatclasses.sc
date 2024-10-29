class Artillery{
  val numGuns: Int = 2
  val range: Double = 30
}

class RenownedDesigner(val name: String, val location: String)

class Boat{
  val length: Double = 20
  val width: Double = 5
  val topSpeed: Double = 30
}

//class Boat(val length: Double, val width: Double, val topSpeed: Double)

class SailBoat extends Boat{
  val numSails: Int = 1
  val hasOars: Boolean = true
  val canTrack: Boolean = true
}

class LuxurySailBoat extends SailBoat{
  val hasJacuzzi: Boolean = true
  val hasBooze: Boolean = true
}

class PirateShip extends SailBoat{
  val numGangPlanks: Int = 1
}

class MotorBoat extends Boat{
  val engineSize: Double = 10
  val fuelType: String = "oil"
}

class WarShip extends MotorBoat{
  val country: String = "UK"
}

class PacerBoat extends MotorBoat{
  val sponsor: String = "Michelin"
  val quarterMileTime: Double = 10
}
