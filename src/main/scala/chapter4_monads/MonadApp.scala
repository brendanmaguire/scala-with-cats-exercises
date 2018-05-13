package chapter4_monads.monad

import scala.language.higherKinds

trait Monad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a => pure(func(a)))
}

/*
case class Box[A](a: A)

class MyMonad[A] extends Monad[Box[A]] {
  override def pure[A](a: A): Box[A] = Box(a)

  override def flatMap[A, B](value: Box[A])(func: A => Box[B]): Box[B] = ???
}
*/

object MonadApp extends App {

}

