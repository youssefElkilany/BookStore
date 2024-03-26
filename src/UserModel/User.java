package UserModel;


public interface User {
    public boolean register(String username,String password,String role);
    public boolean login(String username,String password);
    public boolean checkAdmin(String username);
}
