package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StartProgram {

    @FXML private ImageView image1;

    public void initialize() {
        String img1 = getClass().getResource("/images/MIN.png").toExternalForm();
        image1.setImage(new Image(img1));
    }

    @FXML
    public void adminBtn(ActionEvent event){
        try {
            FXRouter.goTo("adminlogin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Admin Login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    void customerBtn(ActionEvent event) {
        try {
            FXRouter.goTo("customerlogin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Customer Login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void exitProgram(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void creatorBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("creator");
    }
}


