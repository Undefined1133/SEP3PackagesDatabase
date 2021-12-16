import org.json.JSONObject;

public class User
{
  public int id;
  public String username;
  public String password;
  public String Email;
  public String role;

  public User(int id, String username, String password, String email,
      String role)
  {
    this.id = id;
    this.username = username;
    this.password = password;
    this.Email = email;
    this.role = role;
  }
  public User(){

  }

  public int getID()
  {
    return id;
  }

  public void setID(int id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return Email;
  }

  public void setEmail(String email)
  {
    this.Email = email;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  @Override public String toString()
  {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("password", password).put("id", id).put("username", username).put("Email",
        Email).put("role", role);
    return jsonObject.toString();
  }
}
