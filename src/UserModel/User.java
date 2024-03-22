package UserModel;


public interface User {
    public boolean register(String username,String password);
    public boolean login(String username,String password);
}
