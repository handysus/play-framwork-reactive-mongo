package controllers

import javax.inject._

import models.User
import play.api.libs.json.Json
import services.UserService
import play.api.mvc._
import play.api.libs.json.Json._
import reactivemongo.bson.BSONObjectID

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()
(cc: ControllerComponents, userService: UserService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def findAllUser() = Action.async { implicit request =>
    userService
      .findAll()
      .map(toJson(_))
      .map(Ok(_))
  }

  def findById(id: BSONObjectID) = Action.async { implicit request =>
    userService
      .findById(Some(id))
      .map(toJson(_))
      .map(Ok(_))
  }

  def findByNamaDepan(namaDepan: String) = Action.async { implicit request =>
    userService
      .findByNamaDepan(Some(namaDepan))
      .map(toJson(_))
      .map(Ok(_))
  }

  def findByNamaBelakang(namaBelakang: String) = Action.async { implicit request =>
    userService
      .findByNamaBelakang(Some(namaBelakang))
      .map(toJson(_))
      .map(Ok(_))
  }

  def findByTempatLahir(tempatLahir: String) = Action.async { implicit request =>
    userService
      .findByTempatLahir(Some(tempatLahir))
      .map(toJson(_))
      .map(Ok(_))
  }

  def createUser() = Action.async(parse.json) { implicit request =>
    request.body.validate[User].fold(
      _ => Future.successful(BadRequest(Json.obj("status" -> "Invalid"))),
      user => {
        userService
          .create(user)
          .map(toJson(_))
          .map(Created(_))
      }
    )
  }

  def deleteUser(id: BSONObjectID) = Action.async { implicit request =>
    userService
      .delete(Some(id))
      .map(_ => Ok)
  }

  def updateUser() = Action.async(parse.json) { implicit request =>
    request.body.validate[User].fold(
      _ => Future.successful(BadRequest(Json.obj("status" -> "Invalid"))),
      user => {
        userService
          .update(user)
          .map(toJson(_))
          .map(Ok(_))
      }
    )
  }
}