package icalendar.extensions

import icalendar.standard.Formatter._
import icalendar.standard.PropertyParameters._
import icalendar.standard.ValueTypes._
import icalendar.util.ObjectSpecification
import org.scalatest._

class ExtensionPropertyParametersSpec extends WordSpec with Matchers with ObjectSpecification {
  "Extension Data Types" in {
    val geo = GeoLocation(52.369955f, 4.843713f)
    val value= Value("URI")
    val xtitle = XTitle("De School")
    val xaddress = XAddress("\"Dr. Jan van Breemenstraat 1, 1056 AB Amsterdam, Netherlands\"")
    asIcal(
      XAppleStructuredLocation(geo, value, xaddress, xtitle)
    ) should
      haveLines(
        """X-APPLE-STRUCTURED-LOCATION;VALUE=URI;X-ADDRESS="Dr. Jan van Breemenstra""",
        """at 1, 1056 AB Amsterdam, Netherlands";X-TITLE=De School:geo:52.369955,4.843713"""
      )
  }
}
