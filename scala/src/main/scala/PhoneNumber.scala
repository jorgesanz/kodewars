import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import scala.util.Random


object PhoneNumber {
  def createPhoneNumber(numbers: Seq[Int]) = {
    //TODO
  }
}



@RunWith(classOf[JUnitRunner])
class CreatePhoneNumberSuite extends FunSpec {
  it(s"should correctly create phone numbers") {
    assert(createPhoneNumber(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)) == "(123) 456-7890")
  }
}