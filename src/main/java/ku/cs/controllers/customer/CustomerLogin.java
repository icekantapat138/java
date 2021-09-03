package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CustomerLogin {

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
            if (verifyUser() == true) {
                System.out.println("เข้าสำเร็จ");
                Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
                d.setTitle("Login Successful.");
                d.setContentText("Login Successful" +
                        "\r\n Welcome Customer.");
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

    public boolean verifyUser() throws IOException {
        File f = new File("C:/Administrator/project3/data/customer.csv");
        if(!f.exists()){
            f.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        Object[] Lines = br.lines().toArray();
        int i = 0;
        for (i = 3; i < Lines.length; i++) {
            String line = Lines[i].toString().trim();
            String[] data = line.split(",");
            System.out.println(data[2]);
            System.out.println(data[3]);
            if ((usernameText.getText().equals(data[2])) && (passwordField.getText().equals(data[3]))) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
