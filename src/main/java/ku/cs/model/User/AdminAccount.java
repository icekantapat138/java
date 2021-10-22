package ku.cs.model.User;

public class AdminAccount {

    private String username;
    private String password;

    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkUsername(String username) {
        return this.username.equals(username);
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toCsv(){
        return username + "," + password;
    }

    public String toString() {
        return username + "," + password;
    }


}
