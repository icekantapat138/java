package ku.cs.service.fileaccount;

import ku.cs.model.User.Account;
import ku.cs.model.User.CustomerAccount;

public interface AccountData {

    CustomerAccount getCustomerData();
    Account getAdminData();

    void writDataSource(CustomerAccount acc);
}

