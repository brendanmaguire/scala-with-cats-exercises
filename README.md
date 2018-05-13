# Basic notes on types

## Chapter 2
* Semigroup - def combine (A, A) => A
* Monoid - extends Semigroup { def empty: A }

## Chapter 3
* Functor - Functor[F[A]] { def map (f: A => B) => F[B] }
* Contravariant - Contravariant[F[A]].contramap(B => A): F[B] # Facilitates creating a F[B] if you have a F[A] and a funciton B => A
* Invariant - Invariant[F[A]].imap(A => B)(B => A): F[B] # Example, Codec.{encode, decode, imap} - Bidirectional transformations

## Chapter 4
* Monad - Every Monad is also a Functor
```
trait Monad[F[_]] {
  def pure[A](value: A): F[A]
  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]
} 
```

* Id - Wraps a Monad around regular objects / primitives

* Eval - A monad for abstracting over different models of evaluation; eager, lazy and memoized
