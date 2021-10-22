package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.LoginCustomer;
import ku.cs.service.account.CustomerDataSource;

import java.io.IOException;

public class CustomerMenuController {

    private CustomerDataSource css;
    private CustomerAccount customerAccount;
    private CustomerDataSource customerFileDataSource;
    private CustomerAccountList customerAccountList;

    @FXML
    public void initialize() {
        customerAccount = LoginCustomer.getCustomerAccount();
    }

    @FXML
    void storeBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("createstore");
    }

    @FXML
    void logoutBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

    @FXML
    void shopBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("shop");
    }

}
