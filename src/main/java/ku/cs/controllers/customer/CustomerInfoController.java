package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerInfoController {

    @FXML private TextField fistnameText;
    @FXML private TextField lasnameText;
    @FXML private TextField usernameText;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repwField;

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("shop");
    }

    @FXML
    void saveBtn(ActionEvent event) {
        System.exit(0);
    }

}
