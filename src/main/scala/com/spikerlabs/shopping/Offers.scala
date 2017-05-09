package com.spikerlabs.shopping

import com.spikerlabs.shopping.Offers.Offer

case class CartWithOffer(cart: SomeCart, offer: Offer) extends SomeCart {
  def products: List[Product] = cart.products
  val total: Double = cart.total - offer(cart.products)
}

object Offers {
  type Offer = (List[Product]) => Double
  implicit class ApplyOfferToCart(cart: SomeCart) {
    def applyOffer(offer: Offer): SomeCart = CartWithOffer(cart, offer)
  }
  private def nth(n: Int, products: List[Product]) = products.drop(n - 1).grouped(n).map(_.head)
  def buyOneGetOneFreeOnApples(products: List[Product]): Double = {
    val apples = products.flatMap {
      case apple: Apple => Some(apple)
      case _ => None
    }
    nth(2, apples).map(_.price).sum
  }
  def threeForTwoOnOranges(products: List[Product]): Double = {
    val oranges = products.flatMap {
      case orange: Orange => Some(orange)
      case _ => None
    }
    nth(3, oranges).map(_.price).sum
  }
}