package RequestModel;

public interface Request {

    
    public String sendRequest(String lenderName ,String borrowerName ,String title);
    public boolean respondToRequest(String lenderName,int requestId,String status);
    public String viewAllRequests(String lenderName);
    public int checkRequest(String borrowerName,int requestId); // lw request not finished w w feeh serverNo yb2a true 
    public boolean updateServerNo(String lenderName,int requestId,String status , int serverNo);

    // for Admin
    public String viewAllRequestsForAdmin();
    public String viewAllRequests2(String status); // depends on what status he wants
    public String borrowedBooks();
}
