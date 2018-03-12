package repositories.impl


import javax.inject.{Inject, Singleton}

import eager.UserCollection
import models.User
import play.api.libs.json.Json
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.BSONObjectID
import repositories.UserRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepositoryImpl @Inject()
(userCollection: UserCollection) (implicit ec: ExecutionContext) extends UserRepository{

  private val collection = userCollection.collection
  import reactivemongo.play.json._

  override def findAll(): Future[List[User]] = {
    collection.flatMap(_.find(Json.obj())
      .cursor[User](ReadPreference.primary)
      .collect[List](Int.MaxValue, Cursor.ContOnError[List[User]]()))
  }

  override def findById(id: Option[BSONObjectID]) = {
    val s = Json.obj("_id" -> id)
    collection.flatMap(_.find(s).one[User](ReadPreference.primary))
  }

  override def create(user: User) = {
    val id = user._id.orElse(Some(BSONObjectID.generate()))
    val r = user.copy(
      _id = id
    )
    for{
      _ <- collection.flatMap(_.insert[User](r))
      r <- findById(id)
    } yield r
  }

  override def delete(id: Option[BSONObjectID]) = {
    for{
      d <- collection.flatMap(_.remove(Json.obj("_id" -> id)))
    } yield {
      if (d.ok){
        d
      }
      else d
    }
  }

  override def update(user: User) = {
    val s = Json.obj("_id" -> user._id)
    val usr = user
    for{
      _ <- collection
        .flatMap(_.update(s, usr))
      r <- findById(usr._id)
    } yield r
  }

  override def findByNamaDepan(namaDepan: Option[String]): Future[Option[User]] = {
    val s = Json.obj("namaDepan" -> namaDepan)
    collection.flatMap(_.find(s).one[User](ReadPreference.primary))
  }

  override def findByNamaBelakang(namaBelakang: Option[String]): Future[Option[User]] = {
    val s = Json.obj("namaBelakang" -> namaBelakang)
    collection.flatMap(_.find(s).one[User](ReadPreference.primary))
  }

  override def findByTempatLahir(tempatLahir: Option[String]): Future[Option[User]] = {
    val s = Json.obj("tempatLahir" -> tempatLahir)
    collection.flatMap(_.find(s).one[User](ReadPreference.primary))
  }
}