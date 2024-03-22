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
                ClientHandler cHandler = new ClientHandler(clientSocket);
                cHandler.getUser(new userController());
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

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void getUser(User user)
    {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
          //  User user = getUser(new userController());

            //String question;
            while (true) {
               // writer.println("What is your name?"); // Ask a question
               System.out.println("mn bara");
                writer.println("Good to see u");
                writer.println("1- register");
                writer.println("2- login");
                String response = reader.readLine(); // Receive response from client
                boolean flag = true;
                while(response.equals("1") || response.equals("2") || response.equals("exit") )
                {
                    System.out.println("d5lt");
                    if(response.equals("1"))
                {
                    System.out.println("gg");

                    writer.println("enter ur username"); // s
                String username =  reader.readLine(); // c
                System.out.println("username = " + username); 
                  writer.println("enter ur password"); // s
                 String password = reader.readLine(); // c
                 System.out.println("password = " + password);
               //  flag =  user.login(username, password);
                }

                if(response.equals("2"))
                {
                    System.out.println("gg2");
                    writer.println("enter ur username");
                    String username =  reader.readLine();
                    System.out.println(username);
                      writer.println("enter ur password");
                     String password = reader.readLine();
                     System.out.println(password);
                     System.out.println("gg3");
                }

                if (response.equalsIgnoreCase("exit")) {
                    writer.println("Goodbye!"); // Send final message
                    break; // Exit loop if client wants to end the conversation
                }

                if(flag) // h3ml function boolean w lw true h3ml ba2eet functions
                {
                    writer.println("1- Browse");
                    writer.println("2- Search");
                    writer.println("3- Add");
                    writer.println("4- Remove");
                    writer.println("5- Borrow");
                    writer.println("6- Requests"); // => hyb2a feeha Accepted Requests && denyable Request && pending // where requests = kaza fel query
                    String n2 = reader.readLine();
    
                    while(n2.equals("1") || n2.equals("2") || n2.equals("3") || n2.equals("4") || n2.equals("5") || n2.equals("6") || n2.equals("7") || n2.equals("8"))
                    {
                // Handle menu choices
                switch (n2) {
                    case "1":
                    System.out.println("1");
                        // Handle browse
                        break;
                    case "2":
                    System.out.println("2");
                        // Handle search
                        break;
                        case "3":
                    System.out.println("2");
                        // Handle search
                        break;
                        case "4":
                    System.out.println("2");
                        // Handle search
                        break;
                        case "5":
                    System.out.println("2");
                        // Handle search
                        break;
                        case "6":
                    System.out.println("2");
                        // Handle search
                        break;
                        case "7":
                    System.out.println("2");
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
                    writer.println("5- Borrow");
                    writer.println("6- Requests");
                    n2 = reader.readLine();
                }
            }
            

                response = reader.readLine();

                }
                

                

               
            }
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    
}

}
