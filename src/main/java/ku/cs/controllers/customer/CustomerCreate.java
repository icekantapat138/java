package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.model.User.Account;
import ku.cs.model.User.CustomerAccount;
import ku.cs.service.fileaccount.AccountData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerCreate {

    @FXML private TextField firstnameText;
    @FXML private TextField lastnameText;
    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField retypepwField;
    @FXML private FileChooser fileChooser;
    @FXML private ImageView image1;
    @FXML private TextField imageTextField;

    private Account admin;
    private CustomerAccount customerAcc;
    private AccountData accountData;
    private int check;

    @FXML
    public void initialize() {
        String img1 = getClass().getResource("/image/defaultprofile.jpg").toExternalForm();
        image1.setImage(new Image(img1));
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customerlogin");
    }

    @FXML
    public void browseimageBtn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            image1.setImage(image);
            imageTextField.setText(file.getAbsolutePath());
        }


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG file", "*.PNG")
                , new FileChooser.ExtensionFilter("png file", "*.png")
                , new FileChooser.ExtensionFilter("JPG file", "*.JPG")
                , new FileChooser.ExtensionFilter("jpg file", "*.jpg")
        );

    }

    @FXML
    void createBtn(ActionEvent event) throws IOException {
        String fn = firstnameText.getText();
        String ln = lastnameText.getText();
        String un = usernameText.getText();
        String pw = passwordField.getText();
        String rpw = retypepwField.getText();
        String img = imageTextField.getText();

        if ((firstnameText.getText().equals("") || (lastnameText.getText().equals("") || (usernameText.getText().equals("") || (passwordField.getText().equals("")) || (retypepwField.getText().equals("") || (imageTextField.getText().equals(""))))))){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Please Complete All Field.");
            alert1.setContentText("Please!! Enter All Field To Create Account.");
            alert1.showAndWait();
        }else {
            if (!(passwordField.getText().equals(retypepwField.getText()))){
                System.out.println(pw.equals(rpw));
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Re-Password Not Match Password");
                alert1.setContentText("Password And Re-Password Not Match.");
                alert1.showAndWait();
            }else {
                System.out.println(pw.equals(rpw));
                File file = new File("C:/Administrator/project3/data/customer.csv");
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fw);
                bufferedWriter.write("\r\n" + fn + "," + ln + "," + un + "," + pw + "," + rpw + "," + img);
                bufferedWriter.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setContentText("Create Account Successful.");
                alert.showAndWait();
                clearAllField();
                FXRouter.goTo("customerlogin");
            }
        }
    }

    private void clearAllField() {
        firstnameText.clear();
        lastnameText.clear();
        usernameText.clear();
        passwordField.clear();
        retypepwField.clear();
        imageTextField.setText("/image/defaultprofile.jpg");
        image1.setImage(image1.getImage());
    }
}
