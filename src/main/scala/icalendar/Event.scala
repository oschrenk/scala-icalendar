package icalendar

import Properties._

case class Event(
  dtstamp: Option[Dtstamp] = None,
  uid: Uid,
  dtstart: Option[Dtstart] = None,
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
  // dtend/duration
  // ...
  categories: Seq[Categories] = Nil) extends VObject {
  override def properties(): Seq[Property[_ <: ValueType]] = {
    val constants = super.properties().toList
    if (constants.exists(_.isInstanceOf[Dtstamp])) constants
    else Dtstamp.now() :: constants
  }
}
