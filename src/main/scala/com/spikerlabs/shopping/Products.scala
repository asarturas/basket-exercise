package com.spikerlabs.shopping
sealed trait Product {
  def code: String
  def price: Double
}
final case class Apple() extends Product {
  val code = "Apple"
  val price = 0.60
}
final case class Orange() extends Product {
  val code = "Orange"
  val price = 0.25
}

object Product {
  def scan(code: String): Option[Product] = code match {
    case "Apple" => Some(Apple())
    case "Orange" => Some(Orange())
  }
}