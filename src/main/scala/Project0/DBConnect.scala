package Project0

import java.sql.{Connection, DriverManager}

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

  def selectQuery(str: String) : Unit = {
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT product_id, name FROM target_products where name = '" + str + "'")
    while ( resultSet.next() ) {
      val product_id = resultSet.getString("product_id")
      val name = resultSet.getString("name")
      println("SKU, name = " + product_id + ", " + name)
    }
  }

}
