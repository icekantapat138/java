package ku.cs.controllers.admin;


import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.model.User.AdminAccount;
import ku.cs.model.User.AdminAccountList;
import ku.cs.service.account.AdminDataSource;
import ku.cs.service.account.AccountDataSource;

import java.io.IOException;

public class AdminLoginController {

    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;

    private AdminAccount admin;
    private AccountDataSource<AdminAccountList> dataSource;

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

    @FXML
    void loginBtn(ActionEvent event) throws IOException{
        dataSource = new AdminDataSource("data", "admin.csv");
        AdminAccountList adminAccountList = dataSource.readData();
        if (usernameText.getText().equals("") || passwordField.getText().equals("")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Notification");
            error.setContentText("Please complete all fields.");
            error.setHeaderText(null);
            error.showAndWait();
        } else {
            if (adminAccountList.checkUserandPw(usernameText.getText(),passwordField.getText())){
                System.out.println("เข้าสำเร็จ");
                Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
                d.setTitle("Login Successful.");
                d.setContentText("Login Successful" + "\r\n Welcome Admin.");
                d.showAndWait();
                FXRouter.goTo("admincontroller");
            }
            else{
                System.err.println("เข้าไม่ได้");
                Dialog d = new Alert(Alert.AlertType.ERROR);
                d.setTitle("Login Unsuccessful.");
                d.setContentText("Enter Username Or Password.");
                d.showAndWait();
            }
        }

    }

    public void setAdmin(AdminAccount admin) {
        this.admin = admin;
    }
}
