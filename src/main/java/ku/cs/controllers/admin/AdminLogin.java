package ku.cs.controllers.admin;


import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.model.User.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminLogin {

    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;

    private Account admin;

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

    public boolean verifyUser() throws IOException {
        File f = new File("C:/Administrator/project3/data/admin.csv");
        if(!f.exists()){
            f.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        Object[] Lines = br.lines().toArray();
        int i = 0;
        for (i = 1; i < Lines.length; i++) {
            String line = Lines[i].toString().trim();
            String[] data = line.split(",");
            System.out.println(data[0]);
            System.out.println(data[1]);
            if ((usernameText.getText().equals(data[0])) && (passwordField.getText().equals(data[1]))) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @FXML
    void loginBtn(ActionEvent event) throws IOException{
        if (usernameText.getText().equals("") || passwordField.getText().equals("")) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Notification");
            error.setContentText("Please complete all fields.");
            error.setHeaderText(null);
            error.showAndWait();
        } else {
            if (verifyUser() == true){
                System.out.println("เข้าสำเร็จ");
                Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
                d.setTitle("Login Successful.");
                d.setContentText("Login Successful" +
                        "\r\n Welcome Admin.");
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

    public void setAdmin(Account admin) {
        this.admin = admin;
    }
}
