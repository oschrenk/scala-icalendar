package icalendar.api

import java.time.ZonedDateTime

import icalendar.standard.{ VCalendar, VEvent }
import icalendar.standard.CalendarProperties.{ Prodid, Version }
import icalendar.standard.Properties._
import icalendar.standard.ValueTypes.DateTime

object Event {
  def from(title: String, start: ZonedDateTime, end: ZonedDateTime, notes: Option[String]): VCalendar = {

    // the rfc recommends a datetime string, a unique identifier, an at sign
    // and the host eg "19970901T130000Z-123401@example.com"
    // we keep it simple here and create a guid
    val uid = Uid(java.util.UUID.randomUUID().toString)
    val summary = Some(Summary(title))
    val dtstart = Some(Dtstart(DateTime(start)))
    val dtend = Some(Dtend(DateTime(end)))
    val description = notes.map(n => Description(n))

    val event = VEvent(uid = uid, summary = summary, dtstart = dtstart, dtend = dtend, description = description)
    VCalendar(Prodid("-//oschrenk/spacetime/en"), Version("2.0"), Seq(event))
  }
}
