import org.json.JSONObject;

public class Package
{
  public int ID;
  public String Name;
  public String Location;
  public int Price;
  public int Review;

  public Package(int ID, String name, String location, int price, int review)
  {
    this.ID = ID;
    this.Name = name;
    this.Location = location;
    this.Price = price;
    this.Review = review;
  }
  public Package(){

  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getName()
  {
    return Name;
  }

  public void setName(String name)
  {
    this.Name = name;
  }

  public String getLocation()
  {
    return Location;
  }

  public void setLocation(String location)
  {
    this.Location = location;
  }

  public int getPrice()
  {
    return Price;
  }

  public void setPrice(int price)
  {
    this.Price = price;
  }

  public int getReview()
  {
    return Review;
  }

  public void setReview(int review)
  {
    this.Review = review;
  }

  @Override public String toString()
  {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("Review", Review).put("Price", Price).put("Location", Location).put("Name",
        Name).put("ID",
        ID);
    return jsonObject.toString();
  }
}
