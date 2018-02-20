package models

import java.net.URL

import play.api.data.Form
import play.api.data.Forms._

case class Address(country: String, city: String, street: String, buildingNbr: Int)
case class Order(clientName: String, address: Address)
object Order {
  val form: Form[Order] = Form(
    mapping(
      "clientName" -> text,
      "address" -> mapping(
        "country" -> text,
        "city" -> text,
        "street" -> text,
        "buildingNbr" -> number
      )(Address.apply)(Address.unapply)
    )(Order.apply)(Order.unapply)
  )
}

case class FormWithURL(name: String, url: URL)
object FormWithURL {

  import play.api.data.format.Formatter
  import play.api.data.format.Formats._

  implicit object UrlFormatter extends Formatter[URL] {
    override val format = Some(("format.url", Nil))
    override def bind(key: String, data: Map[String, String]) = parsing(new URL(_), "error.url", Seq.empty)(key, data)
    override def unbind(key: String, value: URL) = Map(key -> value.toString)
  }

  val form = Form(
    mapping(
      "name" -> text,
      "url" ->  of[URL]
    )(FormWithURL.apply)(FormWithURL.unapply)
  )
}