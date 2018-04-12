package chapter2_monoids_and_semigroups

import cats.Monoid
import cats.instances.int._
import cats.instances.option._

object SuperAdderApp extends App{
  def add[A](items: List[A])(implicit m: Monoid[A]): A =
    m.combineAll(items)

  println(add(List(Some(3), Some(4), None)))

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid = new Monoid[Order] {
    def empty = Order(0, 0)
    def combine(x: Order, y: Order): Order =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )
  }

  println(add(List(Order(17, 3), Order(18, 4))))
}
