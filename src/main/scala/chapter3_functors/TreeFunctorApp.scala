package chapter3_functors

import cats.Functor
import cats.implicits._

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  // Need this helper methods to indicate to the compiler that the type is Tree[A]
  // and therefore the functor can be used on them
  def branch[A](l: Tree[A], r: Tree[A]): Tree[A] = Branch(l, r)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}

object TreeFunctorApp extends App {
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
      tree match {
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
        case Leaf(value) => Leaf(f(value))
      }
  }

  import Tree._
  val tree = branch(leaf(3), branch(leaf(4), leaf(5)))
  val mappedTree = tree.map(_ + 17)
  println(mappedTree)
}
