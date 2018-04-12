package chapter1.printablelibraryapp

trait Printable[A] {
  def format(value: A): String
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String =
    printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit =
    println(format(value))
}

object PrintableInstances {
  implicit val printableString: Printable[String] = value => value
  implicit val printableInt: Printable[Int] = value => value.toString
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String =
      Printable.format(value)

    def print(implicit printable: Printable[A]): Unit =
      Printable.print(value)
  }
}

final case class Cat(name: String, age: Int, color: String)

object Cat {
  import PrintableInstances._

  implicit val printableCat: Printable[Cat] = { cat =>
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }
}

object PrintableLibraryApp extends App {
  import Printable._
  import PrintableSyntax._

  val cat = Cat("Garfield", 13, "orange")
  print(cat)
  cat.print
}
