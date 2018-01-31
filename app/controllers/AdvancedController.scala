package controllers

import javax.inject.Inject

import models.{BasicForm, FormWithURL, Order}
import play.api.mvc.{AbstractController, ControllerComponents}

class AdvancedController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def nestedForm() = Action { implicit request =>
    Ok(views.html.nestedForm(Order.form))
  }

  def nestedFormPost() = Action { implicit request =>
    Order.form.bindFromRequest().fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.nestedForm(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      })
  }

  def customBindForm() = Action { implicit request =>
    Ok(views.html.customBindForm(FormWithURL.form))
  }

  def customBindFormPost() = Action { implicit request =>
    FormWithURL.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        println(s"ERRORS: $formWithErrors")
        BadRequest(views.html.customBindForm(formWithErrors))
      },
      formData => {
        println(s"OK DATA: $formData")
        Ok(formData.toString)
      }
    )
  }

  def styledForm() = Action { implicit request =>
   Ok(views.html.styledForm(BasicForm.form))
  }

  def styledFormPost() = Action { implicit request =>
    BasicForm.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.styledForm(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      }
    )
  }

}
