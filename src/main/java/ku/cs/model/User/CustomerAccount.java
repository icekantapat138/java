package ku.cs.model.User;

import ku.cs.service.account.CustomerDataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class CustomerAccount {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String repassword;
    private String image;
    private String status;
    private String date;
    private String lowproduct;


    public CustomerAccount(String username, String password, String firstname, String lastname, String repassword, String image, String status, String date, String lowproduct){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.repassword = repassword;
        this.image = image;
        this.status = status;
        this.date = date;
        this.lowproduct = lowproduct;
    }

    public String getLowproduct() {
        return lowproduct;
    }

    public void setLowproduct(String lowproduct) {
        this.lowproduct = lowproduct;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm a");
        this.date = formatTime.format(currentTime);
    }

    public boolean checkUser(String username){
        return this.username.equals(username);
    }

    public boolean checkPw(String password){
        return this.password.equals(password);
    }

    public boolean checkStatus(String status){
        return this.status.equals(status);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toCsv() {
        return username + "," + password + "," + firstname +
                          "," + lastname + "," + repassword +
                          "," + image + "," + status +
                          "," + date + "," + lowproduct;
    }

    public String toString() {
        return firstname + "," + lastname + "," + username;
    }


}
