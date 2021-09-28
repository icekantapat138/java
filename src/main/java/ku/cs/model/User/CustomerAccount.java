package ku.cs.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerAccount extends Account {

    private String firstname, lastname, retypepassword;
    private String image;
    private String status;
    public String time;
    private ArrayList<CustomerAccount>  customerAccounts;

    public CustomerAccount() {
        customerAccounts = new ArrayList<>();
    }

    public CustomerAccount(String username, String password, String firstname, String lastname, String retypepassword, String image, String status, String time) {
        super(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.retypepassword = retypepassword;
        this.image = image;
        this.status = status;
        this.time = time;
    }

    public CustomerAccount(String username, String password) {
        super(username, password);
    }

    public void setStatusHaveStore() {
        this.status = "Have Store";
    }

    public void setStatusNotHaveStore() {
        this.status = "Not Have Store";
    }

    public void setDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd.MMM.yyyy kk:mm:ss");
        String time = formatTime.format(currentTime);
    }

    public String getTime() {
        return time;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void addCustomerAccount(CustomerAccount customerAccount) {
        customerAccounts.add(customerAccount);
    }

    public boolean searchUser(String user){
        return customerAccounts.toString().contains(user);
    }

    public boolean searchPw(String pw){
        return customerAccounts.toString().contains(pw);
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public String toString() {
        return "Customer" + "," +
                firstname + "," +
                lastname + "," +
                getUsername() + "," +
                getPassword() + "," +
                retypepassword + "," +
                image;
    }
}