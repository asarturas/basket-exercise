package com.spikerlabs.shopping

case class Cart(products: List[Product]) {
  def total: Double = products.foldLeft(0.00)(_ + _.price)
}

object Cart {
  def fromCodes(codes: List[String]): Cart = {
    val products = codes.map(Product.scan).filter(_.nonEmpty).flatten
    Cart(products)
  }
}