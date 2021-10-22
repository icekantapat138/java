package ku.cs.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.model.User.AdminAccount;
import ku.cs.model.User.AdminAccountList;
import ku.cs.service.account.AdminDataSource;

import java.io.*;

public class AdminSettingController {

    @FXML private TextField userField;
    @FXML private PasswordField pwField;
    @FXML private Button addBtn;
    @FXML private ListView<AdminAccount> adminView;

    private AdminDataSource adminFileDataSource;
    private AdminAccountList adminAccountList;

    @FXML
    public void initialize() {
        adminFileDataSource = new AdminDataSource("data", "admin.csv");
        adminAccountList = adminFileDataSource.readData();
        showAdminData();
        handleSelectedListView();
        clearSelectedAdmin();
    }

    private void clearSelectedAdmin() {
        userField.clear();
        pwField.clear();
    }

    private void handleSelectedListView() {
        adminView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AdminAccount>() {
            @Override
            public void changed(ObservableValue<? extends AdminAccount> observableValue, AdminAccount adminAccount, AdminAccount t1) {
                System.out.println(t1 + " is selected.");
                showSelectedAdminUser(t1);
            }
        });
    }

    private void showSelectedAdminUser(AdminAccount adminAccount) {
        userField.setText(adminAccount.getUsername());
        pwField.setText(adminAccount.getPassword());
    }

    private void showAdminData() {
        adminView.getItems().addAll(adminAccountList.getAllAdminAccount());
        adminView.refresh();
    }

    @FXML
    void handleAddButton(ActionEvent event) throws IOException {
        if((userField.getText() == null) || (pwField.getText() == null)){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Notification");
            error.setContentText("Please complete all fields.");
            error.setHeaderText(null);
            error.showAndWait();
        }else {
            AdminAccount adminAccount2 = adminAccountList.searchUser(userField.getText());
            adminAccount2.setUsername(userField.getText());
            adminAccount2.setPassword(pwField.getText());
            adminFileDataSource.writeData(adminAccountList);
            Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
            d.setTitle("Password Has Been Changed.");
            d.showAndWait();
        }
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException{
        FXRouter.goTo("admincontroller");
    }
}


