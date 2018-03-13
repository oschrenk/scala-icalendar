package icalendar.api

import java.time.ZonedDateTime

import icalendar.standard.CalendarProperties.Prodid
import icalendar.standard.Properties._
import icalendar.standard.ValueTypes.DateTime
import icalendar.standard.{ VCalendar, VEvent }

sealed trait Event

object Event {

  sealed trait PristineEvent extends Event
  sealed trait Title extends Event
  sealed trait StartDate extends Event
  sealed trait EndDate extends Event
  sealed trait Notes extends Event
  sealed trait Location extends Event
  type MinimalEvent = Event with Title with StartDate with EndDate

  def from(title: String, start: ZonedDateTime, end: ZonedDateTime, notes: Option[String]): VCalendar = {

    // the rfc recommends a datetime string, a unique identifier, an at sign
    // and the host eg "19970901T130000Z-123401@example.com"
    // we keep it simple here and create a guid
    val uid = Uid(java.util.UUID.randomUUID().toString)
    val summary = Summary(title)
    val dtstart = Dtstart(DateTime(start))
    val dtend = Dtend(DateTime(end))
    val description = notes.map(n => Description(n)).toSeq

    val event = new VEvent(uid, Seq(summary, dtstart, dtend) ++ description)
    new VCalendar(Prodid("-//oschrenk/spacetime/en"), Seq(event))
  }
}
