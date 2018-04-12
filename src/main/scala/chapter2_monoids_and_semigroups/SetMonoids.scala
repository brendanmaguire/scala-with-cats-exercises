package chapter2.set_monoids

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
                       (implicit s: Semigroup[A]): Boolean = {
    s.combine(x, s.combine(y, z)) ==
      s.combine(s.combine(x, y), z)
  }

  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }

  def checkLaws[A](x: A, y: A, z: A)(implicit monoid: Monoid[A]) =
    assert(associativeLaw(x, y, z) && identityLaw(x))
}

object SetMonoids {
  implicit def unionMonoid[A] = new Monoid[Set[A]] {
    def empty: Set[A] = Set.empty[A]
    def combine(x: Set[A], y: Set[A]) = x union y
  }

  implicit def insersectSemigroup[A] = new Semigroup[Set[A]] {
    def combine(x: Set[A], y: Set[A]) = x intersect y
  }
}

object SetMonoidsApp extends App {
  val x = Set(1)
  val y = Set(2)
  val z = Set[Int]()
  MonoidLaws.checkLaws(x, y, z)(SetMonoids.unionMonoid)

  assert(MonoidLaws.associativeLaw(x, y, z)(SetMonoids.insersectSemigroup))
}
