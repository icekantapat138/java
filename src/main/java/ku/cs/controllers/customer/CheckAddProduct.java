package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.model.customer.Product;
import ku.cs.model.customer.ProductAdd;

import java.io.IOException;

public class CheckAddProduct {

    @FXML private Label pdname;
    @FXML private Label pddetail;
    @FXML private Label pdcount;
    @FXML private Label pdprice;
    @FXML private ImageView pdImage;

    private Product product;
    private ProductAdd productAdd;

    public void initialize() {
        product = ProductAdd.getProduct();
        pdname.setText(product.getPdname());
        pddetail.setText(product.getPddetail());
        pdcount.setText(product.getPdamount());
        pdprice.setText(product.getPdprice());
        pdImage.setImage(new Image("file:" +product.getPdimage(), true));
    }

    @FXML
    void confirmBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("store");
    }

}
