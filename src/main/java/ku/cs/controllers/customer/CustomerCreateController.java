package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.model.User.CustomerAccount;
import ku.cs.service.fileaccount.CustomerFileAccountDataSource;
import ku.cs.service.fileaccount.FileAccountDataSource;

import java.io.File;
import java.io.IOException;

public class CustomerCreateController {

    @FXML private TextField firstnameText;
    @FXML private TextField lastnameText;
    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField retypepwField;
    @FXML private FileChooser fileChooser;
    @FXML private ImageView image1;
    @FXML private TextField imageTextField;

    private CustomerAccount customerAccount;
    private CustomerFileAccountDataSource account;
    private FileAccountDataSource accounts;

    @FXML
    public void initialize() {
        customerAccount = new CustomerAccount();
        account = new CustomerFileAccountDataSource("data", "customer.csv");

        String img1 = getClass().getResource("/image/defaultprofile.jpg").toExternalForm();
        image1.setImage(new Image(img1));
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
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
        if ((firstnameText.getText().equals("") || (lastnameText.getText().equals("") || (usernameText.getText().equals("") || (passwordField.getText().equals("")) || (retypepwField.getText().equals("") || (imageTextField.getText().equals(""))))))){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Please Complete All Field.");
            alert1.setContentText("Please!! Enter All Field To Create Account.");
            alert1.showAndWait();
        }else {
            if (!(passwordField.getText().equals(retypepwField.getText()))){
                System.out.println("Not Pass");
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Re-Password Not Match Password");
                alert1.setContentText("Password And Re-Password Not Match.");
                alert1.showAndWait();
            }else {
                System.out.println("Pass");

                CustomerAccount css = new CustomerAccount(
                        usernameText.getText(),
                        passwordField.getText(),
                        firstnameText.getText(),
                        lastnameText.getText(),
                        retypepwField.getText(),
                        imageTextField.getText(),
                        "Not Have Store",
                        "No Login Yet.");
                System.out.println(css.toString());

                customerAccount.addCustomerAccount(css);
                account.writeData(css);

                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("CONFIRMATION");
                confirm.setContentText("Create Account Successful.");
                confirm.showAndWait();
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
