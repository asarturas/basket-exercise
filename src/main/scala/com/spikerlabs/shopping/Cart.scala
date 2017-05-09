package com.spikerlabs.shopping
trait SomeCart {
  def products: List[Product]
  def total: Double
}
case class Cart(products: List[Product]) extends SomeCart {
  def total: Double = products.foldLeft(0.0)(_ + _.price)
}
object Cart {
  def fromCodes(codes: List[String]): Cart = {
    val products = codes.map(Product.scan).filter(_.nonEmpty).flatten
    Cart(products)
  }
}