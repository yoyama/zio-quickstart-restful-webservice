package dev.zio.quickstart.users

import java.util.UUID
import zio.json.*

case class User(name: String, age: Int)
case class StoredUser(id: String, name: String, age: Int)

object User:
  given JsonEncoder[User] =
    DeriveJsonEncoder.gen[User]
  given JsonDecoder[User] =
    DeriveJsonDecoder.gen[User]

object StoredUser:
  given JsonEncoder[StoredUser] =
    DeriveJsonEncoder.gen[StoredUser]
  given JsonDecoder[StoredUser] =
    DeriveJsonDecoder.gen[StoredUser]
