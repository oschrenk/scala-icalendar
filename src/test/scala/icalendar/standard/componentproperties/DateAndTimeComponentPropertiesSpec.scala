package icalendar
package standard
package componentproperties

import java.time.{ LocalDate, ZoneOffset, ZonedDateTime }

import org.scalatest._
import icalendar.standard.ValueTypes._
import icalendar.standard.Properties._
import icalendar.standard.Formatter._
import icalendar.util.ObjectSpecification

class DateAndTimeComponentPropertiesSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.8.2 Date and Time Component Properties" should {
    "3.8.2.2 Date-Time End" in {
      asIcal(
        Dtend(DateTime(ZonedDateTime.of(1996, 4, 1, 15, 0, 0, 0, ZoneOffset.UTC)))) should
        haveLines("DTEND:19960401T150000Z")

      asIcal(
        Dtend(Date(LocalDate.parse("1998-07-04")))) should
        haveLines("DTEND;VALUE=DATE:19980704")
    }
  }
}
