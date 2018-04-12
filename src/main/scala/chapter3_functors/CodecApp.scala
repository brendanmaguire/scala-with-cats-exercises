package chapter3_functors.codec

trait Codec[A] { self =>
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    def encode(value: B): String = self.encode(enc(value))
    def decode(value: String): B = dec(self.decode(value))
  }
}

object CodecHelpers {
  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)
}

final case class Box[A](value: A)

object Box {
  implicit def boxCodec[A](implicit codecA: Codec[A]) = new Codec[Box[A]] {
    def encode(box: Box[A]): String = codecA.encode(box.value)
    def decode(value: String): Box[A] = Box(codecA.decode(value))
  }
}

object codecs {
  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  implicit val doubleCodec: Codec[Double] =
    stringCodec.imap(_.toDouble, _.toString)

  implicit val intCodec: Codec[Int] =
    doubleCodec.imap(_.toInt, _.toDouble)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)
}

object CodecApp extends App {
  import CodecHelpers._
  import codecs._

  println(encode(decode[Int]("88.889")))
  println(encode(decode[String]("hello world")))
  println(encode(decode[Boolean]("true")))
  println(encode(Box(123.4)))
  println(decode[Box[Double]]("123.4"))
}
