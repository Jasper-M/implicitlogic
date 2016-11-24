# implicitlogic

This is a simple toy project for playing around with some implicit resolution tricks.

### Getting started

This project is not yet published anywhere. Run the following commands to install it locally:

```
git clone https://github.com/Jasper-M/implicitlogic.git
cd implicitlogic
sbt "+ publishLocal"
```

Then add the following to your own build.sbt:

```scala
libraryDependencies += "com.jasperm" %% "implicitlogic" % "0.1-SNAPSHOT"
```

### Examples

```scala
scala> import implicitlogic._
import implicitlogic._

scala> def stringIntSymbol[A](a: A)(implicit ev: (A <:< String) Or (A <:< Int) Or (A <:< Symbol)): A = a
stringIntSymbol: [A](a: A)(implicit ev: implicitlogic.Or[implicitlogic.Or[<:<[A,String],<:<[A,Int]],<:<[A,Symbol]])A

scala> stringIntSymbol(42)
res8: Int = 42

scala> stringIntSymbol("foo")
res9: String = foo

scala> stringIntSymbol('bar)
res10: Symbol = 'bar

scala> stringIntSymbol(42.5)
<console>:16: error: could not find implicit value for parameter ev: implicitlogic.Or[implicitlogic.Or[<:<[Double,String],<:<[Double,Int]],<:<[Double,Symbol]]
       stringIntSymbol(42.5)
                      ^

scala> def noAnyTypes[A](x: A, y: A)(implicit ev: Not[(AnyVal <:< A) Or (AnyRef <:< A)]) = List(x,y)
noAnyTypes: [A](x: A, y: A)(implicit ev: implicitlogic.Not[implicitlogic.Or[<:<[AnyVal,A],<:<[AnyRef,A]]])List[A]

scala> noAnyTypes(3, "foo")
<console>:16: error: ambiguous implicit values:
 both method makeNot in object Not of type [A]=> implicitlogic.Not[A]
 and method makeNotAmbig in object Not of type [A](implicit a: A)implicitlogic.Not[A]
 match expected type implicitlogic.Not[implicitlogic.Or[<:<[AnyVal,Any],<:<[AnyRef,Any]]]
       noAnyTypes(3, "foo")
                 ^

scala> noAnyTypes(BigInt(4), 7)
<console>:16: error: ambiguous implicit values:
 both method makeNot in object Not of type [A]=> implicitlogic.Not[A]
 and method makeNotAmbig in object Not of type [A](implicit a: A)implicitlogic.Not[A]
 match expected type implicitlogic.Not[implicitlogic.Or[<:<[AnyVal,Any],<:<[AnyRef,Any]]]
       noAnyTypes(BigInt(4), 7)
                 ^

scala> noAnyTypes(Some(5), None)
res23: List[Option[Int]] = List(Some(5), None)

scala> def noIntegralFractions[A](a: A)(implicit ev: Not[Integral[A] And Fractional[A]]): A = a
noIntegralFractions: [A](a: A)(implicit ev: implicitlogic.Not[implicitlogic.And[Integral[A],Fractional[A]]])A
```
