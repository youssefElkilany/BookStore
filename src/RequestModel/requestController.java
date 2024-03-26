package RequestModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.dbConnection;

public class requestController implements Request {

     dbConnection db;
    @Override
    public String sendRequest(String lenderName, String borrowerName,String title) { // mmkn azwd quantity
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; // lazm 23ml check 3la lender name w title bta3 ketab 3shan lw feeh kaza title b nafs elesm y5tar user el3ayz y lend meno

        try {

            ps = conn.prepareStatement("SELECT * FROM book WHERE lenderName = ? AND title = ? AND quantity > 1");
                ps.setString(1, lenderName);
                ps.setString(2, title);
                ResultSet rs = ps.executeQuery();
                if(!rs.next())
                {
                    return "lenderName or title not found or no enough quanity";
                }
                else{

                    ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                    ps.setString(1, borrowerName);
                    ResultSet rs2 = ps.executeQuery(); 
                    if(!rs2.next())
                    {
                        return "borrowerName not found";
                    }
                    else{
                        ps = conn.prepareStatement("INSERT INTO request(lenderName,borrowerName,title,status) VALUES(?,?,?,?)");
                        ps.setString(1, lenderName);
                        ps.setString(2, borrowerName);
                        ps.setString(3, title);
                        ps.setString(4, "pending");
                      //  ps.setString(4, status);
                        int result = ps.executeUpdate(); 
                        if(result > 0)
                        {
                            return "request send successufully";
                        }
                        else{
                            return "request didnt send";
                        }

                    }
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "request didnt send 2";
        
    }
    @Override
    public boolean requestRespond(String lenderName,int requestId ,String status) {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ? AND requestId = ? AND status = pending");
            ps.setString(1, lenderName);
            ps.setInt(2, requestId);
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
            {
                return false;
            }else{
                ps = conn.prepareStatement("UPDATE request SET status = ? WHERE lenderName = ? AND requestId = ?");
                ps.setString(1, status);
                ps.setString(2, lenderName);
                ps.setInt(3, requestId);

                int result = ps.executeUpdate(); // elmfrood update lel book hena w decrease quantity by 1 delwa2ty lakn b3deen 3la 7asab quantity el user hy7oto
                if(result > 0)
                {
                    ps = conn.prepareStatement("SELECT * FROM request WHERE requestId = ? "); // ha5od mn hena borrowerName w title
                    ps.setInt(1, requestId);
                   

                    ResultSet rs2 = ps.executeQuery();
                    if(rs2.next())
                    {
                        ps = conn.prepareStatement("UPDATE book SET quantity = quantity - 1 , listOfClients = ?  WHERE lenderName = ? AND title = ?");

                        ps.setString(2, lenderName);
                        ps.setString(3, rs2.getString(4)); // title
                        String clientsString = String.join(",", rs2.getString(3));
                        ps.setString(1, clientsString);
    
                        int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        return true;
                       // System.out.println("Quantity and array updated successfully.");
                    } else {
                        return false;
                       // System.out.println("No book found with the given title.");
                    }
                    }
                    else{
                        return false; 
                    }

                   
                   
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }



    @Override// mmkn as2lo 3ayz status ehh w agblo el howa 3ayzo badal ma agblo kolo => hzbtha b3deen
    public String viewAllRequests(String lenderName) {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ?");
            ps.setString(1, lenderName);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
              return  rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
            }else{
                return "no available requests";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "no available requests 2";
    }

    //send Request
    //accept request && deny Request
    
}
