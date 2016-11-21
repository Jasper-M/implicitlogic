/*
 * Copyright 2016 Jasper Moeys
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package implicitlogic

/*
 * Equivalent but without "result" value: type Or[A,B] = Not[Not[A] And Not[B]]
 */
case class Or[A, B](result: Either[A,B])

protected trait LowerPriorityOr {
  implicit def makeOrRight[A,B](implicit b: B): Or[A,B] = Or(Right(b))
}

object Or extends LowerPriorityOr {
  implicit def makeOrLeft[A,B](implicit a: A): Or[A,B] = Or(Left(a))
}
