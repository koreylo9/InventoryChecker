package Project0

import java.sql.{Connection, DriverManager}


object Inventory {

  def main(args: Array[String]) : Unit = {
   DBConnect.connect()
  }
}

