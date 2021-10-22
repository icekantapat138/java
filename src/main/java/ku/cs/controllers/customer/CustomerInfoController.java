package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.LoginCustomer;
import ku.cs.service.account.CustomerDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable {

    @FXML private TextField fistnameText;
    @FXML private TextField lasnameText;
    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repwField;
    @FXML private TextField userImageTxt;
    @FXML private ImageView userImage;

    private CustomerAccount customerAccount;
    private CustomerDataSource customerFileDataSource;
    private CustomerAccountList customerAccountList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerAccount = LoginCustomer.getCustomerAccount();
        fistnameText.setText(customerAccount.getFirstname());
        lasnameText.setText(customerAccount.getLastname());
        usernameText.setText(customerAccount.getUsername());
        passwordField.setText(customerAccount.getPassword());
        repwField.setText(customerAccount.getRepassword());
        userImage.setImage(new Image("file:" +customerAccount.getImage(), true));
        userImageTxt.setText(customerAccount.getImage());

        customerFileDataSource = new CustomerDataSource("data", "customer.csv");
        customerAccountList = customerFileDataSource.readData();
    }

    @FXML
    void browseIBtn(ActionEvent event){
        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                userImage.setImage(new Image(target.toUri().toString()));
                userImageTxt.setText(destDir + "/" + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("shop");
    }

    @FXML
    void saveBtn(ActionEvent event) {
        if(!(passwordField.getText().equals(repwField.getText()))){
            Dialog err = new Alert(Alert.AlertType.ERROR);
            err.setTitle("Password and RePassword not match!!");
            err.setContentText("Please Enter Password and RePassword to match!!");
            err.showAndWait();
        }else {
            CustomerAccount customerAccount1 = customerAccountList.searchUser(customerAccount.getUsername());
            customerAccount1.setUsername(usernameText.getText());
            customerAccount1.setFirstname(fistnameText.getText());
            customerAccount1.setLastname(lasnameText.getText());
            customerAccount1.setPassword(passwordField.getText());
            customerAccount1.setRepassword(repwField.getText());
            customerAccount1.setImage(userImageTxt.getText());
            customerFileDataSource.writeData(customerAccountList);

            Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
            d.setTitle("Login Successful.");
            d.setContentText("Login Successful" + "\r\n Welcome Customer.");
            d.showAndWait();
        }
        System.out.println(passwordField.getText());
        System.out.println(repwField.getText());
    }
}
