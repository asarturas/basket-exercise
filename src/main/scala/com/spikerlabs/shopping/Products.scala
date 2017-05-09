package com.spikerlabs.shopping
sealed trait Product {
  def code: String
  def price: Int
}
final case class Apple() extends Product {
  val code = "Apple"
  val price = 60
}
final case class Orange() extends Product {
  val code = "Orange"
  val price = 25
}

object Product {
  def scan(code: String): Option[Product] = code match {
    case "Apple" => Some(Apple())
    case "Orange" => Some(Orange())
  }
}