package dev.zio.quickstart.counter

import zhttp.http.*
import zio.{Ref, ZIO}

import scala.collection.mutable

/**
 * An http app that:  
 *  - Accepts `Request` and returns a `Response`
 *  - Does not fail
 *  - Requires the `Ref[Int]` as the environment
 */
object CounterApp2:
  def apply(): Http[Counter, Nothing, Request, Response] =
    Http.fromZIO(ZIO.service[Counter]).flatMap { counter =>
      Http.collectZIO[Request] {
        case Method.GET -> !! / "up2" =>
          ZIO.succeed(Response.text(counter.inc("2").get("2").toString))
        case Method.GET -> !! / "down2" =>
          ZIO.succeed(Response.text(counter.dec("2").get("2").toString))
        case Method.GET -> !! / "get2" =>
          ZIO.succeed(Response.text(counter.get("2").toString))
      }
    }
