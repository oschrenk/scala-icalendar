package icalendar

abstract class VObject { self: Product =>
  lazy val name: String = "V" + nameFromClassName(this).toUpperCase

  def properties(): List[Property[_ <: ValueType]] =
    self.productIterator.collect {
      case Some(p: Property[_]) => List(p)
      case p: Property[_] => List(p)
      case list: List[_] => list.collect { case p: Property[_] => p }
    }.flatten.toList

  def components(): Iterator[VObject] =
    self.productIterator.collect {
      case Some(o: VObject) => List(o)
      case o: VObject => List(o)
      case list: List[_] => list.collect { case o: VObject => o }
    }.flatten
}
