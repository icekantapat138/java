package ku.cs.model.User;

import java.text.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomerAccountList {

    private ArrayList<CustomerAccount> customerList;

    public CustomerAccountList() {
        customerList = new ArrayList<>();
    }

    public void addCustomerAccount(CustomerAccount css){
        customerList.add(css);
    }

    public ArrayList<CustomerAccount> getAllCustomer() {
        return customerList;
    }

    public int countCustomer() {
        return customerList.size();
    }

    public CustomerAccount searchUser(String username){
        for(CustomerAccount css : customerList){
            if(css.checkUser(username)){
                return css;
            }
        }
        return null;
    }

    public CustomerAccount checkStore(String status){
        for (CustomerAccount css : customerList){
            if(css.checkStatus(status)){
                return css;
            }
        }
        return null;
    }

    public boolean checkUsernameAccount(String username){
        for (CustomerAccount css : customerList) {
            if (css.checkUser(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkUserandPw(String username, String password){
        CustomerAccount css = searchUser(username);
        if(css.checkUser(username) && css.checkPw(password)){
            return true;
        }
        return false;
    }

    public boolean checkStatusHaveStore(String status) {
        CustomerAccount css = checkStore(status);
        if(css.checkStatus("Not Have Store")){
            return true;
        }
        return false;
    }

    public void sort(Comparator comparator){
        Collections.sort(customerList, comparator);
    }

    public String toCsv() {
        String result = "";
        for (CustomerAccount customerAccount : this.customerList) {
            result += customerAccount.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "CustomerAccountListModelClass{" +
                "customerList=" + customerList +
                '}';
    }
}
