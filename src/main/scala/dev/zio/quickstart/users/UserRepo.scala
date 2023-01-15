package dev.zio.quickstart.users

import zio.*

trait UserRepo:
  def register(user: User): Task[String]

  def lookup(id: String): Task[Option[StoredUser]]
  
  def users: Task[List[StoredUser]]

object UserRepo:
  def register(user: User): ZIO[UserRepo, Throwable, String] =
    ZIO.serviceWithZIO[UserRepo](_.register(user))

  def lookup(id: String): ZIO[UserRepo, Throwable, Option[StoredUser]] =
    ZIO.serviceWithZIO[UserRepo](_.lookup(id))

  def users: ZIO[UserRepo, Throwable, List[StoredUser]] =
    ZIO.serviceWithZIO[UserRepo](_.users)

