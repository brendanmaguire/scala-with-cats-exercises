package chapter4_monads.id

object IdApp extends App {
  type Id[A] = A

  def pure[A](value: A): Id[A] =
    value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] =
    flatMap(initial)(func)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]): Id[B] =
    func(initial)

  println(map(pure(3))(_ + 2))
}
