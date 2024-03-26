package MainSocket;
import java.util.ArrayList;



public class server {



    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public synchronized boolean addClient(ClientHandler client) { // org
        if (clients.size() < 2) { // 2 is max client
            clients.add(client);
            return true;
        }
        return false; // Room is full
    }

    // public synchronized boolean addClient(UserController userController) {
    //     if (clients.size() < 2) { // 2 is max client
    //         clients.add(userController);
    //         return true;
    //     }
    //     return false; // Room is full
    // }


    public synchronized void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized boolean isFull() {
        return clients.size() >= 2; // 2 is max client in a server
    }

    public synchronized boolean isSemiFull() {
        return clients.size() >= 1; // if one accepted a request and another one accepted request so they will enter together and i dont want them to enter together
    }

    // public synchronized void broadcastMessage(String message,UserController userController) {
    //     for (ClientHandler client : clients) {
    //         if (client != userController) {
    //             client.sendMessage(message);
    //         }
    //     }
    // }

    public synchronized void broadcastMessage(String message,ClientHandler sender) { // org
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }




//    public  Socket socket;
//    public User user;
//    public ArrayList<String> msg = new ArrayList<>();
   


//    public void sendMsg(String str){
//     try {
//         DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
//         writer.writeUTF(str);

//     } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//     }
    
//    }

//    public ArrayList<String> getMessages(){
//     return msg;
//    }
//    //ha7ot objects mn kol classes el hst5dmha t2reban

//    public void setUser(User user)
//    {
//     this.user = user;
//    }

//    public void setSocket(Socket socket) {
//     this.socket = socket;
// }

//    // having all the functions of the system
//    public void execute() 
//    {
//     try (DataInputStream reader = new DataInputStream(socket.getInputStream())) {
//         DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
    
//     try {
        

//         writer.writeUTF("Good to see u");
//         writer.writeUTF("1- register");
//         writer.writeUTF("2- login");

//         int n = reader.readInt();
//         System.out.println(n);
//         while(n == 1 || n == 2)
//         {
//             boolean flag = false;
//             System.out.println("2");
        
//             if(n == 1)
//             {
//                 System.out.println("1");
//                 writer.writeUTF("enter ur username");
//                 String username =  reader.readUTF();
//                   writer.writeUTF("enter ur password");
//                  String password = reader.readUTF();
//                 //  user.login(username, password);
//                 flag = true;
//             }
//             if(n == 2)
//             {
                
//             }

//             if(flag) // h3ml function boolean w lw true h3ml ba2eet functions
//             {
//                 writer.writeUTF("1- Browse");
//                 writer.writeUTF("2- Search");
//                 writer.writeUTF("3- Add");
//                 writer.writeUTF("4- Remove");
//                 writer.writeUTF("5- Borrow");
//                 writer.writeUTF("6- Requests"); // => hyb2a feeha Accepted Requests && denyable Request && pending // where requests = kaza fel query
//                 int n2 = reader.readInt();

//                 while(n2 == 1 || n2 == 2 || n2 == 3 || n2 == 4 || n2 == 5 || n2 == 6 || n2 == 7 || n2 == 8)
//             // Handle menu choices
//             switch (n2) {
//                 case 1:
//                 System.out.println("1");
//                     // Handle browse
//                     break;
//                 case 2:
//                 System.out.println("2");
//                     // Handle search
//                     break;
//                 // Add cases for other menu options
//                 default:
//                 System.out.println("11");
//                     // Handle invalid menu choice
//                     break;
//             }
//              // after finishing above user can do any of this again
//             writer.writeUTF("1- Browse");
//                 writer.writeUTF("2- Search");
//                 writer.writeUTF("3- Add");
//                 writer.writeUTF("4- Remove");
//                 writer.writeUTF("5- Borrow");
//                 writer.writeUTF("6- Requests");
//                 n2 = reader.readInt();
//             }

            
           
//         }



//     } catch (IOException | NumberFormatException e) {
//         try {
//             writer.writeUTF("Invalid input. Please enter a valid integer.");
//             System.err.println("Error reading user input: " + e.getMessage());
//         } catch (IOException e1) {
//             System.err.println("Error handling client communication: " + e.getMessage());
//         }
                
//     }
//     } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//     }
    

//    }


    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }


    
}
