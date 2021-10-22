package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.LoginCustomer;
import ku.cs.service.account.CustomerDataSource;
import ku.cs.service.account.AccountDataSource;

import java.io.IOException;
import java.util.Comparator;

public class CustomerLoginController {

    private CustomerAccountList customerAccountList;
    private CustomerAccount customerAccount;
    private AccountDataSource<CustomerAccountList> dataSource;
    private LoginCustomer loginCustomer;

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
        dataSource = new CustomerDataSource("data", "customer.csv");
        CustomerAccountList css = dataSource.readData();

        System.out.println(css.toString());
        if ((usernameText.getText() == null) || (passwordField.getText() == null)){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Notification");
            error.setContentText("Please complete all fields.");
            error.setHeaderText(null);
            error.showAndWait();
            System.out.println("กรุณาใส่ให้ครบทุกช่อง!!");
        } else {
            if (css.checkUserandPw(usernameText.getText(),passwordField.getText())) {
                System.out.println("เข้าสำเร็จ");
                CustomerAccount css2 = css.searchUser(usernameText.getText());
                css2.setDate();
                Comparator<CustomerAccount> customerAccountComparator = new Comparator<CustomerAccount>() {
                    @Override
                    public int compare(CustomerAccount o1, CustomerAccount o2) {
                        if (o1.getDate().compareTo(o2.getDate()) < 0) return 1;
                        if (o1.getDate().compareTo(o2.getDate()) > 0) return -1;
                        return 0;
                    }
                };
                css.sort(customerAccountComparator);
                dataSource.writeData(css);
                Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
                d.setTitle("Login Successful.");
                d.setContentText("Login Successful" + "\r\n Welcome Customer.");
                d.showAndWait();
                loginCustomer = new LoginCustomer(css2);
                FXRouter.goTo("customercontroller");
            }else {
                Dialog notPass = new Alert(Alert.AlertType.ERROR);
                notPass.setTitle("Login UnSuccessful.");
                notPass.setContentText("Login UnSuccessful" + "\r\n Please Try Again");
                notPass.showAndWait();
            }
        }

        System.out.println(usernameText.getText());
        System.out.println(passwordField.getText());
    }
}
