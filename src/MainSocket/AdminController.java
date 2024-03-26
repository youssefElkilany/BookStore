package MainSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import BookModel.Book;
import BookModel.bookController;
import RequestModel.Request;
import RequestModel.requestController;

public class AdminController {

    private PrintWriter writer;
   private BufferedReader reader;
   private bookController book;
   private requestController request;

    public void adminFunctionalities(String savedUser,Socket clientSocket,PrintWriter writer,BufferedReader reader,Book book,Request request)
    {
        writer.println("1- view all books");
        writer.println("2- view all requests");
        writer.println("3- borrowedBooks");
        writer.println("4- requests");
        String n2;
        System.out.println("client socket = " + clientSocket);
        try {
            
            n2 = reader.readLine();
            while(n2.equals("1") || n2.equals("2") || n2.equals("3") || n2.equals("4") )
        {
            switch (n2) {
                case "1": // view all books
                System.out.println("username = " + savedUser);
                writer.println("here are all the available books");
                String resultOfBooks =  book.getAllBooks();
                writer.println(resultOfBooks);
              System.out.println(resultOfBooks);

                System.out.println("1");
                    // Handle browse
                    break;
                    case "2": // view all requests
                
                writer.println("here are all the available requests");
             String req =  request.viewAllRequestsForAdmin();
             writer.println(req);

                System.out.println("2");
                    // Handle browse
                    break;
                    case "3": // borrowedBooks
                
                writer.println("here are all the available books");
                String req2 = request.borrowedBooks(); // to be continued
                writer.println(req2);
             

                System.out.println("3");
                    // Handle browse
                    break;
                    case "4": // requests
                    writer.println("enter status of request");
                    String status2 = reader.readLine();
              String res3 =  request.viewAllRequests2(status2);
              writer.println(res3);
              

                System.out.println("4");
                    // Handle browse
                    break;
        }
        writer.println("1- view all books");
        writer.println("2- view all requests");
        writer.println("3- borrowedBooks");
        writer.println("4- requests");
         n2 = reader.readLine();  
    }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  

        
}

}
