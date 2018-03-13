package icalendar
package standard

import java.time.{ ZoneOffset, ZonedDateTime }

import icalendar.standard.CalendarProperties.Prodid
import icalendar.standard.Properties._
import org.scalatest._

class ICalendarObjectSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.4 iCalendar Object" should {
    "Simple example" in {
      val dtstamp: Dtstamp = ZonedDateTime.of(1997, 6, 10, 17, 23, 45, 0, ZoneOffset.UTC)
      val dtstart: Dtstart = ZonedDateTime.of(1997, 7, 14, 17, 0, 0, 0, ZoneOffset.UTC)
      val dtend: Dtend = ZonedDateTime.of(1997, 7, 15, 4, 0, 0, 0, ZoneOffset.UTC)
      val summary = Summary("Bastille Day Party")
      Formatter.asIcal(
        new VCalendar(
          prodid = Prodid("-//hacksw/handcal//NONSGML v1.0//EN"),
          events = Seq(new VEvent(
            Uid("19970610T172345Z-AF23B2@example.com"),
            Seq(dtstart, dtend, summary),
            Seq.empty,
            dtstamp)))) should
        haveLines(
          "BEGIN:VCALENDAR",
          "PRODID:-//hacksw/handcal//NONSGML v1.0//EN",
          "VERSION:2.0",
          "BEGIN:VEVENT",
          "UID:19970610T172345Z-AF23B2@example.com",
          "DTSTAMP:19970610T172345Z",
          "DTSTART:19970714T170000Z",
          "DTEND:19970715T040000Z",
          "SUMMARY:Bastille Day Party",
          "END:VEVENT",
          "END:VCALENDAR")
    }
  }
}
