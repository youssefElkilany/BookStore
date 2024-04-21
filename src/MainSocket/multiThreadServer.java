 package MainSocket;


import java.io.*;
import java.net.*;
import java.util.ArrayList;

// import BookModel.Book;
// import BookModel.bookController;
// import RequestModel.Request;
// import RequestModel.requestController;
// import UserModel.User;
// import UserModel.userController;

public class multiThreadServer  {
   // public static ArrayList<ClientHandler> clientsList = new ArrayList<>(); // org
static ArrayList<server> servers = new ArrayList<>();
   public  ClientHandler client;
  // public static multiThreadServer server; // nshelha b3deen
    public static void main(String[] args) {
        try {
            
            ServerSocket serverSocket = new ServerSocket(12345); // Change port as needed
            System.out.println("Server started.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

               // ClientHandler clientThread = new ClientHandler(clientSocket);
              //  server = new multiThreadServer();
               ClientHandler client = new ClientHandler(clientSocket);
                System.out.println("clientThread = " + client);
                
               // server.setClient(client);
              //  System.out.println( "ana el 3amlha = " +  server.getClient()); 
                //clientsList.add(client); // org
                // ClientHandler cHandler = new ClientHandler(clientSocket);
                // cHandler.setUser(new userController());
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static synchronized server getAvailableServer() {
    for (server Server : servers) {
        if (!Server.isFull()) {
            return Server;
        }
    }
    // if (Servers.size() < MAX_ServerS) {
        server newServer = new server();
        servers.add(newServer);
        return newServer;
  //  }
    ///return null; // No available Server
}

public static synchronized server getAvailableServer2() {
    for (server Server : servers) {
        if (!Server.isSemiFull()) {
            return Server;
        }
    }
    // if (Servers.size() < MAX_ServerS) {
        server newServer = new server();
        servers.add(newServer);
        return newServer;
  //  }
    ///return null; // No available Server
}

public static ArrayList<server> getServers() {
    return servers;
}
    
    // public static void broadcastMessage(String message, ClientHandler sender) {
    //     for (ClientHandler client : clientsList) {
    //         if (client != sender) {
    //             client.sendMessage(message);
    //         }
    //     }
    // }

    // public void setClient(Thread client)
    // {
    //    this.client = client;
    // }

    // public Thread getClient()
    // {
    //     return client;
    // }

    // public void setClient(ClientHandler client)
    // {
    //     this.client = client;
    // }

    // public ClientHandler getClient()
    // {
    //     return client;
    // }
}

