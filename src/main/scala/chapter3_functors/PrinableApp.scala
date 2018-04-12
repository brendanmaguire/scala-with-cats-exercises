package chapter3_functors

trait Printable[A] { self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    (value) => self.format(func(value))
}

object PrintableApp extends App {
  implicit val pInt = new Printable[Int] {
    def format(value: Int) = value.toString
  }

  implicit val pStr = pInt.contramap(Integer.parseInt)

  def print[A](a: A)(implicit printable: Printable[A]) =
    println(printable.format(a))

  print(89)
  print("111")
}
