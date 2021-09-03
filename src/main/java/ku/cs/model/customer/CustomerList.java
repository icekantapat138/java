package ku.cs.model.customer;

import ku.cs.model.User.CustomerAccount;

import java.util.ArrayList;

public class CustomerList {

    private ArrayList<CustomerAccount> customer;

    public CustomerList() {
        customer = new ArrayList<>();
    }

    public ArrayList<CustomerAccount> getAllCustomer() {
        return customer;
    }

    public int countCustomer() {
        return customer.size();
    }

    public void addCustomer(CustomerAccount cs) {
        customer.add(cs);
    }


}