package controllers

import javax.inject.Inject
import javax.inject.Singleton

import models.{BasicForm, ConstrainedForm, InputTypesForm}
import play.api.libs.Files
import play.api.mvc._

@Singleton
class BasicController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def simpleForm() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.basicForm(BasicForm.form))
  }

  def simpleFormPost() = Action { implicit request =>
    BasicForm.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.basicForm(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      }
    )
  }

  def filledForm() = Action { implicit request =>
    Ok(views.html.basicForm(BasicForm.form.fill(BasicForm("dummy value", 99))))
  }

  def complexForm() = Action { implicit request =>
    Ok(views.html.complexForm(ConstrainedForm.form))
  }

  def complexFormPost() = Action { implicit request =>
    ConstrainedForm.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.complexForm(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      }
    )
  }

  def inputTypesForm() = Action { implicit request =>
    Ok(views.html.inputsTypesForm(InputTypesForm.form))
  }

  def inputTypesFormPost() = Action { implicit request =>
    InputTypesForm.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.inputsTypesForm(formWithErrors))
      },
      formData => {
        Ok(formData.toString)
      }
    )
  }

  def fileForm() = Action { implicit request =>
    Ok(views.html.fileForm())
  }

  def fileFormPost() = Action(parse.multipartFormData) { implicit request =>
    val fileOpt = request.body.file("file")
    fileOpt.fold {
      BadRequest("File not sent")
    } { file =>
      val x: Files.TemporaryFile = file.ref
      Ok(s"File received: ${file.ref}")
    }
  }
}
