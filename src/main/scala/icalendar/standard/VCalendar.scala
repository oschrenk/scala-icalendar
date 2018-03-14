package icalendar.standard

import icalendar.standard.CalendarProperties._

case class VCalendar(
  prodid: Prodid,
  events: Seq[VEvent] = Seq.empty,
  version: Version = Version("2.0")) extends VObject {
  override val name = "VCALENDAR"

  override def properties: Seq[Property[_ <: ValueType]] = Seq(prodid, version)

  override def components: Seq[_ <: VObject] = events
}
