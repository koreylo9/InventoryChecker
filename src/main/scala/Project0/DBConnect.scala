package Project0

import java.sql.{Connection, DriverManager, SQLException}

object DBConnect {

  private var connection:Connection = _

  def connect() : Unit = {
    val url = "jdbc:mysql://localhost:3306/inventorychecker"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "Korgalnol9!"
//    val username = System.getenv("JDBC_ROLE")
//    val password = System.getenv("JDBC_Password")
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
//      val statement = connection.createStatement
//      val rs = statement.executeQuery("SELECT * FROM people")
//      while (rs.next) {
//        val id = rs.getString("id")
//        val firstname = rs.getString("firstname")
//        println("host = %s, user = %s".format(id,firstname))
//      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def closeConnect() : Unit = {
    connection.close()
  }

  def showProducts(id: Int, store: String) : Unit = {
    try {
      val statement = connection.createStatement()
//      val resultSet1 = statement.executeQuery("SELECT name FROM target_products WHERE product_id = '" + sku + "'")
      val resultSet = statement.executeQuery("SELECT st.store_name, st.address, dpci.quantity, tp.* " +
        "FROM stores AS st " +
        "INNER JOIN " +
        "dpci_stock as dpci " +
        "ON dpci.store_id = st.store_id " +
        "INNER JOIN " +
        store + "_products as tp " +
        "ON tp.product_id = dpci.product_id " +
        "WHERE tp.product_id = '" + id + "'")
//      while(resultSet1.next()){
//        val product = resultSet1.getString("name")
//        println(product)
//      }
      while ( resultSet.next() ) {
        if(resultSet.isFirst()){
          val product_name = resultSet.getString("tp.name")
          val desc = resultSet.getString("tp.description")
          println("Product: " + product_name)
          println("Description: " + desc)
          println()
          println("Store            Availability            Price")
        }
        val store_name = resultSet.getString("st.store_name")
        val address = resultSet.getString("st.address")
        val quantity = resultSet.getString("dpci.quantity")
        val price = resultSet.getString("tp.price")

        println(store_name + "            " + quantity + "            " + price)
        println(address)
        println()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def listSKU(str: String, store: String) : Unit = {
    try{

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT product_id, name FROM " + store + "_products where REGEXP_LIKE(name, '" + str + "', 'i')")
      if(!resultSet.next()) {
        println("No Results Were Found")
        println()
        return
      }

      while ( resultSet.next() ) {
        val product_id = resultSet.getString("product_id")
        val name = resultSet.getString("name")
        println(name)
        if(store == "walmart") {
          println("SKU: " + product_id)
        } else if(store == "target")
          println("DPCI: " + product_id)
        println()
      }
    } catch {
      case e: Exception => e.printStackTrace()
        case sql: SQLException => sql.printStackTrace()
    }

  }

}
