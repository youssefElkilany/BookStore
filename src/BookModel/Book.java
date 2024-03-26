package BookModel;

import java.util.ArrayList;
import java.util.List;

public interface Book {

    public String getAllBooks();
    public String searchBook(String key);
    public String addBook(String lenderName,String title,String author,String genre, int price , int quanity );
    public String removeBook(String lenderName,String title);
}
