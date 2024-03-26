 package MainSocket;



// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.Socket;

// public class client {
//     public static void main(String[] args) {
//         try {
//             // Connect to the server
//             Socket socket = new Socket("localhost", 6564);
//             System.out.println("Connected to server");

//             // Create separate threads for reading and writing to the server
//             Thread readThread = new Thread(() -> {
//                 try {
//                     DataInputStream reader = new DataInputStream(socket.getInputStream());
//       //               BufferedReader reader = new BufferedReader(
// 			//  new InputStreamReader(socket.getInputStream()));
//        String message;
//        while((message = reader.readLine()) != null){
//         System.out.println("Server response: " + message);
//        }
//                     // while (true) {
//                     //   System.out.println("gg1");
//                     //     String message = reader.readLine();
//                     //     System.out.println("Server says: " + message);
//                     // }
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             });

            // Thread writeThread = new Thread(() -> {
            //     try {
            //         DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            //         //DataInputStream userInput = new DataInputStream(System.in);
            //         BufferedReader reader = new BufferedReader(
			//  new InputStreamReader(System.in));
            //         while (true) {
            //           System.out.println("gg2");
            //             String input = reader.readLine();
            //             writer.writeUTF(input);
            //             writer.flush();
            //         }
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //     }
            // });

//             // Start the threads
//             readThread.start();

//             writeThread.start();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
// }

// ========================================== 
// mo7awla tanya inshallah tng7


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {



    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345); // Change host and port if needed
            System.out.println("Connected to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);


            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            boolean flag = false;

            Thread writeThread = new Thread(() -> {
                try {
            //         DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            //         //DataInputStream userInput = new DataInputStream(System.in);
            //         BufferedReader reader = new BufferedReader(
			//  new InputStreamReader(System.in));
                    while (true) {
                      //System.out.println("gg2");
                        String input = consoleReader.readLine();
                        writer.println(input);
                        //writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
           
            Thread readThread = new Thread(() -> {
                              try {
                     String message;
                     while((message = reader.readLine()) != null){
                      System.out.println("Server response: " + message);
                     
                     }
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }
                          });

            
readThread.start();
writeThread.start();

//             while (true) {
//               String question;
            
//                   System.out.print("Your response: ");
//                   String response = consoleReader.readLine(); // Read response from client
//                   writer.println(response); // Send response to server
  
//                     if(response.equals("1")) // register
//                   {
//                     System.out.println("11111");
//                     String username = consoleReader.readLine(); // c
//                     writer.println(username);
                  
//                     String password = consoleReader.readLine(); // c
//                     writer.println(password);
//                     if(reader.readLine() == "true")
//                     {
//                         flag = true;
//                     }
//                   }
  
//                   else if(response.equals("2")) // login
//                   {
//                     String username = consoleReader.readLine();
//                     writer.println(username);
//                     String password = consoleReader.readLine();
//                     writer.println(password);
//                     if(reader.readLine() == "true")
//                     {
//                         flag = true;
//                     }
//                   }
//                 //   if(flag)
//                 //   {
//                 //      if(response.equals("3"))
//                 //     {
//                 //         System.out.println("ana gowaa ahaha");
//                 //       String username = consoleReader.readLine();
//                 //       writer.println(username);
//                 //       String password = consoleReader.readLine();
//                 //       writer.println(password);
//                 //     }
//                 //   }

                 
//                   else if (response.equalsIgnoreCase("exit")) {
//                     writer.println("Goodbye!"); // Send final message
//                     break; // Exit loop if client wants to end the conversation
//                 }
                  
//  String choice = consoleReader.readLine();
//  writer.println(choice);
//             }

//             clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
