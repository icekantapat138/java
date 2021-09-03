package ku.cs.model.User;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerAccount extends Account {

    private String firstname, lastname, retypepassword;
    private String time;
    private ArrayList<CustomerAccount> customerAccounts;

    public CustomerAccount(String username, String password, String firstname, String lastname, String retypepassword, String time) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.retypepassword = retypepassword;
        this.time = time;
    }

    public void setDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd.MMM.yyyy kk:mm:ss");
        String time = formatTime.format(currentTime);
        this.time = time;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(ArrayList<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    public boolean searchUser(String user) {
        return customerAccounts.toString().contains(user);
    }

    public boolean searchPassword(String pw) {
        return customerAccounts.toString().contains(pw);
    }

    @Override
    public String toString() {
        return "Username : " + super.getUsername() + "," + "Password : " + super.getPassword()
                + "," + "Firstname : " + firstname + "," + "Lastname : " + lastname
                + "," + "Date : " + time;
    }
}