package Project0

import java.sql.{Connection, DriverManager}


object Inventory {

  def main(args: Array[String]) : Unit = {
    val connect = databaseConnect()
    if(connect == 1){
      println("Successful Connect")
    }
    else{
      println("Unsuccessful")
    }
  }

  def databaseConnect() : Int = {
    // connect to the database named "mysql" on port 8889 of localhost
    val url = "jdbc:mysql://localhost:3306/demodatabase"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "Korgalnol9!"
    var connection:Connection = null
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

      return 1;
    } catch {
      case e: Exception => e.printStackTrace
        return 0;
    }
    //    connection.close
  }
}

