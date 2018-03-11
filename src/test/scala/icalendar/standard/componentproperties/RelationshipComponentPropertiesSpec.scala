package icalendar
package standard
package componentproperties

import org.scalatest._

import icalendar.standard.Properties._
import icalendar.standard.Formatter._

class RelationshipComponentPropertiesSpec extends WordSpec with Matchers with ObjectSpecification {
  "3.8.4 Relationship Component Properties" should {
    "3.8.4.6 Uniform Resource Locator" in {
      asIcal(
        Url("http://example.com/pub/calendars/jsmith/mytime.ics")) should
        haveLines("URL:http://example.com/pub/calendars/jsmith/mytime.ics")
    }
  }
}
