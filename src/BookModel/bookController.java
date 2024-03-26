package BookModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxExpr.Array;

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
      //  while(rs.next())
        //{
            return rs.getString(1) + " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getInt(5)+" "+ rs.getInt(6);//+rs.getArray(7);
           
            // System.out.println(rs.getString(1) + " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getString(5)+" "+ rs.getString(6));
        //}
    }else{
        return "no available books";
       // System.out.println("no available books");
    }

    } catch (SQLException e) {
        
        e.printStackTrace();
    }
    return "gg";
    }

    @Override
    public String searchBook(String key) {
        db = new dbConnection();
        Connection conn =  db.connectDB();
        PreparedStatement ps;
       try {
        ps = conn.prepareStatement("SELECT * FROM book WHERE quantity LIKE ? OR author LIKE ? OR title LIKE ? OR genre LIKE ?");

        String keywordPattern = "%" + key + "%";

        // Set the parameters for the prepared statement
        ps.setString(1, keywordPattern);
        ps.setString(2, keywordPattern);
        ps.setString(3, keywordPattern);
        ps.setString(4, keywordPattern);

        // Execute the query
        ResultSet rs = ps.executeQuery();

        // Process the result set
        if(rs.next()){
       // while (rs.next()) {
           
            return rs.getString(1) + " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getInt(5)+" "+ rs.getInt(6);//+rs.getArray(7);
        //}
        }else{
            return "no available Books";
        }


      } catch (SQLException e) {
         
          e.printStackTrace();
      }
      return null;
    }

    @Override
    public String addBook(String lenderName, String title, String author, String genre, int price, int quanity) {
        
                db = new dbConnection();
              Connection conn = db.connectDB();
              PreparedStatement ps;
              try {
                ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? ");
                ps.setString(1, lenderName);
               // ps.setString(2, title);
                ResultSet rs = ps.executeQuery();
                if(!rs.next())
                {
                    return "lenderName not found";
                }
                else{
                    ps = conn.prepareStatement("SELECT * FROM book WHERE lenderName = ? AND title = ?");
                    ps.setString(1, lenderName);
                    ps.setString(2, title);
                    ResultSet rs2 = ps.executeQuery(); // if lender want to add same book so increase quantity instead of adding same book
                    if(rs2.next())
                    {
                        ps = conn.prepareStatement("UPDATE book SET quantity = ? WHERE lenderName = ? AND title = ? ");
                        ps.setInt(1, quanity);
                        ps.setString(2, lenderName);
                    ps.setString(2, title);
                    
                    }
                    else{
                        ps = conn.prepareStatement("INSERT INTO book(lenderName,title,author,genre,price,quantity) VALUES(?,?,?,?,?,?)");
                    
                        ps.setString(1, lenderName);
                        ps.setString(2, title);
                        ps.setString(3, author);
                        ps.setString(4, genre);
                        ps.setInt(5, price);
                        ps.setInt(6, quanity);
                       // ps.setInt(1, quanity);
                //         String clientsString = String.join(",", listOfClients);
                // ps.setString(7, clientsString);
        
                // Execute the update
                int rowsAffected = ps.executeUpdate();
        
                if (rowsAffected > 0) {
                    return "Book added successfully";
                } else {
                    return "Failed to add book";
                }
                    }
                }

                
               
                // ps.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "Failed to add book 2";
    }

    @Override
    public String removeBook() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBook'");
    }

    //browse && search && add && remove
}
