package ku.cs.controllers.admin;


import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    void customerlistBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customerlist");
    }

    @FXML
    void logoutBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void productaddBtn(ActionEvent event) throws IOException {
        try{
            FXRouter.goTo("addproduct");
        }catch (IOException e){
            System.err.println("เข้าสู่หนเา Add Product ไม่ได้");
        }
    }

    @FXML
    void adminsettingBtn(ActionEvent event) throws IOException{
        FXRouter.goTo("adminsetting");
    }

}
