import org.json.JSONObject;

public class BoughtPackage
{
  public int id;
  public int packageId;
  public int userId;

  public BoughtPackage(int id, int packageId, int userId)
  {
    this.id = id;
    this.packageId = packageId;
    this.userId = userId;
  }
  public BoughtPackage(){

  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getPackageId()
  {
    return packageId;
  }

  public void setPackageId(int packageId)
  {
    this.packageId = packageId;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  @Override public String toString()
  {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", id).put("packageId",packageId).put("userId",userId);
    return jsonObject.toString();
  }
}
