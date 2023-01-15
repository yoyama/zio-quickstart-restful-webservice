package dev.zio.quickstart.users
import zio.*

import scala.collection.mutable

  case class InmemoryUserRepo(map: Ref[mutable.Map[String, StoredUser]]) extends UserRepo:
    def register(user: User): UIO[String] =
      for
        id <- Random.nextUUID.map(_.toString)
        _ <- map.updateAndGet(_ addOne(id, StoredUser(id, user.name, user.age)))
      yield id

    def lookup(id: String): UIO[Option[StoredUser]] =
      map.get.map(_.get(id))
     
    def users: UIO[List[StoredUser]] =
       map.get.map(_.values.toList) 

  object InmemoryUserRepo {
    def layer: ZLayer[Any, Nothing, InmemoryUserRepo] =
      ZLayer.fromZIO(
        Ref.make(mutable.Map.empty[String, StoredUser]).map(new InmemoryUserRepo(_))
      )
  }