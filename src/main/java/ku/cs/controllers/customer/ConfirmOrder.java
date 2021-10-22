package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.model.customer.OrderProduct;
import ku.cs.model.customer.Product;
import ku.cs.model.customer.ProductCheck;
import ku.cs.service.product.ProductCheckFileDataSource;

import java.io.IOException;

public class ConfirmOrder {

    @FXML private ImageView pdImage;
    @FXML private Label pdname;
    @FXML private Label countLabel;
    @FXML private Label priceLabel;

    private Product product;
    private ProductCheck productCheck;
    private OrderProduct orderProduct;
    private ProductCheckFileDataSource productCheckFileDataSource;

    @FXML
    public void initialize() {
        productCheck = OrderProduct.getProduct();
        pdname.setText(productCheck.getPdname());
        countLabel.setText(productCheck.getRequireproduct());
        priceLabel.setText(productCheck.getTotalprice());
        pdImage.setImage(new Image(("file :" +product.getPdimage()), true));
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("marketplace");
    }
}
