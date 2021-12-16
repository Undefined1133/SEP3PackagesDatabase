import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main
{

  public static void main(String[] args)
      throws IOException, SQLException, ClassNotFoundException
  {


    ServerSocket serverSocket = new ServerSocket(4343, 10);
    while (true)
    {
      // Receiving
      Socket socket = serverSocket.accept();
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      //Received String
      String received = read(is);

      switch (received.charAt(0))
      {
        case '*':
          SendAllPackages(os, received.substring(1));
          socket.close();
          break;
        case '^':
          CheckLogin(os, received.substring(1));
          socket.close();
          break;
        case '@':
          CheckRegister(os, received.substring(1));
          socket.close();
          break;
        case '!':
          AddPackage(os, received.substring(1));
          socket.close();
          break;
        case '#':
          UpdatePackageInfo(os, received.substring(1));
          socket.close();
          break;
        case '&':
          DeletePackageById(os, received.substring(1));
          socket.close();
          break;
        case '%':
          SendPackageById(os, received.substring(1));
          socket.close();
          break;
        case '>':
          SendBoughtPackages(os);
          socket.close();
          break;
        case '<':
          AddBoughtPackage(os, received.substring(1));
          socket.close();
          break;
        case '0':
          DeleteBoughtPackageById(os, received.substring(1));
          socket.close();
          break;
        case '1':
          UpdateBoughtPackageInfo(os, received.substring(1));
          socket.close();
          break;
        case '2':
          SendBoughtPackageById(os, received.substring(1));
          socket.close();
          break;
        case '3':
          SendUserByUserName(os, received.substring(1));
        socket.close();
        break;
        case'4':SendAllUsers(os,received.substring(1));socket.close();break;
        case'5':SendUserById(os,received.substring(1));socket.close();break;
        case'6':UpdateRolesByAdmin(os,received.substring(1));socket.close();break;
        case'7':UpdateInfoByUser(os,received.substring(1));socket.close();break;
        default:
          DefaultResponse(os, received);
          socket.close();
      }

    }

  }

  //serverSocket.close();

  public static void DefaultResponse(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    System.out.println(
        "Server received: " + message + "\n" + "Nothing had been done!!!");

    //Sending
    String toSend = "Something went wrong, please try again";
    send(outputStream, toSend);
  }

  public static void AddBoughtPackage(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationBoughtPackages dbManipulationBoughtPackages = new DBManipulationBoughtPackages();
    Gson gson = new Gson();
    System.out.println("Server received: " + message + "\n");
    BoughtPackage boughtPackage = gson.fromJson(message, BoughtPackage.class);
    dbManipulationBoughtPackages.InsertingValue(boughtPackage.getPackageId(),
        boughtPackage.getUserId());
    System.out.println("Bought package added!");
    String toSend = "I got your package, thanks";
    send(outputStream, toSend);
  }

  public static void AddPackage(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationPackages dbManipulationPackages = new DBManipulationPackages();
    Gson gson = new Gson();
    System.out.println("Server received: " + message + "\n");
    System.out.println(message);
    Package packageSmall = gson.fromJson(message, Package.class);
    System.out.println(packageSmall);
    dbManipulationPackages
        .InsertingValue(packageSmall.getName(), packageSmall.getLocation(),
            packageSmall.getPrice(), packageSmall.getReview());
    System.out.println("Package added!!");
    //Sending
    String toSend = "I Got your package, thanks";
    send(outputStream, toSend);
  }
  public static void UpdateRolesByAdmin(OutputStream outputStream,String message)throws IOException, SQLException, ClassNotFoundException{
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    Gson gson = new Gson();
    System.out.println("Server received: " + message + "\n");
    System.out.println(message);
    User userReceived = gson.fromJson(message, User.class);
    System.out.println(userReceived);
    dbManipulationUsers
        .UpdateInfo(userReceived.getRole(), userReceived.getID());
    System.out.println("Role updated!!!");
    String toSend = dbManipulationUsers.getUserById(userReceived.getID());
    String substringJson = toSend.substring(1,toSend.length()-1);
    Gson gsonToSend = new Gson();
    User user = gson.fromJson(substringJson,User.class);
    //Sending
    String toSendUser = user.toString();
    send(outputStream, toSendUser);
  }
  public static void UpdateInfoByUser(OutputStream outputStream,String message)throws IOException, SQLException, ClassNotFoundException{
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    Gson gson = new Gson();
    System.out.println("Server received: " + message + "\n");
    System.out.println(message);
    User userReceived = gson.fromJson(message, User.class);
    System.out.println(userReceived);
    dbManipulationUsers
        .UpdateInfo(userReceived.getUsername(), userReceived.getPassword(),
            userReceived.getEmail(), userReceived.getID());
    System.out.println("User info updated!!!");
    String toSend = dbManipulationUsers.getUserById(userReceived.getID());
    String substringJson = toSend.substring(1,toSend.length()-1);
    Gson gsonToSend = new Gson();
    User user = gson.fromJson(substringJson,User.class);
    //Sending
    String toSendUser = user.toString();
    send(outputStream, toSendUser);
  }

  public static void SendBoughtPackages(OutputStream outputStream)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationBoughtPackages dbManipulationBoughtPackages = new DBManipulationBoughtPackages();
    String toSend = dbManipulationBoughtPackages.gettingValues();
    System.out.println("Package is being delivered!");
    //Sending
    send(outputStream, toSend);
  }

  public static void SendBoughtPackageById(OutputStream outputStream,
      String message) throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationBoughtPackages dbManipulationBoughtPackages = new DBManipulationBoughtPackages();
    System.out.println("Server received: " + message + "\n");

    String toSend = dbManipulationBoughtPackages
        .getBoughtPackageById(Integer.parseInt(message));
    System.out.println("Package is being delivered!");
    //Sending

    send(outputStream, toSend);
  }

  public static void SendPackageById(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationPackages dbManipulationPackages = new DBManipulationPackages();
    System.out.println("Server received: " + message + "\n");

    String toSend = dbManipulationPackages
        .getPackageById(Integer.parseInt(message));
    System.out.println("Package is being delivered!");
    //Sending

    send(outputStream, toSend);
  }
  public static void SendUserByUserName(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    System.out.println("Server received: " + message + "\n");
    System.out.println("Preparing to send user");
    String toSend = dbManipulationUsers.getUserByUsername(message);
    String substringJson = toSend.substring(1,toSend.length()-1);

    Gson gson = new Gson();
    User user = gson.fromJson(substringJson, User.class);
    System.out.println(user);
//    String sending = user.getID() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getRole() + " " + user.getEmail();
    System.out.println("User is being delivered");
    //Sending

    send(outputStream, user.toString());
  }
  public static void SendAllUsers(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    System.out.println("Server received: " + message);
    System.out.println("Sending packages to the client");

    String toSend = dbManipulationUsers.gettingValues();
    System.out.println(toSend);
    send(outputStream, toSend);
  }
  public static void SendUserById(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    System.out.println("Server received: " + message + "\n");

    String toSend = dbManipulationUsers.getUserById(Integer.parseInt(message));
    String substringJson = toSend.substring(1,toSend.length()-1);

    Gson gson = new Gson();
    User user = gson.fromJson(substringJson, User.class);
    System.out.println(user);
    //    String sending = user.getID() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getRole() + " " + user.getEmail();
    System.out.println("User is being delivered");
    //Sending

    send(outputStream, user.toString());
  }

  public static void DeletePackageById(OutputStream outputStream,
      String message) throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationPackages dbManipulationPackages = new DBManipulationPackages();
    System.out.println("Server received: " + message + "\n");

    int number = 0;
    number = Integer.parseInt(message);
    dbManipulationPackages.DeleteById(number);
    System.out.println("Package with id " + number + " is deleted!");
    String toSend = "Package with id " + number + " is deleted!";
    send(outputStream, toSend);
  }

  public static void DeleteBoughtPackageById(OutputStream outputStream,
      String message) throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationBoughtPackages dbManipulationBoughtPackages = new DBManipulationBoughtPackages();
    System.out.println("Server received: " + message + "\n");

    int number = 0;
    number = Integer.parseInt(message);
    dbManipulationBoughtPackages.DeleteById(number);
    System.out.println("Package with id " + number + " is deleted!");
    String toSend = "Package with id " + number + " is deleted!";
    send(outputStream, toSend);
  }

  public static void UpdatePackageInfo(OutputStream outputStream,
      String message) throws IOException, SQLException, ClassNotFoundException
  {
    Gson gson = new Gson();
    DBManipulationPackages dbManipulationPackages = new DBManipulationPackages();
    System.out.println("Server received: " + message + "\n");

    Package packageSmall = gson.fromJson(message, Package.class);
    dbManipulationPackages
        .UpdateInfo(packageSmall.getName(), packageSmall.getLocation(),
            packageSmall.getPrice(), packageSmall.getReview(),
            packageSmall.getID());
    System.out.println("Package info Updated!!");
    //Sending
    String toSend = "I Got your package, the info is updated!";
    send(outputStream, toSend);
  }

  public static void UpdateBoughtPackageInfo(OutputStream outputStream,
      String message) throws IOException, SQLException, ClassNotFoundException
  {
    Gson gson = new Gson();
    DBManipulationBoughtPackages dbManipulationBoughtPackages = new DBManipulationBoughtPackages();
    System.out.println("Server received: " + message + "\n");

    BoughtPackage packageSmall = gson.fromJson(message, BoughtPackage.class);
    dbManipulationBoughtPackages
        .UpdateInfo(packageSmall.getId(), packageSmall.getPackageId(),
            packageSmall.getUserId());
    System.out.println("Package info Updated!!");
    //Sending
    String toSend = "I Got your package, the info is updated!";
    send(outputStream, toSend);
  }

  public static void SendAllPackages(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationPackages dbManipulationPackages = new DBManipulationPackages();
    System.out.println("Server received: " + message);
    System.out.println("Sending packages to the client");

    String toSend = dbManipulationPackages.gettingValues();
    System.out.println(toSend);
    send(outputStream, toSend);
  }

  public static void CheckLogin(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    System.out.println("Server received: " + message);
    String[] usernameAndPassword = message.split(" ");
    System.out.println(usernameAndPassword[0] + " " + usernameAndPassword[1]);
    if (dbManipulationUsers
        .CheckingLogin(usernameAndPassword[0], usernameAndPassword[1]))
    {
      System.out.println("This user is registered successful log in");
      Gson gson = new Gson();
      String userFromDb = dbManipulationUsers.getUserByUsername(usernameAndPassword[0]).substring(1,dbManipulationUsers.getUserByUsername(usernameAndPassword[0]).length()-1);
      System.out.println(userFromDb);
      User user = gson.fromJson(userFromDb, User.class);
      String toSend =
          usernameAndPassword[0] + " " + usernameAndPassword[1] + " " + user.getRole();
      System.out.println(toSend);
      send(outputStream, toSend);
    }
    else
    {
      System.out.println("This user is not registered yet!");
      String toSend = "Not registered";
      send(outputStream, toSend);
    }
  }

  public static void CheckRegister(OutputStream outputStream, String message)
      throws IOException, SQLException, ClassNotFoundException
  {
    DBManipulationUsers dbManipulationUsers = new DBManipulationUsers();
    System.out.println("Server received: " + message);

    String[] usernameAndPassword = message.split(" ");
    if (dbManipulationUsers
        .CheckingRegister(usernameAndPassword[0], usernameAndPassword[1],
            usernameAndPassword[2]))
    {
      System.out.println("Successfully registered!");
      String toSend = usernameAndPassword[0] + " " + usernameAndPassword[1];
      send(outputStream, toSend);
    }
    else
      System.out.println("This user is already registered!");
    String toSendIfRegistered =
        usernameAndPassword[0] + " " + usernameAndPassword[1]
            + " is already registered";
    send(outputStream, toSendIfRegistered);
  }

  private static String read(InputStream inputStream) throws IOException
  {
    //translating input
    byte[] lenBytes = new byte[4];
    inputStream.read(lenBytes, 0, 4);
    int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) | (
        (lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
    byte[] receivedBytes = new byte[len];
    inputStream.read(receivedBytes, 0, len);
    String received = new String(receivedBytes, 0, len);
    return received;
  }

  private static void send(OutputStream outputStream, String toSend)
      throws IOException
  {
    byte[] toSendBytes = toSend.getBytes();
    int toSendLen = toSendBytes.length;
    byte[] toSendLenBytes = new byte[4];
    toSendLenBytes[0] = (byte) (toSendLen & 0xff);
    toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
    toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
    toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
    outputStream.write(toSendLenBytes);
    outputStream.write(toSendBytes);
  }
}

