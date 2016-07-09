import java.sql.*;
import java.util.Properties;

public class DBDemo
{
  // The JDBC Connector Class.
  //  java -cp mysql-connector-java-5.1.39-bin.jar: DBDemo
  private static final String dbClassName = "com.mysql.jdbc.Driver";

  private static final String CONNECTION =
                          "jdbc:mysql://127.0.0.1/hellowork";

  public static void main(String[] args) throws
                             ClassNotFoundException,SQLException
  {
    System.out.println(dbClassName);
    // Class.forName(xxx) loads the jdbc classes and
    // creates a drivermanager class factory
    Class.forName(dbClassName);

    // Properties for user and password. Here the user and password are both 'paulr'
    Properties p = new Properties();
    p.put("user","root");
    p.put("password","mysql");

    // Now try to connect
    Connection c = DriverManager.getConnection(CONNECTION,p);

    System.out.println("It works !");

    Statement stm = c.createStatement();
    String sql = "select * from jobsearch";
    ResultSet rs = stm.executeQuery(sql);

    while(rs.next()){
	String uuid = rs.getString("uuid");
	String title = rs.getString("title");
	System.out.println("Result -> " + uuid + ":" + title);
    }
    
    c.close();
  }
}
