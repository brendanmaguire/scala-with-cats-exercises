package chapter3_functors.printable

trait Printable[A] { self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    (value) => self.format(func(value))
}

final case class Box[A](value: A)

object Box {
  implicit def printableBox[A](
      implicit printableA: Printable[A]
  ): Printable[Box[A]] =
    printableA.contramap { case Box(a) => a }
}

object PrintableApp extends App {
  implicit val pInt = new Printable[Int] {
    def format(value: Int) = value.toString
  }

  implicit val pStr = pInt.contramap(Integer.parseInt)

  implicit val booleanPrintable: Printable[Boolean] =
    (value) => if (value) "yes" else "no"

  def print[A](a: A)(implicit printable: Printable[A]) =
    println(printable.format(a))

  print(89)
  print("111")
  print(Box(true))

  // Fails because "hello world" is not parseable to an int
  print(Box("hello world"))
}
