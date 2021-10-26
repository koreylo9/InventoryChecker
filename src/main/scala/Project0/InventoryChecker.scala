package Project0

import java.sql.{Connection, DriverManager}
import scala.annotation.tailrec


object InventoryChecker {

  def main(args: Array[String]) : Unit = {
    DBConnect.connect()
    startMenu()
  }

  @tailrec
  def startMenu() : Unit = {
    println("Welcome to Inventory Checker!!!")
    println("Which store would you like to check inventory from?")
    println("(1) Walmart")
    println("(2) Target")
    println("(3) Exit")
    val option = scala.io.StdIn.readInt()
    option match {
      case 1 => walmart()
      case 2 => target()
      case 3 => exitApp()
      case _ => startMenu()
    }
  }

  @tailrec
  def target() : Unit = {
    println("Target Inventory Checker")
    println("Target uses an SKU (Stock Keeping Unit) number to store all of their items.  " +
      "If you know the SKU you can search by that or utilize our SKU finder.")
    println("(1) Enter SKU ")
    println("(2) Use SKU finder")
    println("(3) Return to Main Menu")
    println("(4) Exit")
    val option = scala.io.StdIn.readInt()
    option match {
      case 1 => searchProduct()
      case 2 => skuFinder()
      case 3 => startMenu()
      case 4 => exitApp()
      case _ => target()
    }
  }

  def skuFinder() : Unit = {
    print("What product are you looking for: ")
    val option = scala.io.StdIn.readLine()
    DBConnect.listSKU(option)
  }

  def searchProduct() : Unit = {
    print("Enter SKU for product: ")
    val option = scala.io.StdIn.readInt()
    DBConnect.showProducts(option)
  }

  def walmart() : Unit = {
    println("Walmart Inventory Checker")
  }

  def exitApp() : Unit = {
    DBConnect.closeConnect()
    println("Exiting...")
  }
}

