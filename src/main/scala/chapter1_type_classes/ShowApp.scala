package chapter1.showapp

import cats._
import cats.implicits._
import cats.syntax.eq

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catShow = Show.show[Cat]{ cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  implicit val catEq = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name  === cat2.name &&
    cat1.age   === cat2.age  &&
    cat1.color === cat2.color
  }
}

object ShowApp extends App {
  val cat = Cat("Garfield", 13, "orange")
  println(cat.show)

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")
  println(cat1 === cat2)
  println(cat1 =!= cat2)

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]
  println(optionCat1 === optionCat2)
  println(optionCat1 =!= optionCat2)
}
