package Project0

import java.sql.{Connection, DriverManager}


object Inventory {

  def main(args: Array[String]) : Unit = {
    DBConnect.connect()
    startMenu()
  }

  def startMenu() : Unit = {
    var option = 0
    do{
      println("Welcome to Inventory Checker!!!")
      println("Which store would you like to check inventory from?")
      println("(1) Walmart")
      println("(2) Target")
      println("(3) Exit")
      option = scala.io.StdIn.readInt()
      option match {
        case 1 => walmart()
        case 2 => target()
        case 3 => exitApp()
        case _ => println("Invalid! Please try again")
      }
    }while(option != 3)
  }

  def walmart() : Unit = {
    println("You chose Walmartzzzz")
  }

  def target() : Unit = {
    println("You chose Target")
  }

  def exitApp() : Unit = {
    println("Thank you. Good Bye")
  }
}

