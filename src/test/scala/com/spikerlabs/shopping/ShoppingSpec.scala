package com.spikerlabs.shopping

import org.scalatest.{FlatSpec, Matchers}

class ShoppingSpec extends FlatSpec with Matchers {
  it should "create a cart with products from list of codes" in {
    Cart.fromCodes(List("Apple", "Apple", "Orange", "Apple")) should
      be(Cart(List(Apple(), Apple(), Orange(), Apple())))
  }
  "cart" should "return a price of a single product" in {
    Cart(List(Apple())).total should be(0.60)
  }
  it should "return a price of all products" in {
    Cart(List(Apple(), Apple(), Orange(), Apple())).total should be(2.05)
  }
}
