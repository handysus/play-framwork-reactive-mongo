package services

import models.User
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONObjectID

import scala.concurrent.Future

trait UserService{
  def findAll(): Future[List[User]]

  def findById(id: Option[BSONObjectID]): Future[Option[User]]

  def create(user: User): Future[Option[User]]

  def delete(id: Option[BSONObjectID]): Future[WriteResult]

  def update(user: User): Future[Option[User]]
}