package icalendar.util

import org.scalatest.Matchers
import org.scalatest.matchers.Matcher

trait ObjectSpecification {
  implicit def liftOption[T](value: T): Option[T] = Some(value)

  def haveLines(lines: String*): Matcher[String] = Matchers.equal(lines.mkString("\r\n") + "\r\n")
}
