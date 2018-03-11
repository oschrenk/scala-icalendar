package icalendar
package standard

import java.time.{ ZoneOffset, ZonedDateTime }

import icalendar.standard.Properties.{ Categories, Classification, Summary, Uid }
import icalendar.standard.ValueTypes.{ ListType, Private }
import org.scalatest._

class ComponentsSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.6 Calendar Components" should {
    "3.6.1 Event" in {
      Formatter.asIcal(
        VEvent(
          dtstamp = ZonedDateTime.of(1997, 9, 1, 13, 0, 0, 0, ZoneOffset.UTC),
          uid = Uid("19970901T130000Z-123401@example.com"),
          dtstart = ZonedDateTime.of(1997, 9, 3, 16, 30, 0, 0, ZoneOffset.UTC),
          dtend = ZonedDateTime.of(1997, 9, 3, 19, 0, 0, 0, ZoneOffset.UTC),
          classification = Classification(Private),
          summary = Summary("Annual Employee Review"),
          categories = Seq(Categories(ListType("BUSINESS", "HUMAN RESOURCES"))))) should
        haveLines(
          "BEGIN:VEVENT",
          "DTSTAMP:19970901T130000Z",
          "UID:19970901T130000Z-123401@example.com",
          "DTSTART:19970903T163000Z",
          "DTEND:19970903T190000Z",
          "CLASS:PRIVATE",
          "SUMMARY:Annual Employee Review",
          "CATEGORIES:BUSINESS,HUMAN RESOURCES",
          "END:VEVENT")
    }
  }
}
