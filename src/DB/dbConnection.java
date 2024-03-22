package DB;
import java.sql.*;

public class dbConnection {


    public Connection connectDB()
    {
        try {
            
            System.out.println("success");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaBooks", "root", ""); // this ?
            System.out.println("success");
            //  Statement statement = connection.createStatement();
            return connection;
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } 
         return null;

    }



public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");
    dbConnection db = new dbConnection();
   Connection conn =  db.connectDB();
}
}
