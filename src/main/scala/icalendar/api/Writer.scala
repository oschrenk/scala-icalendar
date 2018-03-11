package icalendar.api

import icalendar.standard.{ Formatter, VObject }

object Writer {
  def asIcal(vobject: VObject): String = {
    Formatter.asIcal(vobject)
  }

}
