package BookModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.dbConnection;

public class bookController implements Book {

    dbConnection db;
    @Override
    public String getAllBooks() {
        db = new dbConnection();
      Connection conn =  db.connectDB();
     try {
        Statement statement =  conn.createStatement();
        ResultSet rs = statement.executeQuery("Select * from book where quantity > 1");
        if(rs.next()){
        while(rs.next())
        {
            return rs.getString(1) + " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getString(5)+" "+ rs.getString(6);
           // System.out.println(rs.getString(1) + " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getString(5)+" "+ rs.getString(6));
        }
    }else{
        return "no available books";
       // System.out.println("no available books");
    }

    } catch (SQLException e) {
        
        e.printStackTrace();
    }
    return null;
    }

    @Override
    public String searchBook(String key) {
        db = new dbConnection();
        Connection conn =  db.connectDB();
        PreparedStatement ps;
       try {
        ps = conn.prepareStatement("SELECT * FROM book WHERE quantity LIKE ? OR author LIKE ? OR title LIKE ? OR genre LIKE ?");

        String searchKeyword = "user_input_search_keyword";
        String keywordPattern = "%" + searchKeyword + "%";

        // Set the parameters for the prepared statement
        ps.setString(1, keywordPattern);
        ps.setString(2, keywordPattern);
        ps.setString(3, keywordPattern);
        ps.setString(4, keywordPattern);

        // Execute the query
        ResultSet rs = ps.executeQuery();

        // Process the result set
        if(rs.next()){
        while (rs.next()) {
            return rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6);
           // System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
        }
        }else{
            return "no available Books";
        }


      } catch (SQLException e) {
         
          e.printStackTrace();
      }
      return null;
    }

    //browse && search && add && remove
}
