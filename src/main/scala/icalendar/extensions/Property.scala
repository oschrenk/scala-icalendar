package icalendar.extensions

import icalendar.standard.PropertyParameters.Value
import icalendar.standard.ValueTypes.{ GeoLocation, Text }
import icalendar.standard.{ Property, PropertyParameter }

/**
 * {{{
 * LOCATION:De School\nDr. Jan van Breemenstraat 1\, 1056 AB Amsterdam\, Ne
 * therlands
 * X-APPLE-STRUCTURED-LOCATION;VALUE=URI;X-ADDRESS="Dr. Jan van Breemenstra
 *  at 1, 1056 AB Amsterdam, Netherlands";X-TITLE=De School:geo:52.369955,4.843713
 * }}}
 */
case class XAppleStructuredLocation(value: GeoLocation, address: XAddress, title: XTitle) extends Property[GeoLocation] {
  override lazy val name: String = "X-APPLE-STRUCTURED-LOCATION"
  override val parameters: Seq[PropertyParameter[_]] = Seq(Value("URI"), address, title)
}

case class XAddress(valu: Text) extends PropertyParameter[Text] {
  // a bit hacky as further down the line this gets expanded to X-Address
  override lazy val name: String = "XAddress"
  // even more hacky, Apple wants quoted text
  override val value: Text = Text.fromString("\"" + s"${valu.text}" + "\"")
}
case class XTitle(value: Text) extends PropertyParameter[Text] {
  // a bit hacky as further down the line this gets expanded to X-Title
  override lazy val name: String = "XTitle"
}
