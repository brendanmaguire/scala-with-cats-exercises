# Basic notes on types

## Chapter 2
* Semigroup - def combine (A, A) => A
* Monoid - extends Semigroup { def empty: A }

## Chapter 3
* Functor - Functor[F[A]] { def map (f: A => B) => F[B] }
* contramap - F[A].contramap(B => A): F[B] # Facilitates creating a F[B] if you have a F[A] and a funciton B => A
