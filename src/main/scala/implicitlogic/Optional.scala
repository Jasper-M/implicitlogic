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

sealed trait Optional[A] {
  def result: Option[A]

  override def toString = s"Optional($result)"
  override def equals(that: Any) = that match {
    case opt: Optional[_] => result.equals(opt.result)
    case _ => false
  }
  override def hashCode = 13 * 19 + result.hashCode
}

object Optional {
  private def apply[A](ab: Option[A]) = new Optional[A] {
    val result = ab
  }

  implicit def makeOptional[A](implicit ev : (![A] || A)): Optional[A] = {
    val result = ev.result match {
      case Left(_) => None
      case Right(a) => Some(a)
    }
    Optional(result)
  }
}
