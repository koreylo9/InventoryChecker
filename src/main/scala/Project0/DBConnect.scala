package Project0

import java.sql.{Connection, DriverManager}

object DBConnect {

  private var connection:Connection = _

  def connect() : Unit = {
    val url = "jdbc:mysql://localhost:3306/demodatabase"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "Korgalnol9!"
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement
      val rs = statement.executeQuery("SELECT * FROM people")
      while (rs.next) {
        val id = rs.getString("id")
        val firstname = rs.getString("firstname")
        println("host = %s, user = %s".format(id,firstname))
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def closeConnection() : Unit = {
    connection.close()
  }

}
