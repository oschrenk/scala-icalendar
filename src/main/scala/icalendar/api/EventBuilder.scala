package icalendar.api

import java.time.ZonedDateTime

import icalendar.extensions.{ XAddress, XAppleStructuredLocation, XTitle }
import icalendar.standard.CalendarProperties.Prodid
import icalendar.standard.Properties._
import icalendar.standard.ValueTypes.{ DateTime, GeoLocation }
import icalendar.standard.{ VCalendar, VEvent }

object EventBuilder {
  def default: EventBuilder =
    new EventBuilder()
}

class EventBuilder() {

  private var title: Option[String] = None
  private var startTime: Option[ZonedDateTime] = None
  private var endTime: Option[ZonedDateTime] = None
  private var notes: Option[String] = None
  private var location: Option[(String, String, Double, Double)] = None

  def titled(title: String): EventBuilder = {
    this.title = Some(title)
    this
  }

  def starting(time: ZonedDateTime): EventBuilder = {
    this.startTime = Some(time)
    this
  }

  def ending(time: ZonedDateTime): EventBuilder = {
    this.endTime = Some(time)
    this
  }

  def notes(t: String): EventBuilder = {
    this.notes = Some(t)
    this
  }

  def at(title: String, address: String, lat: Double, lon: Double): EventBuilder = {
    this.location = Some((title, address, lat, lon))
    this
  }

  def build(): VCalendar = {
    // the rfc recommends a datetime string, a unique identifier, an at sign
    // and the host eg "19970901T130000Z-123401@example.com"
    // we keep it simple here and create a guid
    val uid = Uid(java.util.UUID.randomUUID().toString)
    val summary = Summary(title.get)
    val dtstart = Dtstart(DateTime(startTime.get))
    val dtend = Dtend(DateTime(endTime.get))
    val description = notes.map(n => Description(n)).toSeq
    val loc = {
      location.map {
        case (t, a, lat, lon) =>
          val standardLocation = Location(s"$t\n$a", None)
          val appleLocation = XAppleStructuredLocation(GeoLocation(lat.toFloat, lon.toFloat), XAddress(a), XTitle(t))
          Seq(standardLocation, appleLocation)
      }.getOrElse(Seq.empty)
    }

    val event = new VEvent(uid, dtstart, Seq(summary, dtend) ++ description ++ loc)
    VCalendar(Prodid("-//oschrenk/spacetime/en"), Seq(event))
  }
}
