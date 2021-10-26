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

  def walmart() : Unit = {
    var check = false
    val store = "walmart"
    do{
      println("Walmart Inventory Checker")
      println("Walmart uses a SKU (Stock Keeping Unit) number to store all of their items.  " +
        "If you know the SKU you can search by that or utilize our SKU finder.")
      println("(1) Enter SKU ")
      println("(2) Use SKU finder")
      println("(3) Return to Main Menu")
      println("(4) Exit")
      val option = scala.io.StdIn.readInt()
      option match {
        case 1 => SearchProduct(store)
        case 2 => skuDpciFinder(store)
        case 3 => startMenu()
                  check = true
        case 4 => exitApp()
                  check = true
        case _ => walmart()
      }
    }while(!check)

  }

  def skuDpciFinder(store: String) : Unit = {
    print("What product are you looking for: ")
    val option = scala.io.StdIn.readLine()
    DBConnect.listSKU(option,store)
  }

  def SearchProduct(str : String) : Unit = {
    if(str == "walmart"){
      print("Enter SKU for product: ")
    }
    else if(str == "target"){
      print("Enter DPCI for product: ")
    }
    val option = scala.io.StdIn.readInt()
    DBConnect.showProducts(option,str)
  }

  def target() : Unit = {
    var check = false
    val store = "target"
    do{
      println("Target Inventory Checker")
      println("Target uses a DPCI (Department Class Item) number to store all of their items.  " +
        "If you know the DPCI you can search by that or utilize our DPCI finder.")
      println("(1) Enter DPCI ")
      println("(2) Use DPCI finder")
      println("(3) Return to Main Menu")
      println("(4) Exit")
      val option = scala.io.StdIn.readInt()
      option match {
        case 1 => SearchProduct(store)
        case 2 => skuDpciFinder(store)
        case 3 => startMenu()
                  check = true
        case 4 => exitApp()
                  check =  true
        case _ => target()
      }
    }while(!check)


  }

  def exitApp() : Unit = {
    DBConnect.closeConnect()
    println("Exiting...")
  }
}

