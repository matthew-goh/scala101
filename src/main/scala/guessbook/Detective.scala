package guessbook

object Detective extends Enumeration with BookAttributeEnum {
  val Police = Value("police")
  val PrivateProfessional = Value("private professional")
  val Amateur = Value("amateur")
  val OrdinaryPerson = Value("ordinary person / none at all")
}
