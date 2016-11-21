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

object typehelpers {
  type Union[X,A,B] = (X <:< A) Or (X <:< B)
  type U[A,B] = { type lambda[X] = Union[X,A,B] }
  type <:!<[A,B] = Not[A <:< B]
  type NotSubtypeOf[A] = { type lambda[X] = X <:!< A }
  type NotSupertypeOf[A] = { type lambda[X] = A <:!< X }
  type =:!=[A,B] = Not[A =:= B]
  type NotType[A] = { type lambda[X] = X =:!= A }
}
