package icalendar
package standard

import java.time.{ ZoneOffset, ZonedDateTime }

import icalendar.standard.CalendarProperties.Prodid
import icalendar.standard.Properties.{ Summary, Uid }
import org.scalatest._

class ICalendarObjectSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.4 iCalendar Object" should {
    "Simple example" in {
      Formatter.asIcal(
        VCalendar(
          prodid = Prodid("-//hacksw/handcal//NONSGML v1.0//EN"),
          events = Seq(VEvent(
            uid = Uid("19970610T172345Z-AF23B2@example.com"),
            dtstamp = ZonedDateTime.of(1997, 6, 10, 17, 23, 45, 0, ZoneOffset.UTC),
            dtstart = ZonedDateTime.of(1997, 7, 14, 17, 0, 0, 0, ZoneOffset.UTC),
            dtend = ZonedDateTime.of(1997, 7, 15, 4, 0, 0, 0, ZoneOffset.UTC),
            summary = Summary("Bastille Day Party"))))) should
        haveLines(
          "BEGIN:VCALENDAR",
          "PRODID:-//hacksw/handcal//NONSGML v1.0//EN",
          "VERSION:2.0",
          "BEGIN:VEVENT",
          "DTSTAMP:19970610T172345Z",
          "UID:19970610T172345Z-AF23B2@example.com",
          "DTSTART:19970714T170000Z",
          "DTEND:19970715T040000Z",
          "SUMMARY:Bastille Day Party",
          "END:VEVENT",
          "END:VCALENDAR")
    }
  }
}
