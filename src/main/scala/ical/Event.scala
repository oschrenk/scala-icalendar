package ical

import java.time.ZonedDateTime

import Properties._
import ical.ValueTypes.DateTime

object Event {
  def from(title: String, start: ZonedDateTime, end: ZonedDateTime, notes: Option[String]): Event = {

    // the rfc recommends a datetime string, a unique identifier, an at sign
    // and the host eg "19970901T130000Z-123401@example.com"
    // we keep it simple here and create a guid
    val uid = Uid(java.util.UUID.randomUUID().toString)
    val summary = Some(Summary(title))
    val dtstart = Some(Dtstart(DateTime(start)))
    val dtend = Some(Dtend(DateTime(end)))
    val description = notes.map(n => Description(n))

    Event(uid = uid, summary = summary, dtstart = dtstart, dtend = dtend, description = description)
  }
}

case class Event(
  dtstamp: Option[Dtstamp] = None,
  uid: Uid,
  dtstart: Option[Dtstart] = None,
  dtend: Option[Dtend] = None,
  // TODO spec asks for dtend or duration, atm just support dtend and no duration
  classification: Option[Classification] = None,
  description: Option[Description] = None,
  // geo
  // last-mod
  location: Option[Location] = None,
  organizer: Option[Organizer] = None,
  // priority
  // seq
  // status
  summary: Option[Summary] = None,
  // transp
  url: Option[Url] = None,
  // recurid
  // rrule
  categories: Seq[Categories] = Nil) extends VObject {
  override def properties(): Seq[Property[_ <: ValueType]] = {
    val constants = super.properties().toList
    if (constants.exists(_.isInstanceOf[Dtstamp])) constants
    else Dtstamp.now() :: constants
  }
}
