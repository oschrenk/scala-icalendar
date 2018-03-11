package icalendar
package standard

import org.scalatest._

import icalendar.standard.Properties._
import icalendar.standard.Formatter._

class ContentLinesSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.1 Content Lines" should {
    "format a long description" in {
      asIcal(Description(
        "This is a long description that exists on a long line. This is a long description that exists on a long line.")) should
        haveLines(
          "DESCRIPTION:This is a long description that exists on a long line. This is ",
          " a long description that exists on a long line.")
    }
  }
}
