package icalendar.standard

import icalendar.standard.Properties._

class VEvent(uid: Uid, private val props: Seq[Property[_ <: ValueType]], val components: Seq[VObject] = Seq.empty, dtstamp: Dtstamp = Dtstamp.now()) extends VObject {
  override val name = "VEVENT"
  override def properties: Seq[Property[_ <: ValueType]] = {
    uid :: dtstamp :: props.toList
  }
}
