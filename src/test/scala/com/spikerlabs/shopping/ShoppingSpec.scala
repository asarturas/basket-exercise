package com.spikerlabs.shopping

import org.scalatest.{FlatSpec, Matchers}

class ShoppingSpec extends FlatSpec with Matchers {
  it should "create a cart with products from list of codes" in {
    Cart.fromCodes(List("Apple", "Apple", "Orange", "Apple")) should
      be(Cart(List(Apple(), Apple(), Orange(), Apple())))
  }
  "cart" should "return a price of a single product" in {
    Cart(List(Apple())).total should be(60)
  }
  it should "return a price of all products" in {
    Cart(List(Apple(), Apple(), Orange(), Apple())).total should be(205)
  }
  it should "apply an offer" in {
    import Offers._
    val cart = Cart(List(Apple())).applyOffer { (products: List[Product]) =>
      products.map(_.price / 2).sum
    }
    cart.total should be(30)
  }
  "buy one get one free on apples" should "equal to price of sum prices of every second apple" in {
    Offers.buyOneGetOneFreeOnApples(List(Apple(), Orange(), Apple())) should be(60)
  }
  "three for two on oranges" should "equal to price of every third orange" in {
    Offers.threeForTwoOnOranges(List(Apple(), Orange(), Orange(), Apple(), Orange())) should be(25)
  }
  "cart" should "apply multiple offers" in {
    import Offers._
    val cart = Cart(List(Apple(), Orange(), Orange(), Apple(), Orange(), Orange(), Apple()))
    cart.applyOffer(buyOneGetOneFreeOnApples).applyOffer(threeForTwoOnOranges).total should be(195)
  }
  it should "return formatted price too" in {
    Cart(List(Apple())).formattedTotal should be("£0.60")
  }
}
