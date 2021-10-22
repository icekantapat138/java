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
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.service.account.CustomerDataSource;
import ku.cs.service.account.AccountDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class CustomerCreateController {

    @FXML private TextField firstnameText;
    @FXML private TextField lastnameText;
    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField retypepwField;
    @FXML private ImageView image1;
    @FXML private TextField imageTextField;

    private AccountDataSource<CustomerAccountList> account;
    private CustomerAccount selectedCustomer;
    private CustomerDataSource customerDataSource;
    private CustomerAccountList customerAccountList;

    @FXML
    public void initialize() {
        System.out.println(System.getProperty("user.dir"));
        image1.setImage(new Image(getClass().getResource("/images/defaultprofile.jpg").toExternalForm()));
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customerlogin");
    }

    @FXML
    void createBtn(ActionEvent event) throws IOException {
        customerDataSource  = new CustomerDataSource("data", "customer.csv");
        customerAccountList = customerDataSource.readData();
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
                if (customerAccountList.checkUsernameAccount(usernameText.getText())) {
                    System.out.println("Pass");
                    account = new CustomerDataSource();
                    CustomerAccountList customerAccountList = account.readData();
                    CustomerAccount css = new CustomerAccount(
                            usernameText.getText(),
                            passwordField.getText(),
                            firstnameText.getText(),
                            lastnameText.getText(),
                            retypepwField.getText(),
                            imageTextField.getText(),
                            "Not Have Store",
                            "00/00/0000 @00:00 a.m.",
                            "0");
                    selectedCustomer = css;
                    System.out.println(css.toCsv());

                    customerAccountList.addCustomerAccount(css);
                    account.writeData(customerAccountList);


                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                    confirm.setTitle("CONFIRMATION");
                    confirm.setContentText("Create Account Successful.");
                    confirm.showAndWait();
                    clearAllField();
                    FXRouter.goTo("customerlogin");
                }else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("ERROR");
                    error.setContentText("Username Has Use");
                    error.showAndWait();
                }
            }
        }
    }

    private void clearAllField() {
        firstnameText.clear();
        lastnameText.clear();
        usernameText.clear();
        passwordField.clear();
        retypepwField.clear();
        imageTextField.setText("");
        image1.setImage(image1.getImage());
    }

    @FXML
    public void browseImageBtn(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            try {
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                // SET NEW FILE PATH TO IMAGE
                image1.setImage(new Image(target.toUri().toString()));
                imageTextField.setText(destDir + "/" + filename);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
