package models

import play.api.libs.json.Json
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._

case class User(_id: Option[BSONObjectID] = None,
                namaDepan: String,
                namaBelakang: String,
                tempatLahir: String,
                hasPremiumAccess: Boolean)

object User {
  implicit val userFormat = Json.format[User]
}
