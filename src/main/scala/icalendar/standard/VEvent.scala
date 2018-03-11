package icalendar.standard

import icalendar.standard.Properties._

case class VEvent(
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
  override val name = "VEVENT"
  override def properties(): Seq[Property[_ <: ValueType]] = {
    val constants = super.properties().toList
    if (constants.exists(_.isInstanceOf[Dtstamp])) constants
    else Dtstamp.now() :: constants
  }

}
