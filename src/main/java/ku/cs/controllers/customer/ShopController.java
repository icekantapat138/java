package ku.cs.controllers.customer;


import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ShopController {

    @FXML
    void logoutBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void marketplaceBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("marketplace");
    }

    @FXML
    void csinfoBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customerinfo");
    }

}
