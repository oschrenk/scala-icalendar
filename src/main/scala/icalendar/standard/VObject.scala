package icalendar.standard

trait VObject {
  val name: String
  def properties: Seq[Property[_ <: ValueType]]
  def components: Seq[_ <: VObject]
}
