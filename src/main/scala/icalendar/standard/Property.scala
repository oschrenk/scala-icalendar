package icalendar.standard

import java.net.{ URI, URL }
import java.time.{ LocalDate, ZoneOffset, ZonedDateTime }

sealed abstract class Property[T <: ValueType] { self: Product =>
  lazy val name: String = nameFromClassName(this)

  val parameters: Seq[PropertyParameter[_]] = self.productIterator.collect {
    case Some(p: PropertyParameter[_]) => p
    case p: PropertyParameter[_] => p
  }.toSeq
  val value: T
}

object CalendarProperties {
  import ValueTypes._

  case class Prodid(value: Text) extends Property[Text]
  case class Version(value: Text) extends Property[Text]
}

/** Component properties */
object Properties {
  import PropertyParameters._
  import ValueTypes._

  /** Descriptive */
  case class Attach(value: EitherType[Uri, Binary]) extends Property[EitherType[Uri, Binary]]
  case class Categories(value: ListType[Text]) extends Property[ListType[Text]]
  case class Classification(value: ClassificationValue) extends Property[ClassificationValue] {
    override lazy val name = "Class"
  }
  case class Description(value: Text, altrep: Option[Altrep] = None) extends Property[Text]
  case class Location(value: Text, language: Option[Language]) extends Property[Text]
  case class Summary(value: Text, language: Option[Language] = None) extends Property[Text]

  /** Date and Time */
  case class Dtstart(value: EitherType[DateTime, Date]) extends Property[EitherType[DateTime, Date]]
  object Dtstart {
    implicit def optionFromDateTime(dt: ZonedDateTime): Option[Dtstart] = Some(Dtstart(EitherType(Left(dt))))
    implicit def optionFromLocalDate(ld: LocalDate): Option[Dtstart] = Some(Dtstart(EitherType(Right(ld))))
  }
  case class Dtend(value: EitherType[DateTime, Date]) extends Property[EitherType[DateTime, Date]]
  object Dtend {
    implicit def optionFromDateTime(dt: ZonedDateTime): Option[Dtend] = Some(Dtend(EitherType(Left(dt))))
    implicit def optionFromLocalDate(ld: LocalDate): Option[Dtend] = Some(Dtend(EitherType(Right(ld))))
  }
  case class FreeBusy(value: ListType[Period], fbtype: Option[Fbtype] = None) extends Property[ListType[Period]]

  /** Relationship */
  case class Attendee(value: CalAddress) extends Property[CalAddress]
  case class Organizer(value: CalAddress) extends Property[CalAddress]
  case class Url(value: Uri) extends Property[Uri]
  object Url {
    implicit def optionFromURL(url: URL): Option[Url] = Some(Url(url))
    implicit def optionFromURI(uri: URI): Option[Url] = Some(Url(uri))
  }

  /**
   * This property defines the persistent, globally unique identifier for
   * the calendar component.
   *
   * The "UID" itself MUST be a globally unique identifier.
   *
   *  The generator of the identifier MUST guarantee that the identifier
   *  is unique.  There are several algorithms that can be used to
   *  accomplish this.  A good method to assure uniqueness is to put the
   *  domain name or a domain literal IP address of the host on which
   *  the identifier was created on the right-hand side of an "@", and
   *  on the left-hand side, put a combination of the current calendar
   *  date and time of day (i.e., formatted in as a DATE-TIME value)
   *  along with some other currently unique (perhaps sequential)
   *  identifier available on the system (for example, a process id
   *  number).  Using a DATE-TIME value on the left-hand side and a
   *  domain name or domain literal on the right-hand side makes it
   *  possible to guarantee uniqueness since no two hosts should be
   *  using the same domain name or IP address at the same time.  Though
   *  other algorithms will work, it is RECOMMENDED that the right-hand
   *  side contain some domain identifier (either of the host itself or
   *  otherwise) such that the generator of the message identifier can
   *  guarantee the uniqueness of the left-hand side within the scope of
   *  that domain.    *
   *
   * @param value the value of the uid
   *
   * See https://tools.ietf.org/html/rfc5545#section-3.8.4.7
   */
  case class Uid(value: Text) extends Property[Text]

  /** Change Management */
  case class Dtstamp(value: DateTime) extends Property[DateTime]
  object Dtstamp {
    def now(): Dtstamp = Dtstamp(ZonedDateTime.now(ZoneOffset.UTC))
    implicit def optionFromDateTime(dt: ZonedDateTime): Option[Dtstamp] = Some(Dtstamp(dt))
  }
}
