package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MarketPlaceController {

    @FXML private Label productnameLabel;
    @FXML private Label dcproductLabel;
    @FXML private Label countLabel;
    @FXML private Label priceLabel;

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("shop");
    }

    @FXML
    void confirmodBtn(ActionEvent event) {

    }

    @FXML
    void orderBtn(ActionEvent event) {

    }

}
