package ical

abstract class VObject { self: Product =>
  lazy val name: String = "V" + nameFromClassName(this).toUpperCase

  def properties(): Seq[Property[_ <: ValueType]] =
    self.productIterator.collect {
      case Some(p: Property[_]) => Seq(p)
      case p: Property[_] => Seq(p)
      case list: Seq[_] => list.collect { case p: Property[_] => p }
    }.flatten.toSeq

  def components(): Iterator[VObject] =
    self.productIterator.collect {
      case Some(o: VObject) => Seq(o)
      case o: VObject => Seq(o)
      case list: Seq[_] => list.collect { case o: VObject => o }
    }.flatten
}
