package eager

import javax.inject.{Inject, Singleton}

import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.FailoverStrategy
import reactivemongo.api.indexes.{Index, IndexType}
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserCollection @Inject()(reactiveMongoApi: ReactiveMongoApi)(implicit ec: ExecutionContext){
  def collection: Future[JSONCollection] = {
    reactiveMongoApi.database.map(_.collection("user", FailoverStrategy.default))
  }

  def findByUserId() = {
    collection.flatMap(_.indexesManager.ensure(
      Index(
        key = Seq(("_id", IndexType.Ascending)),
        name = Some("findByUserId"),
        unique = false,
        background = true
      )
    ))
  }
  findByUserId()

  def findByUserNamaDepan() = {
    collection.flatMap(_.indexesManager.ensure(
      Index(
        key = Seq(("namaDepan", IndexType.Ascending)),
        name = Some("findByUserNamaDepan"),
        unique = false,
        background = true
      )
    ))
  }
  findByUserNamaBelakang()

  def findByUserNamaBelakang() = {
    collection.flatMap(_.indexesManager.ensure(
      Index(
        key = Seq(("namaDepan", IndexType.Ascending)),
        name = Some("findByUserNamaBelakang"),
        unique = false,
        background = true
      )
    ))
  }
  findByUserNamaBelakang()
}