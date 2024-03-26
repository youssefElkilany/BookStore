 package MainSocket;





import java.io.*;
import java.net.*;

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
                    while (true) {
                      //System.out.println("gg2");
                        String input = consoleReader.readLine();
                        writer.println(input);
                        if(input.equalsIgnoreCase("exit"))
                        {
                            break;
                        }
                        //writer.flush();
                    }
                    clientSocket.close();
                    reader.close();
                    writer.close();
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


        }  catch(SocketException e)
        {
            System.out.println("connection from client closed");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
    }
}
