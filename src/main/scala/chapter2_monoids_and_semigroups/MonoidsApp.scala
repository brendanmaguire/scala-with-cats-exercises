package chapter2.boolean_monoids

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

object MonoidLaws {
  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }

  def checkLaws[A](x: A, y: A, z: A)(implicit monoid: Monoid[A]) =
    assert(associativeLaw(x, y, z) && identityLaw(x))
}


object BooleanMonoids {
  implicit val booleanAndMonoid = new Monoid[Boolean] {
    def empty: Boolean = true
    def combine(x: Boolean, y: Boolean) = x && y
  }

  implicit val booleanOrMonoid = new Monoid[Boolean] {
    def empty: Boolean = false
    def combine(x: Boolean, y: Boolean) = x || y
  }
}

object BooleanMonoidsApp extends App {
  val x = true
  val y = false
  val z = true

  MonoidLaws.checkLaws(x, y, z)(BooleanMonoids.booleanAndMonoid)
  MonoidLaws.checkLaws(x, y, z)(BooleanMonoids.booleanOrMonoid)
}
