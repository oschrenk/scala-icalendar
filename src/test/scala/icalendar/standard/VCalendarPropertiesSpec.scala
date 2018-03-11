package icalendar
package standard

import org.scalatest._

import icalendar.standard.CalendarProperties._
import icalendar.standard.Formatter._

class VCalendarPropertiesSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.7 Calendar Properties" should {
    "3.7.3 Product Identifier" in {
      asIcal(
        Prodid("-//ABC Corporation//NONSGML My Product//EN")) should
        haveLines("""PRODID:-//ABC Corporation//NONSGML My Product//EN""")
    }
  }
}
