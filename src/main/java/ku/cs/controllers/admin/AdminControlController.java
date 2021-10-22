package ku.cs.controllers.admin;


import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminControlController {

    @FXML
    void customerlistBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("admincheckcustomer");
    }

    @FXML
    void logoutBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void adminsettingBtn(ActionEvent event) throws IOException{
        FXRouter.goTo("adminsetting");
    }

}
