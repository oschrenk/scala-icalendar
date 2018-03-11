package icalendar.standard

import icalendar.standard.CalendarProperties._

case class VCalendar(
  prodid: Prodid,
  version: Version = Version("2.0"),
  // TODO calscale
  // TODO method
  // TODO x-prop / iana-prop
  events: Seq[VEvent] = Nil
// TODO other components
) extends VObject {
  override val name = "VCALENDAR"
}
