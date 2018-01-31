package models

import java.io.File

import play.api.data.Form
import play.api.data.Forms._

case class BasicForm(name: String, age: Int)

object BasicForm {
  val form: Form[BasicForm] = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(BasicForm.apply)(BasicForm.unapply)
  )
}

case class ConstrainedForm(name: String, nickname: String, age: Int, email: String, date: java.util.Date, opt: Option[String], employed: Boolean, lst: List[String])

object ConstrainedForm {
  val form: Form[ConstrainedForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "nickname" -> text(minLength = 4, maxLength = 6),
      "age" -> number(min = 18, max = 35),
      "email" -> email,
      "date" -> date,
      "opt" -> optional(text),
      "employed" -> boolean,
      "lst" -> list(text)
    )(ConstrainedForm.apply)(ConstrainedForm.unapply)
  )
}

case class InputTypesForm(name: String, password: String, description: String, rich: Boolean, date: java.util.Date, favColor: String, leastFavColor: String)

object InputTypesForm {

  object Preferences extends Enumeration {
    val Red, Green, Blue = Value
  }

  val form: Form[InputTypesForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "password" -> nonEmptyText(minLength = 6),
      "description" -> nonEmptyText,
      "rich" -> boolean,
      "date" -> date,
      "favColor" -> nonEmptyText,
      "leastFavColor" -> nonEmptyText,
    )(InputTypesForm.apply)(InputTypesForm.unapply)
  )
}
