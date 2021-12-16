import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManipulationPackages
{
  Connection connection = null;
  Statement statement = null;

  public void createTablePackages()
  {
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    try
    {
      String query = "create table packages(id SERIAL primary key,name varchar(200),location varchar(200),price integer,review integer)";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Table created successfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void InsertingValue(String name, String location, int price,
      int review)
  {

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();

    try
    {
      String query =
          "insert into packages(name,location,price,review) values('" + name
              + "','" + location + "','" + price + "','" + review + "')";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Value inserted successfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void DeleteByName(String name)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query = "delete from packages where name='" + name + "'";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Package deleted succesfully");
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
      String query = "delete from packages where id='" + id + "'";
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Package deleted succesfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void UpdateInfo(String newName, String newLocation, int newPrice,
      int newReview, int id)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query =
          "update packages set name = '" + newName + "', location = '"
              + newLocation + "', price='" + newPrice + "'," + "review = '"
              + newReview + "' where id=" + id;
      statement = connection.createStatement();
      statement.executeUpdate(query);
      System.out.println("Info updated successfully");
      connection.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String gettingValues() throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> name = new ArrayList<String>();
    List<String> location = new ArrayList<String>();
    List<String> price = new ArrayList<String>();
    List<String> review = new ArrayList<String>();
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    JSONObject object = new JSONObject();
    String pustoi = "[";
    try
    {
      String query = "Select * from packages";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        name.add(rs.getString(2));
        location.add(rs.getString(3));
        price.add(rs.getString(4));
        review.add(rs.getString(5));
      }

      for (int i = 0; i < id.size(); i++)
      {
        Package aPackage = new Package(Integer.parseInt(id.get(i)), name.get(i),
            location.get(i), Integer.parseInt(price.get(i)), Integer.parseInt(review.get(i)));

        pustoi += aPackage.toString() + ",";

      }
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

  public String getPackageById(int packageID) throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> name = new ArrayList<String>();
    List<String> location = new ArrayList<String>();
    List<String> price = new ArrayList<String>();
    List<String> review = new ArrayList<String>();
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    JSONObject object = new JSONObject();
    String pustoi = "[";
    try
    {
      String query = "Select * from packages where id=" + packageID;
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        name.add(rs.getString(2));
        location.add(rs.getString(3));
        price.add(rs.getString(4));
        review.add(rs.getString(5));
      }
      Package aPackage = new Package(Integer.parseInt(id.get(0)), name.get(0),
          location.get(0), Integer.parseInt(price.get(0)), Integer.parseInt(review.get(0)));
      pustoi += aPackage.toString() + ",";

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
  public void DropTable(){
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();
    try
    {
      String query =
          "DROP TABLE packages";
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
}

