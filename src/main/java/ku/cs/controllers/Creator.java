package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Creator {

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    public void initialize() {

        String img1 = getClass().getResource("/image/ice.jpg").toExternalForm();
        image1.setImage(new Image(img1));

        String img2 = getClass().getResource("/image/mew.jpg").toExternalForm();
        image2.setImage(new Image(img2));

        String img3 = getClass().getResource("/image/noon.jpg").toExternalForm();
        image3.setImage(new Image(img3));

    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("startprogram");
    }

}
