package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.model.User.CustomerAccount;
import ku.cs.service.fileaccount.CustomerFileAccountDataSource;
import ku.cs.service.fileaccount.FileAccountDataSource;

import java.io.IOException;

public class CustomerLoginController {

    private CustomerAccount customerAccount;
    private FileAccountDataSource dataSource;

    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordField;

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

    @FXML
    void createBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customercreate");
    }

    @FXML
    void loginBtn(ActionEvent event) throws IOException {
        if (usernameText.getText().equals("") || passwordField.getText().equals("")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Notification");
            error.setContentText("Please complete all fields.");
            error.setHeaderText(null);
            error.showAndWait();
        } else {
            for (CustomerAccount customer : customerAccount.getCustomerAccounts()){
                if (customer.checkUser(usernameText.getText()) && customer.checkPw(passwordField.getText())) {
                    customer.setDate();

                    dataSource = new CustomerFileAccountDataSource("data", "customer.csv");
                    dataSource.writeData(customerAccount);
                    System.out.println(customer.getUsername() + "," + customer.getTime());
                    System.out.println("เข้าสำเร็จ");

                    Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
                    d.setTitle("Login Successful.");
                    d.setContentText("Login Successful" + "\r\n Welcome Customer.");
                    d.showAndWait();
                    FXRouter.goTo("customercontroller");
                } else {
                    System.err.println("เข้าไม่ได้");
                    Dialog d = new Alert(Alert.AlertType.ERROR);
                    d.setTitle("Login Unsuccessful.");
                    d.setContentText("Enter Username Or Password.");
                    d.showAndWait();
                }
            }
        }


    }
}
