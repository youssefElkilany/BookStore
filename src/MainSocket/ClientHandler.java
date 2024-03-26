package MainSocket;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import BookModel.Book;
import BookModel.bookController;
import RequestModel.Request;
import RequestModel.requestController;
import UserModel.User;
import UserModel.userController;


class ClientHandler extends Thread {
    private Socket clientSocket;
    private User user;
    private Book book;
    private Request request;
   
    private PrintWriter writer;
    ClientHandler client ;
    private server Server;

   

    public ClientHandler(Socket clientSocket ) {
        this.clientSocket = clientSocket;
        
     
       try {
        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    public String toString() {
        return  clientSocket.toString();
    }
    

   

   

    
    
    public void sendMessage(String message) {
        writer.println(message);
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
        AdminController admin;
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
             
                
               System.out.println("ana el 3amlha 2  = "+  multiThreadServer.server.getClient());
               System.out.println("client = "+ client);
               System.out.println("client = "+ ClientHandler.this);
               System.out.println("clientsList = " + multiThreadServer.clientsList);
              
                String savedUser = "";
               
               System.out.println("mn bara");
                writer.println("Good to see u");
                writer.println("1- register");
                writer.println("2- login");
                writer.println("type exit to close connection");
              
                String response = reader.readLine(); // Receive response from client
              //  System.out.println(response);
                boolean flag = false;
                
                while(response.equals("1") || response.equals("2") || response.equals("3") )
                {
                    System.out.println("d5lt");
                    if(response.equals("1")) // register
                {

                    writer.println("enter ur username"); // s
                String username =  reader.readLine(); // c
                savedUser = username;
                System.out.println("username = " + username); 
                  writer.println("enter ur password"); // s
                 String password = reader.readLine(); // c
                 System.out.println("password = " + password);
                 writer.println("enter ur role"); // s
                 String role = reader.readLine();
                 if(!role.equalsIgnoreCase("user") && !role.equalsIgnoreCase("admin"))
                 {
                    writer.println("u need to enter user or admin");
                    break;
                 }
                 flag =  user.register(username, password,role);
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
                        writer.println("not found (404) or  unauthorized (401)");
                     }
                }

               
               

                if (response.equalsIgnoreCase("3")) {
                    writer.println("Goodbye!"); // Send final message
                    break; // Exit loop if client wants to end the conversation
                }

               

                if(flag) // h3ml function boolean w lw true h3ml ba2eet functions
                {
                    System.out.println("savedUser = " + savedUser);
                 boolean checkadmin =  user.checkAdmin(savedUser);
                 System.out.println(checkadmin);
                 if(checkadmin)
                 {
                    admin = new AdminController();
                    admin.adminFunctionalities(savedUser,clientSocket,writer,reader,book,request);
                
                
                }
                   
                    System.out.println("savedUser2 = " + savedUser);
                    writer.println("1- Browse");
                    writer.println("2- Search");
                    writer.println("3- Add");
                    writer.println("4- Remove");
                    writer.println("5- send request");
                    writer.println("6-view Requests"); // => hyb2a feeha Accepted Requests && denyable Request && pending // where requests = kaza fel query
                    writer.println("7-response to Requests");
                    writer.println("8- see chating requests");
                    writer.println("enter invalid choice to logout");
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
            
                        // writer.println("enter Lender name");
                        // lenderName = reader.readLine();
                        writer.println("enter title");
                        title = reader.readLine();
                        writer.println("enter author");
                        author = reader.readLine();
                        writer.println("enter genre");
                        genre = reader.readLine();
               
                          try {
                            writer.println("enter price");
                            price = Integer.parseInt(reader.readLine());
                            writer.println("enter quantity");
                        quanity = Integer.parseInt(reader.readLine());
                        

                       String result2 = book.addBook(savedUser, title, author, genre,  price ,  quanity);
                       writer.println(result2);
                            
                          } catch (NumberFormatException e) {
                            writer.println("Invalid input. Please enter an integer.");
                          
                          }
                            
                    System.out.println("3");
                        // Handle search
                        break;
                        case "4": // remove books
                        writer.println("enter title");
                        String title2 = reader.readLine();
                      String removeRes =   book.removeBook(savedUser, title2);
                      writer.println(removeRes);
                    System.out.println("4");
                        // Handle search
                        break;
                        case "5": // send requests to borrow book

                        writer.println("enter lenderName");
                        String lender =  reader.readLine();
                        if(lender == savedUser) // if borrowerName == lender
                        {
                            writer.println("u cant assign someUser to borrow from ur books");
                            break;
                        }
                        writer.println("enter title ");
                        String titlee =  reader.readLine();
                        
                        String requestResult = request.sendRequest(lender, savedUser, titlee);
                        writer.println(requestResult);

                    System.out.println("5");
                        // Handle search
                        break;
                        case "6": // view requests

                       
                        String requests = request.viewAllRequests(savedUser);
                        writer.println(requests);

                    System.out.println("6");
                        // Handle search
                        break;
                        case "7": // respond to requests
                        
                        writer.println("enter requestId");
                        int requestId = 0;
                        try {
                             requestId =  Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            writer.println("Invalid input. Please enter an integer.");
                        }
                        writer.println("enter status ");
                        String status =  reader.readLine();
                        if(!status.equalsIgnoreCase("accepted") && !status.equalsIgnoreCase("rejected"))
                        {
                            writer.println("status must be equal to accepted or rejected");
                            break;
                        }

                        boolean respond = request.respondToRequest(savedUser, requestId, status);
                        if(respond) // h3ml hena communication bewteen 2 clients  =>if endpoint is true nothing wrong happens
                        {
                            if(status.equalsIgnoreCase("accepted"))
                            {

                                Server = multiThreadServer.getAvailableServer2();
                                            if (Server != null) {
                                                Server.addClient(this);
                                                writer.println("You are in Server " + (multiThreadServer.servers.indexOf(Server) + 1));
                                            }
                                            boolean res = request.updateServerNo(savedUser, requestId, status, multiThreadServer.servers.indexOf(Server) + 1);
                                            if(res)
                                            {
                                                while (true) {
                                                    writer.println("Enter your message to broadcast or say out to exit chatting:");
                                                    String message = reader.readLine();
                                                    if(message.equalsIgnoreCase("out"))
                                                    {
                                                     break;
                                                    } 
                                                   Server.broadcastMessage(message,this);   // h3ml hena message lel exit
                                                }
                                            }
                                            else{
                                                writer.println("no requests found");
                                                break;
                                            }

                               
                              // }
                               
                           }

                                writer.println("request is " + status);
                            }
                            else if(status.equalsIgnoreCase("rejected"))
                            {
                                writer.println("request is " + status);
                            }
                       // }
                        else{
                            writer.println("something wrong in api");
                        }
                    System.out.println("7");
                        // Handle search
                        break;
                        case "8": // browse
                        // if (n2.equalsIgnoreCase("8")) {
                            Server = multiThreadServer.getAvailableServer();
                            if (Server != null) {
                                writer.println("enter requestId");
                            //  String borrowName =  reader.readLine();
                            //  writer.println("enter requestId");
                            int requestId2 = 0;
                           // requestId2 = Integer.parseInt(reader.readLine());
                            try {
                                requestId2 = Integer.parseInt(reader.readLine());
                            } catch (NumberFormatException e) {
                                writer.println("Invalid input. Please enter an integer.");
                            }
                             
                             
                             int serverNo =  request.checkRequest(savedUser, requestId2);
                             if(serverNo != -1)
                             {
                                ArrayList<server>servers = multiThreadServer.getServers();
                                for (int i = 0; i < servers.size(); i++) {
                                    writer.println((i + 1) + ". Room " + (i + 1));
                                }
                                Server = servers.get(serverNo - 1); // rakam elhgeebo mn database
                                System.out.println(servers.get(serverNo - 1));
                                Server.addClient(this);
                                writer.println("You are in Server " + (multiThreadServer.servers.indexOf(Server) + 1));
                             }
                             else{
                                writer.println("no accepted requests yet to enter chat room " + serverNo);
                                break;
                             }

           
                                while (true) {
                                    writer.println("Enter your message to broadcast or say out to exit chatting:");
                                    String message = reader.readLine();
                                    if(message.equalsIgnoreCase("out"))
                                   {
                                    break;
                                   }   //add hena exit msg
                                   Server.broadcastMessage(message,this);
                                   
                                }
                            }
                            
                       // }

                    System.out.println("8");
                        // Handle browse
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
                 writer.println("8- see chating requests");
                 writer.println("enter invalid choice to logout");
                    n2 = reader.readLine();
                }
            }

           // writer.println(writer);
            writer.println("Good to see u again");
            writer.println("1- register");
            writer.println("2- login");
            writer.println("3- exit");
                response = reader.readLine();

                }
                

                

               
            }
       

        } catch (java.net.SocketException e) {
           System.out.println("connection from client closed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        catch(java.lang.NullPointerException e)
        {
            System.out.println("null returned");
        }
        
    
}

}


