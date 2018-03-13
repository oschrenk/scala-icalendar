package icalendar
package standard

import java.time.{ ZoneOffset, ZonedDateTime }

import icalendar.standard.Properties._
import icalendar.standard.ValueTypes.{ ListType, Private }
import org.scalatest._

class ComponentsSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.6 Calendar Components" should {
    "3.6.1 Event" in {
      val dtstamp: Dtstamp = ZonedDateTime.of(1997, 9, 1, 13, 0, 0, 0, ZoneOffset.UTC)
      val dtstart: Dtstart = ZonedDateTime.of(1997, 9, 3, 16, 30, 0, 0, ZoneOffset.UTC)
      val dtend: Dtend = ZonedDateTime.of(1997, 9, 3, 19, 0, 0, 0, ZoneOffset.UTC)
      val classification = Classification(Private)
      val summary = Summary("Annual Employee Review")
      val categories = Categories(ListType("BUSINESS", "HUMAN RESOURCES"))
      Formatter.asIcal(
        new VEvent(
          uid = Uid("19970901T130000Z-123401@example.com"),
          Seq(dtstart, dtend, classification, summary, categories),
          Seq.empty,
          dtstamp)) should
        haveLines(
          "BEGIN:VEVENT",
          "UID:19970901T130000Z-123401@example.com",
          "DTSTAMP:19970901T130000Z",
          "DTSTART:19970903T163000Z",
          "DTEND:19970903T190000Z",
          "CLASS:PRIVATE",
          "SUMMARY:Annual Employee Review",
          "CATEGORIES:BUSINESS,HUMAN RESOURCES",
          "END:VEVENT")
    }
  }
}
