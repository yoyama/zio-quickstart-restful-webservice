package dev.zio.quickstart.counter

import dev.zio.quickstart.users.InmemoryUserRepo
import zio.{Ref, ZIO, ZLayer}

import scala.collection.mutable

class Counter(counter:mutable.Map[String, Int]) {
  def get(key:String):Int = counter.get(key).getOrElse(0)

  def set(key:String, c:Int):Counter = {
    counter += ((key, c))
    this
  }

  def inc(key:String):Counter = {
    val cur = get(key)
    set(key, cur+1)
  }

  def dec(key: String): Counter = {
    val cur = get(key)
    val v = if (cur == 0) 0 else cur -1
    set(key, v)
  }

}

object Counter {
  def layer: ZLayer[Any, Nothing, Counter] = {
    ZLayer.succeed(new Counter(mutable.Map.empty[String, Int]))
  }
}
