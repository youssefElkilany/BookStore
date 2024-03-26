package RequestModel;

public interface Request {

    
    public String sendRequest(String lenderName ,String borrowerName ,String title);
    public boolean requestRespond(String lenderName,int requestId,String status);
    public String viewAllRequests(String lenderName);

}
