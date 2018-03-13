package icalendar.api

import java.time.ZonedDateTime

import icalendar.api.Event.PristineEvent
import icalendar.standard.VCalendar

object EventBuilder {
  def default: EventBuilder[PristineEvent] =
    new EventBuilder[PristineEvent]()
}

class EventBuilder[E <: Event]() {

  import Event._

  private var title: Option[String] = None
  private var startTime: Option[ZonedDateTime] = None
  private var endTime: Option[ZonedDateTime] = None

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

  def build(implicit ev: E =:= MinimalEvent): VCalendar = {
    Event.from(title.get, startTime.get, endTime.get, None)
  }
}
