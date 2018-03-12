package services.impl

import javax.inject.{Inject, Singleton}

import models.User
import reactivemongo.bson.BSONObjectID
import services.UserService
import repositories.UserRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserServiceImpl @Inject() (userRepository: UserRepository) (implicit ex: ExecutionContext) extends UserService{
  override def findAll(): Future[List[User]] = {
    for {
      u <- userRepository.findAll()
    } yield u
  }

  override def create(user: User) = {
    userRepository.create(user)
  }

  override def delete(id: Option[BSONObjectID]) = {
    userRepository.delete(id)
  }

  override def update(user: User) = {
    userRepository.update(user)
  }

  override def findById(userID: Option[BSONObjectID]): Future[Option[User]] = {
    userRepository.findById(userID)
  }

  override def findByNamaDepan(namaDepan: Option[String]): Future[Option[User]] = {
    userRepository.findByNamaDepan(namaDepan)
  }

  override def findByNamaBelakang(namaBelakang: Option[String]): Future[Option[User]] = {
    userRepository.findByNamaBelakang(namaBelakang)
  }

  override def findByTempatLahir(tempatLahir: Option[String]): Future[Option[User]] = {
    userRepository.findByTempatLahir(tempatLahir)
  }
}