package doobie.syntax

import cats.Monad
import doobie.free.connection.ConnectionIO
import doobie.util.transactor.Transactor

class ConnectionIOOps[A](ma: ConnectionIO[A]) {
  def transact[M[_]: Monad](xa: Transactor[M]): M[A] = xa.trans.apply(ma)
}

trait ToConnectionIOOps {
  implicit def toConnectionIOOps[A](ma: ConnectionIO[A]): ConnectionIOOps[A] =
    new ConnectionIOOps(ma)
}

object connectionio extends ToConnectionIOOps
