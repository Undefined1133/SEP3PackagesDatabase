import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManipulationUsers
{
  Connection connection = null;
  Statement statement = null;

  public void createTableUsers()
  {
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    try
    {
      String query = "create table users(id SERIAL primary key,username varchar(200),password varchar(200),email varchar(200),role varchar(200))";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Finished");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public boolean CheckingLogin(String username,String password)
  {
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    try
    {
      String query = "Select * from users where username='" + username
          + "' and password= '" + password + "'";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      int count = 0;
      while (rs.next())
      {
        count++;
      }
      connection.close();
      if (count == 0)
      {
        return false;
      }
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return false;
  }
  public boolean CheckingRegister(String username,String password,String email)
  {
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    try
    {
      String query = "Select * from users where email='" + email + "'";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      int count = 0;
      while (rs.next())
      {
        count++;
      }
      if (count>=1)
      {
        return false;
      }
      else
        InsertingValue(username,password,email);
      connection.close();
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return false;
  }
  public String gettingValues() throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> username = new ArrayList<String>();
    List<String> password = new ArrayList<String>();
    List<String> email = new ArrayList<String>();
    List<String> role = new ArrayList<String>();
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    JSONObject object = new JSONObject();
    String pustoi = "[";
    try
    {
      String query = "Select * from users";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        username.add(rs.getString(2));
        password.add(rs.getString(3));
        email.add(rs.getString(4));
        role.add(rs.getString(5));
      }

      for (int i = 0; i < id.size(); i++)
      {
        User aUser = new User(Integer.parseInt(id.get(i)),username.get(i),password.get(i),email.get(i),role.get(i));

        pustoi += aUser.toString() + ",";

      }
      pustoi = pustoi.substring(0, pustoi.length() - 1) + "]";
      connection.close();
      return pustoi;
    }

    catch (Exception e)
    {
      e.printStackTrace();
    }

    return pustoi;
  }

  public void InsertingValue(String username, String password,String email)
  {

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();

    try
    {
      String query =
          "insert into users(username,password,email,role) values('" + username + "','"
              + password + "','"+ email+ "','user')";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Value inserted succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void InsertingValueAdmin(String username, String password,String email)
  {

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();

    try
    {
      String query =
          "insert into users(username,password,email,role) values('" + username + "','"
              + password + "','"+ email+ "','admin')";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Value inserted succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void DeleteByUsername(String username)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query = "delete from users where username='" + username + "'";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("User deleted succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void DeleteById(int id)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query = "delete from users where id=" + id;
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("User deleted succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void UpdateInfo(String role, int id)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query =
          "update users set role='" +role
      +"' where id=" + id;
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("User info updated succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void UpdateInfo(String username,String password,String email, int id)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query =
          "update users set username='" + username
              +"',password = '" + password + "',email ='" + email +"' where id=" + id;
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("User info updated succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public void DropTable(){
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();
    try
    {
      String query =
          "DROP TABLE users";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Table has been dropped");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public String getUserByUsername(String name) throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> username = new ArrayList<String>();
    List<String> password = new ArrayList<String>();
    List<String> email = new ArrayList<String>();
    List<String> role = new ArrayList<String>();
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    JSONObject object = new JSONObject();
    String pustoi = "[";
    try
    {
      String query = "Select * from users where username='" + name +"'";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        username.add(rs.getString(2));
        password.add(rs.getString(3));
        email.add(rs.getString(4));
        role.add(rs.getString(5));
      }
      User user = new User(Integer.parseInt(id.get(0)), username.get(0),
          password.get(0), email.get(0), role.get(0));
      pustoi += user.toString() + ",";

      pustoi = pustoi.substring(0, pustoi.length() - 1) + "]";
      connection.close();
      return pustoi;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return pustoi;
  }
  public String getUserById(int ID) throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> username = new ArrayList<String>();
    List<String> password = new ArrayList<String>();
    List<String> email = new ArrayList<String>();
    List<String> role = new ArrayList<String>();
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    JSONObject object = new JSONObject();
    String pustoi = "[";
    try
    {
      String query = "Select * from users where id=" + ID;
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        username.add(rs.getString(2));
        password.add(rs.getString(3));
        email.add(rs.getString(4));
        role.add(rs.getString(5));
      }
      User user = new User(Integer.parseInt(id.get(0)), username.get(0),
          password.get(0), email.get(0), role.get(0));
      pustoi += user.toString() + ",";

      pustoi = pustoi.substring(0, pustoi.length() - 1) + "]";
      connection.close();
      return pustoi;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    connection.close();
    return pustoi;
  }

}
