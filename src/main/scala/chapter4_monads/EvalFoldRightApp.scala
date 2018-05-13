package chapter4_monads.eval

import cats.Eval

object EvalFoldRightApp extends App {

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(foldRight(tail, acc)(fn).map(fn(head, _)))
      case Nil =>
        Eval.later(acc)
    }

  println(foldRight(List.fill(1000000)(3), 0)(_ + _).value)
}
