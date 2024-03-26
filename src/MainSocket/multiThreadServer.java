package MainSocket;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.net.ServerSocket;
// import java.net.Socket;

// import UserModel.userController;

// public class multiThreadServer extends server implements Runnable {
//     public void run()
//     {
//         execute();
//     }

//     public static void main(String[] args) throws Exception {
//         System.out.println("Hello, World!");

//         try {
//             ServerSocket serverSocket = new ServerSocket(6564);

//             while (true) {
             
//              Socket socket = serverSocket.accept();
//              socket.setSoTimeout(15000);
//              multiThreadServer server = new multiThreadServer();
//              server.setUser(new userController());
//              server.setSocket(socket);
     
//              Thread thread = new Thread(server);
//              thread.start();
//      System.out.println("gg");
//              // DataInputStream reader = new DataInputStream(socket.getInputStream());
     
//              //     DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
     
                 
//              //     writer.println();("+:12:21");
               
//              //     writer.flush();
                 
//              //    writer.println();("from server");
               
//              //     writer.flush();
          
          
//              //     System.out.println(reader.readUTF());
             
//              System.out.println("multiThread is runnning");
     
//             }
     
//          }
     
            
//          catch (IOException e) {
//             // TODO: handle exception
//         }
     
   
//     }
    

// }
// =================================================

// second chance


import java.io.*;
import java.net.*;
import java.util.ArrayList;

import BookModel.Book;
import BookModel.bookController;
import RequestModel.Request;
import RequestModel.requestController;
import UserModel.User;
import UserModel.userController;

public class multiThreadServer  {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Change port as needed
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                Thread clientThread = new ClientHandler(clientSocket);
                // ClientHandler cHandler = new ClientHandler(clientSocket);
                // cHandler.setUser(new userController());
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private User user;
    private Book book;
    private Request request;
    

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        
    }

    

   

    public void setUser(User user)
    {
        this.user = user;
    }
    public void setBook(Book book)
    {
        this.book = book;
    }
    public void setRequest(Request request)
    {
        this.request = request;
    }


    @Override
    public void run() {
        BufferedReader reader;
        PrintWriter writer;
        try {
             reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             writer = new PrintWriter(clientSocket.getOutputStream(), true);
          //  User user = getUser(new userController());
         // ClientHandler clientHandler = new ClientHandler(clientSocket);
        user = new userController();
        book = new bookController();
        request = new requestController();
        

            //String question;
            while (true) {
               
                String savedUser = "";
               // writer.println("What is your name?"); // Ask a question
               System.out.println("mn bara");
                writer.println("Good to see u");
                writer.println("1- register");
                writer.println("2- login");
                writer.println("3- exit");
               // System.out.println(reader.readLine());
                String response = reader.readLine(); // Receive response from client
              //  System.out.println(response);
                boolean flag = true;
                // if (!response.equalsIgnoreCase("exit") && !response.equals("1") && !response.equals("2")) {
                //     writer.println("Good damn!");
                //     break; 
                // }else
                while(response.equals("1") || response.equals("2") || response.equals("3") )
                {
                    System.out.println("d5lt");
                    if(response.equals("1")) // register
                {

                    writer.println("enter ur username"); // s
                String username =  reader.readLine(); // c
                System.out.println("username = " + username); 
                  writer.println("enter ur password"); // s
                 String password = reader.readLine(); // c
                 System.out.println("password = " + password);
                 flag =  user.register(username, password);
                 if(flag)
                 {
                    writer.println("registed succsessfully");
                 }else{
                    writer.println("email already exist");
                 }
                }

                if(response.equals("2")) // login
                {
                    
                    writer.println("enter ur username");
                    String username =  reader.readLine();
                    savedUser = username;
                    System.out.println(username);
                      writer.println("enter ur password");
                     String password = reader.readLine();
                     System.out.println(password);
                     flag =  user.login(username, password);
                     if(flag)
                     {
                        writer.println("login succsessfully");
                     }else{
                        writer.println("not found or authorized");
                     }
                }
               

                if (response.equalsIgnoreCase("3")) {
                    writer.println("Goodbye!"); // Send final message
                    break; // Exit loop if client wants to end the conversation
                }

               

                if(flag) // h3ml function boolean w lw true h3ml ba2eet functions
                {
                    //client = new ClientHandler(clientSocket);
                    //System.out.println(client); // important *********************************
                    // *** lw ragel dah accepted 3ndo h3mlo add fel list w yd5ol y3ml chat m3 eltany 8eer keda msh h3mlo add w msh hynf3 y3ml chat
                    // h3ml condition hena lw howa user elhytft7 m3aah chat hd5lo 3aleeh 3la tool r5ama
                    //client.addClient(client);
                    System.out.println(savedUser);
                    writer.println("1- Browse");
                    writer.println("2- Search");
                    writer.println("3- Add");
                    writer.println("4- Remove");
                    writer.println("5- send request");
                    writer.println("6-view Requests"); // => hyb2a feeha Accepted Requests && denyable Request && pending // where requests = kaza fel query
                    writer.println("7-response to Requests");
                    String n2 = reader.readLine();
    
                    while(n2.equals("1") || n2.equals("2") || n2.equals("3") || n2.equals("4") || n2.equals("5") || n2.equals("6") || n2.equals("7") || n2.equals("8"))
                    {
                // Handle menu choices
                switch (n2) {
                    case "1": // browse
                    System.out.println("username = " + savedUser);
                    writer.println("here are all the available books");
                    String resultOfBooks =  book.getAllBooks();
                    writer.println(resultOfBooks);
                  System.out.println(resultOfBooks);

                    System.out.println("1");
                        // Handle browse
                        break;
                    case "2": // search
                    writer.println("enter a word to search"); // genre or author ...etc
                   String word =  reader.readLine();
                 String result =  book.searchBook(word);
                 writer.println(result);
                    System.out.println("2");
                        // Handle search
                        break;
                        case "3": // add books
                        String lenderName, title, author, genre; 
                        int price, quanity;
            
                        writer.println("enter Lender name");
                        lenderName = reader.readLine();
                        writer.println("enter title");
                        title = reader.readLine();
                        writer.println("enter author");
                        author = reader.readLine();
                        writer.println("enter genre");
                        genre = reader.readLine();
                //         writer.println("enter price");
                //         price = Integer.parseInt(reader.readLine());
                //         writer.println("enter quantity");
                //     quanity = Integer.parseInt(reader.readLine());
                    

                //    String result2 = book.addBook(lenderName, title, author, genre,  price ,  quanity);
                //    writer.println(result2);
            
                        // catch (NumberFormatException e) {
                        //     PrintWriter writerr;
                        //   try {
                        //       writerr = new PrintWriter(clientSocket.getOutputStream(), true);
                        //       writerr.println("Invalid input. Please enter an integer.");
                        //   } catch (IOException e1) {
                        //       // TODO Auto-generated catch block
                        //       e1.printStackTrace();
                        //   }
                        //   }
                          try {
                            writer.println("enter price");
                            price = Integer.parseInt(reader.readLine());
                            writer.println("enter quantity");
                        quanity = Integer.parseInt(reader.readLine());
                        

                       String result2 = book.addBook(lenderName, title, author, genre,  price ,  quanity);
                       writer.println(result2);
                            
                          } catch (NumberFormatException e) {
                            writer.println("Invalid input. Please enter an integer.");
                          //  break;
                            // TODO: handle exception
                          }
                    //       String result2 = book.addBook(lenderName, title, author, genre,  price ,  quanity);
                    //    writer.println(result2);
                            
                    System.out.println("3");
                        // Handle search
                        break;
                        case "4": // remove books
                    System.out.println("4");
                        // Handle search
                        break;
                        case "5": // send requests to borrow book

                        writer.println("enter lenderName");
                       String lendername =  reader.readLine();
                        writer.println("enter borrowerName");
                        String borrowername =  reader.readLine();
                        if(borrowername == lendername)
                        {
                            writer.println("u cant borrow from ur self");
                            break;
                        }
                        writer.println("enter title ");
                        String titlee =  reader.readLine();
                        
                        String requestResult = request.sendRequest(lendername, borrowername, titlee);
                        writer.println(requestResult);

                    System.out.println("5");
                        // Handle search
                        break;
                        case "6": // view requests

                        writer.println("enter lenderName");
                        String lendername2 =  reader.readLine();
                        String requests = request.viewAllRequests(lendername2);
                        writer.println(requests);

                    System.out.println("6");
                        // Handle search
                        break;
                        case "7": // respond to requests
                        writer.println("enter lenderName");
                       String lendername3 =  reader.readLine();
                        writer.println("enter requestId");
                        int requestId = 0;
                        try {
                             requestId =  Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            writer.println("Invalid input. Please enter an integer.");
                        }
                        writer.println("enter status ");
                        String status =  reader.readLine();
                        if(!status.equalsIgnoreCase("accepted") || !status.equalsIgnoreCase("rejected"))
                        {
                            writer.println("status must be equal to accepted or rejected");
                            break;
                        }

                        boolean respond = request.requestRespond(lendername3, requestId, status);
                        if(respond) // h3ml hena communication bewteen 2 clients
                        {
                            if(status.equalsIgnoreCase("accepted"))
                            {
                                writer.println("request is " + status);
                            }
                            else if(status.equalsIgnoreCase("rejected"))
                            {
                                writer.println("request is " + status);
                            }
                        }
                        else{
                            writer.println("something wrong in api");
                        }
                    System.out.println("7");
                        // Handle search
                        break;
                    // Add cases for other menu options
                    default:
                    System.out.println("11");
                        // Handle invalid menu choice
                        break;
                }
                 // after finishing above user can do any of this again
                 writer.println("1- Browse");
                 writer.println("2- Search");
                 writer.println("3- Add");
                 writer.println("4- Remove");
                 writer.println("5- send request");
                 writer.println("6-view Requests"); 
                 writer.println("7-response to Requests");
                    n2 = reader.readLine();
                }
            }

            writer.println(writer);
                response = reader.readLine();

                }
                

                

               
            }
       
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
}

}
