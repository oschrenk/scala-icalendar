package icalendar

package object standard {

  def nameFromClassName(obj: Any): String = {
    val className = obj.getClass.getName
    partAfter(partAfter(className, '.'), '$').replace("$", "")
  }

  private def partAfter(string: String, char: Char): String =
    string.substring(string.indexOf(char) + 1)
}
