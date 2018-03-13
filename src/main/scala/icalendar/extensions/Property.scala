package icalendar.extensions

import icalendar.standard.PropertyParameters.{LanguageTag, Value}
import icalendar.standard.{Parameterized, Property, PropertyParameter, ValueType}
import icalendar.standard.ValueTypes.{GeoLocation, Text}

/**
  * {{{
  * X-APPLE-STRUCTURED-LOCATION;VALUE=URI;X-ADDRESS="Dr. Jan van Breemenstra
  *  at 1, 1056 AB Amsterdam, Netherlands";X-TITLE=De School:geo:52.369955,4.843713
  * }}}
  * @param value
  */
case class XAppleStructuredLocation(value: GeoLocation, extra: Value, address: XAddress, title: XTitle) extends Property[GeoLocation] {
  override lazy val name: String = "X-APPLE-STRUCTURED-LOCATION"
}

case class XAddress(value: Text) extends PropertyParameter[Text] {
  // a bit hacky as further down the line this gets expanded to X-XAddress
  override lazy val name: String = "XAddress"
}
case class XTitle(value: Text) extends PropertyParameter[Text] {
  // a bit hacky as further down the line this gets expanded to X-Title
  override lazy val name: String = "XTitle"
}
