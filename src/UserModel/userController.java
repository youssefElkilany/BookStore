package UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DB.dbConnection;

public class userController implements User {

   
    dbConnection db;
    @Override
    public boolean register(String username, String password) {
       
      db = new dbConnection();
      Connection conn = db.connectDB();
      PreparedStatement ps;
     // Statement s ; 
      try {
        // s = conn.createStatement();
        // ResultSet rs = s.executeQuery("Select * from users");
      ps =  conn.prepareStatement("SELECT username from users WHERE username = ?");
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return false;
       // System.out.println("username already exist");
      }
      else
      {
        ps = conn.prepareStatement("Insert into users(username,password)Values(?,?)");
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        return true;
       // System.out.println("welcome to the server");
}
    } catch (SQLException e) {
        e.printStackTrace();
    }
       return false;
    }

    @Override
    public boolean login(String username, String password) {
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps;
        try {
        ps =  conn.prepareStatement("SELECT * from users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ps =  conn.prepareStatement("SELECT * from users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs2 = ps.executeQuery();
            if(rs2.next())
            {
                return true;
               // System.out.println("welcome to our server");
            }
            else{
                return false;
                //System.out.println("unauthorized");
            }
        }
        else
        {
            return false;
          //  System.out.println("username not found");
        }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return false;
    }

    // signUp && login
}
