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
    public boolean respondToRequest(String lenderName,int requestId ,String status) {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ? AND requestId = ? AND status = ?");
            ps.setString(1, lenderName);
            ps.setInt(2, requestId);
            ps.setString(3, "pending");
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
            {
                System.out.println("hena 111");
                return false;
            }else{
                ps = conn.prepareStatement("UPDATE request SET status = ? , statusOfServer = ? WHERE lenderName = ? AND requestId = ?");
                ps.setString(1, status);
                ps.setString(2, "not finished");
                ps.setString(3, lenderName);
                ps.setInt(4, requestId);

System.out.println("hena 1");

                int result = ps.executeUpdate(); // elmfrood update lel book hena w decrease quantity by 1 delwa2ty lakn b3deen 3la 7asab quantity el user hy7oto
                if(result > 0)
                {

                    ps = conn.prepareStatement("SELECT * FROM request WHERE requestId = ? "); // ha5od mn hena borrowerName w title
                    ps.setInt(1, requestId);
                   
                    ResultSet rs2 = ps.executeQuery();
                    System.out.println("hena 2");
                    if(rs2.next())
                    {
                        ps = conn.prepareStatement("SELECT listOfClients FROM book WHERE lenderName = ? AND title = ? ");
                        
                        ps.setString(1, lenderName);
                        ps.setString(2, rs2.getString(4));
                        ResultSet rs3 = ps.executeQuery();
                        if(rs3.next())
                        {
                            ps = conn.prepareStatement("UPDATE book SET quantity = quantity - 1 , listOfClients = ?  WHERE lenderName = ? AND title = ?");

                        ps.setString(2, lenderName);
                        ps.setString(3, rs2.getString(4)); // title
                        
                        String clientsString = String.join(",", rs2.getString(3) + " , " +  rs3.getString(1) + " , ");
                        ps.setString(1, clientsString);
    
                        int rowsUpdated = ps.executeUpdate();
                        System.out.println("hena 3");
                    if (rowsUpdated > 0) {
                        System.out.println("hena 4");
                        return true;
                       // System.out.println("Quantity and array updated successfully.");
                    } else {
                        System.out.println("hena 5");
                        return false;
                       // System.out.println("No book found with the given title.");
                    }
                        }
                    
                    }
                    else{
                        System.out.println("hena 6");
                        return false; 
                    }

                   
                   
                }else{
                    System.out.println("hena 7");
                    return false;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("hena 8");
        return false;
    }



//     @Override
//     public boolean respondToRequest(String lenderName,int requestId ,String status) {
        
//         db = new dbConnection();
//         Connection conn = db.connectDB();
//         PreparedStatement ps; 
//         try {
//             ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ? AND requestId = ? AND status = ?");
//             ps.setString(1, lenderName);
//             ps.setInt(2, requestId);
//             ps.setString(3, "pending");
//             ResultSet rs = ps.executeQuery();
//             if(!rs.next())
//             {
//                 System.out.println("hena 111");
//                 return false;
//             }else{
//                 ps = conn.prepareStatement("UPDATE request SET status = ? WHERE lenderName = ? AND requestId = ?");
//                 ps.setString(1, status);
//               //  ps.setString(2, serverNo);
//                 ps.setString(2, lenderName);
//                 ps.setInt(3, requestId);

// System.out.println("hena 1");

//                 int result = ps.executeUpdate(); // elmfrood update lel book hena w decrease quantity by 1 delwa2ty lakn b3deen 3la 7asab quantity el user hy7oto
//                 if(result > 0)
//                 {
//                     ps = conn.prepareStatement("SELECT * FROM request WHERE requestId = ? "); // ha5od mn hena borrowerName w title
//                     ps.setInt(1, requestId);
                   
//                     ResultSet rs2 = ps.executeQuery();
//                     System.out.println("hena 2");
//                     if(rs2.next())
//                     {
//                         ps = conn.prepareStatement("UPDATE book SET quantity = quantity - 1 , listOfClients = ?  WHERE lenderName = ? AND title = ?");

//                         ps.setString(2, lenderName);
//                         ps.setString(3, rs2.getString(4)); // title
//                         String clientsString = String.join(",", rs2.getString(3));
//                         ps.setString(1, clientsString);
    
//                         int rowsUpdated = ps.executeUpdate();
//                         System.out.println("hena 3");
//                     if (rowsUpdated > 0) {
//                         System.out.println("hena 4");
//                         return true;
//                        // System.out.println("Quantity and array updated successfully.");
//                     } else {
//                         System.out.println("hena 5");
//                         return false;
//                        // System.out.println("No book found with the given title.");
//                     }
//                     }
//                     else{
//                         System.out.println("hena 6");
//                         return false; 
//                     }

                   
                   
//                 }else{
//                     System.out.println("hena 7");
//                     return false;
//                 }
//             }
//         } catch (SQLException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//         System.out.println("hena 8");
//         return false;
//     }

    @Override
    public String viewAllRequests(String lenderName) {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ?");
            ps.setString(1, lenderName);
            ResultSet rs = ps.executeQuery();
            StringBuilder requestArray = new StringBuilder();
            boolean flag = false;
            while (rs.next()) {
                String requests =   rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
                requestArray.append(requests).append("\n");
                flag = true;
            }

            if (flag) {
                return requestArray.toString();
            } else {
                return "no available requests";
            }
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "no available requests 2";
    }

    @Override // deh hst5dmha fel awal
    public int checkRequest(String borrowerName,int requestId)
    {
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT serverNo FROM request WHERE borrowerName = ? AND requestId = ? AND statusOfServer = ? AND status = ?");
            ps.setString(1, borrowerName);
            ps.setInt(2, requestId);
            ps.setString(3, "not finished");
            ps.setString(4, "accepted");
           
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                ps = conn.prepareStatement("UPDATE request SET statusOfServer = ? WHERE requestId = ?");
                ps.setString(1, "finished");
                ps.setInt(2, requestId);
                ps.executeUpdate();

                return rs.getInt(1);
          //    return  rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
            }else{
                System.out.println("entered query");
                return -1;
         //       return "no available requests";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("entered query 2");
        return -1;
    }
    @Override
    public boolean updateServerNo(String lenderName, int requestId, String status, int serverNo) {
    
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE lenderName = ? AND requestId = ? AND status = ?");
            ps.setString(1, lenderName);
            ps.setInt(2, requestId);
            ps.setString(3, "accepted");
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
            {
                return false;
            }else{
                ps = conn.prepareStatement("UPDATE request SET serverNo = ? WHERE lenderName = ? AND requestId = ?");
                ps.setInt(1, serverNo);
                ps.setString(2, lenderName);
                ps.setInt(3, requestId);

                int result = ps.executeUpdate(); // elmfrood update lel book hena w decrease quantity by 1 delwa2ty lakn b3deen 3la 7asab quantity el user hy7oto
                if(result > 0)
                {
                   return true;

                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Unimplemented method 'updateServerNo'");
    }


// for admin

    @Override
    public String viewAllRequestsForAdmin() {
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request");
            
            ResultSet rs = ps.executeQuery();
            StringBuilder requestArray = new StringBuilder();
            boolean flag = false;
            while (rs.next()) {
                String bookInfo = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
                requestArray.append(bookInfo).append("\n");
                flag = true;
            }

            if (flag) {
                return requestArray.toString();
            } else {
                return "no available requests";
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "no available requests 2";
    }
    @Override
    public String viewAllRequests2(String status) {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE status = ?");
            ps.setString(1, status);
            
            ResultSet rs = ps.executeQuery();
            StringBuilder requestArray = new StringBuilder();
            boolean flag = false;
            while (rs.next()) {
                String bookInfo = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
                requestArray.append(bookInfo).append("\n");
                flag = true;
            }

            if (flag) {
                return requestArray.toString();
            } else {
                return "no available requests";
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "no available requests 2";
    }


    @Override
    public String borrowedBooks() {
        
        db = new dbConnection();
        Connection conn = db.connectDB();
        PreparedStatement ps; 
        try {
            ps = conn.prepareStatement("SELECT * FROM request WHERE status = ?");
            ps.setString(1, "accepted");
            
            ResultSet rs = ps.executeQuery();
            StringBuilder requestArray = new StringBuilder();
            boolean flag = false;
            while (rs.next()) {
                String bookInfo = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
                requestArray.append(bookInfo).append("\n");
                flag = true;
            }

            if (flag) {
                return requestArray.toString();
            } else {
                return "no available requests";
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "no available requests 2";
    }

    //send Request
    //accept request && deny Request
    
}

