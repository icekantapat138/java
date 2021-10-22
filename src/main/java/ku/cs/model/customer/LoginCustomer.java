package ku.cs.model.customer;

import ku.cs.model.User.CustomerAccount;

public class LoginCustomer {
    private static CustomerAccount customerAccount;

    public LoginCustomer(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public static CustomerAccount getCustomerAccount() {
        return customerAccount;
    }
}
