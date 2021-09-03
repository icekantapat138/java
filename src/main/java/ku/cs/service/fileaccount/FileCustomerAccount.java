package ku.cs.service.fileaccount;

import javafx.fxml.FXML;
import ku.cs.model.User.Account;
import ku.cs.model.User.CustomerAccount;

import java.io.*;

public class FileCustomerAccount implements AccountData {

    @Override
    public CustomerAccount getCustomerData() {
        return null;
    }

    @Override
    public Account getAdminData() {
        return null;
    }

    @Override
    public void writDataSource(CustomerAccount acc) {

    }
}