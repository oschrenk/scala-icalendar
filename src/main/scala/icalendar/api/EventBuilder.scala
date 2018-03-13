package icalendar.api

import java.time.ZonedDateTime

import icalendar.api.Event.PristineEvent
import icalendar.extensions.{ XAddress, XAppleStructuredLocation, XTitle }
import icalendar.standard.CalendarProperties.Prodid
import icalendar.standard.Properties._
import icalendar.standard.ValueTypes.{ DateTime, GeoLocation }
import icalendar.standard.{ VCalendar, VEvent }

object EventBuilder {
  def default: EventBuilder[PristineEvent] =
    new EventBuilder[PristineEvent]()
}

class EventBuilder[E <: Event]() {

  import Event._

  private var title: Option[String] = None
  private var startTime: Option[ZonedDateTime] = None
  private var endTime: Option[ZonedDateTime] = None
  private var notes: Option[String] = None
  private var location: Option[(String, String, Double, Double)] = None

  def titled(title: String): EventBuilder[Event with Title] = {
    val eb = new EventBuilder[Event with Title]()
    eb.title = Some(title)
    eb
  }

  def starting(time: ZonedDateTime): EventBuilder[Event with StartDate] = {
    val eb = new EventBuilder[Event with StartDate]()
    eb.startTime = Some(time)
    eb
  }

  def ending(time: ZonedDateTime): EventBuilder[Event with EndDate] = {
    val eb = new EventBuilder[Event with EndDate]()
    eb.endTime = Some(time)
    eb
  }

  def notes(t: String): EventBuilder[Event with Notes] = {
    val eb = new EventBuilder[Event with Notes]()
    eb.notes = Some(t)
    eb
  }

  def at(title: String, address: String, lat: Double, lon: Double): EventBuilder[Event with Location] = {
    val eb = new EventBuilder[Event with Location]()
    eb.location = Some((title, address, lat, lon))
    eb
  }

  def build(implicit ev: E =:= MinimalEvent): VCalendar = {
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

    val event = new VEvent(uid, Seq(summary, dtstart, dtend) ++ description ++ loc)
    new VCalendar(Prodid("-//oschrenk/spacetime/en"), Seq(event))
  }
}
