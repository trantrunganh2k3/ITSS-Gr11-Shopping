package Model;

public class User {
    private String username;
    private String password;
    private String name;
    private String gender;
    private String email;
    private String address;
    private int groupID;
    private String status;

    public User(){}

    public User(String username, String password, String name, String gender, String email, String address, int groupID, String status){
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.groupID = groupID;
        this.status = status;
    }

    public User(String username, String password, String gender, String email){
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.status = "Active";
    }

    public User(String username, String name, String gender, String email, String address, String status){
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.status = status;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
