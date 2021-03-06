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

sealed trait And[A,B] {
  def result: (A,B)
  
  override def toString = s"And($result)"
  override def equals(that: Any) = that match {
    case and: And[_,_] => result.equals(and.result)
    case _ => false
  }
  override def hashCode = 13 * 7 + result.hashCode
}

object And {
  private def apply[A,B](ab: (A,B)) = new And[A,B] {
    val result = ab
  }
  
  implicit def makeAnd[A,B](implicit a: A, b: B): And[A,B] = And((a,b))
}
