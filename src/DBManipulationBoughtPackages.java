import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManipulationBoughtPackages
{
  Connection connection = null;
  Statement statement = null;
  public void createTableBoughtPackages()
  {
    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    try
    {
      String query = "create table boughtPackages(id SERIAL primary key,packageId INTEGER REFERENCES packages(id),userId INTEGER REFERENCES users(id))";
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
  public String gettingValues() throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> packageId = new ArrayList<String>();
    List<String> userId = new ArrayList<String>();

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;
    String pustoi = "[";
    try
    {
      String query = "Select * from boughtPackages";
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        packageId.add(rs.getString(2));
        userId.add(rs.getString(3));
      }

      for (int i = 0; i < id.size(); i++)
      {
BoughtPackage boughtPackage = new BoughtPackage(Integer.parseInt(id.get(i)),Integer.parseInt(packageId.get(i)),Integer.parseInt(userId.get(i)));
        pustoi += boughtPackage.toString()+ ",";

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
    return  pustoi;
  }
  public void InsertingValue( int packageId, int userId)
  {

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();

    try
    {
      String query =
          "insert into boughtPackages(packageId,userId) values('" + packageId + "','" + userId+"')";
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
  public void DeleteById(int id)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query = "delete from boughtPackages where id='" + id + "'";
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
  public void UpdateInfo(int id, int packageId,int userId)
  {
    DBConnection dbConnection = new DBConnection();

    connection = dbConnection.getConnection();

    try
    {
      String query =
          "update boughtPackages set packageId = '"
              + packageId + "', userId='" + userId+"' where id="+id;
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
  public String getBoughtPackageById(int Id) throws SQLException
  {
    List<String> id = new ArrayList<String>();
    List<String> packageId = new ArrayList<String>();
    List<String> userId = new ArrayList<String>();

    DBConnection dbConnection = new DBConnection();
    connection = dbConnection.getConnection();
    ResultSet rs = null;

    String pustoi = "";
    try
    {
      String query = "Select * from boughtPackages where id=" + Id;
      statement = connection.createStatement();
      rs = statement.executeQuery(query);
      while (rs.next())
      {
        id.add(rs.getString(1));
        packageId.add(rs.getString(2));
        userId.add(rs.getString(3));

      }

      pustoi += id.get(0) + " " + packageId.get(0) + " " + userId.get(0);
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
          "DROP TABLE boughtpackages";
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
