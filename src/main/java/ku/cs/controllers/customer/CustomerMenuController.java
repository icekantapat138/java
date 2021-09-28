package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerMenuController {

    @FXML
    void storeBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("storeaddproduct");
    }

    @FXML
    void logoutBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

    @FXML
    void shopBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("shop");
    }

}
