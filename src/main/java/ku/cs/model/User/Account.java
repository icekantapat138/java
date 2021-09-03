package ku.cs.model.User;


import java.io.File;
import java.io.IOException;

public class Account {
    private String username, password;
    private String x;


    public Account() {
        this.username =  "Admin";
        this.password =  "Admin1234" ;
    }

    public Account(String username, String password) {
        this.username =  username;
        this.password =  password;
    }


    public boolean checkUser(String user) {
        return username.equals(user);
    }

    public boolean checkPassword(String pw) {
        return password.equals(pw);
    }

    public void setPassword(String pw) {
        this.password = pw;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



}
