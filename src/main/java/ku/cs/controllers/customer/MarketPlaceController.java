package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.*;
import ku.cs.service.account.CustomerDataSource;
import ku.cs.service.product.ProductCheckFileDataSource;
import ku.cs.service.product.ProductDataSource;
import ku.cs.service.product.ProductFileDataSource;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class MarketPlaceController {

    @FXML private ListView<Product> productView;
    @FXML private TextField pdname;
    @FXML private Label pddetailLabel;
    @FXML private Label alert;
    @FXML private Label priceLabel;
    @FXML private TextField requirepdTxt;
    @FXML private Button orderBtn;
    @FXML private Button backBtn;
    @FXML private Button confirmOrderBtn;
    @FXML private Label storrenameLabel;
    @FXML private Label pdnameLabel;
    @FXML private Label pdpriceLabel;
    @FXML private TextField pdCount;
    @FXML private ImageView pdImage;
    @FXML private MenuButton sortMenuButton;
    @FXML private TextField totalTxt;
    @FXML private TextField pdImageurl;

    private CustomerAccount customerAccount;
    private Product product;
    private Product selectedProduct;
    private ProductList productList;
    private ProductFileDataSource productFileDataSource;
    private OrderProduct orderProduct;
    private ProductCheckList productCheckList;
    private ProductCheckFileDataSource productCheckFileDataSource;

    @FXML
    public void initialize() {
        customerAccount = LoginCustomer.getCustomerAccount();
        productFileDataSource = new ProductFileDataSource("data", "product.csv");
        productList = productFileDataSource.readData();
        pdImage.setImage(new Image(getClass().getResource("/images/pdnoimage.jpg").toExternalForm()));
        pdCount.setEditable(false);
        productCheckFileDataSource = new ProductCheckFileDataSource("data", "purchase.csv");
        pdImageurl.setVisible(false);
        showProductData();
        handleSelectedProduct();
        //clearAllField();
    }

    private void clearListView() {
        productView.getItems().clear();
    }

    private void showProductData() {
        productView.getItems().addAll(productList.getAllProductList());
        productView.refresh();
    }

    private void handleSelectedProduct() {
        productView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                System.out.println(t1 + " is selected.");
                showSelectedProduct(t1);
            }
        });
    }

    private void showSelectedProduct(Product product) {
        selectedProduct = product;
        storrenameLabel.setText(product.getStorename());
        pdname.setText(product.getPdname());
        pddetailLabel.setText(product.getPddetail() + System.lineSeparator());
        pdCount.setText(product.getPdamount());
        pdCount.setEditable(false);
        pdpriceLabel.setText(product.getPdprice());
        pdImage.setImage(new Image("file:" +selectedProduct.getPdimage(), true));
        pdImageurl.setText(product.getPdimage());
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customercontroller");
    }

    @FXML
    void confirmodBtn(ActionEvent event) throws IOException {
        System.out.println("It Use");
//        productCheckList = productCheckFileDataSource.readData();
//        ProductCheck productCheck = new ProductCheck(pdname.getText(),
//                pdCount.getText(),
//                totalTxt.getText(),
//                pdImageurl.getText());
//        productCheckList.addProductCheck(productCheck);
//        productCheckFileDataSource.writeData(productCheckList);
//        orderProduct = new OrderProduct(productCheck);
//        FXRouter.goTo("confirmorder");
        if(totalTxt.getText().equals("")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please Buy Product");
            error.showAndWait();

        }else{
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Your Order has been Confirm\n Total Price: " +totalTxt.getText() +" Bahts.");
            confirm.showAndWait();
            productList = productFileDataSource.readData();
            Product product = productList.searchProductName(pdname.getText());
            product.setPdamount(pdCount.getText());
            productFileDataSource.writeData(productList);
            FXRouter.goTo("marketplace");
        }
    }

    @FXML
    void orderBtn(ActionEvent event) {
        int requireItem = Integer.parseInt(pdCount.getText());
        int amountItem = Integer.parseInt(requirepdTxt.getText());
        int itemPrice = Integer.parseInt(pdpriceLabel.getText());

        if(amountItem > requireItem){
            alert.setText("*Insuffcient number of products.");
        }else{
           int amountProduct = requireItem - amountItem;
           String amountproductcount = String.valueOf(amountProduct);
           int total = amountItem * itemPrice;
           String totalprice = String.valueOf(total);
           pdCount.setText(amountproductcount);
            totalTxt.setText(totalprice);
           alert.setText("");
        }
    }

    @FXML
    public void sortHightoLow(ActionEvent event){
        clearListView();
        productView.refresh();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.pdpriceToInt() > o2.pdpriceToInt()) return 1;
                if (o1.pdpriceToInt() < o2.pdpriceToInt()) return -1;
                return 0;
            }
        };
        ProductDataSource<ProductList> dataSource = new ProductFileDataSource("data", "product.csv");
        ProductList productList1 = dataSource.readData();
        productList1.sort(productComparator);
        productView.getItems().addAll(productList1.getAllProductList());
    }

    @FXML
    public void sortLowtoHigh(ActionEvent event){
        clearListView();
        productView.refresh();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.pdpriceToInt() < o2.pdpriceToInt()) return 1;
                if (o1.pdpriceToInt() > o2.pdpriceToInt()) return -1;
                return 0;
            }
        };
        ProductDataSource<ProductList> dataSource = new ProductFileDataSource("data", "product.csv");
        ProductList productList1 = dataSource.readData();
        productList1.sort(productComparator);
        productView.getItems().addAll(productList1.getAllProductList());
    }

    @FXML
    public void sortOldtoNew(ActionEvent event){
        clearListView();
        productView.refresh();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getDate().compareTo(o2.getDate()) > 0) return 1;
                if (o1.getDate().compareTo(o2.getDate()) < 0) return -1;
                return 0;
            }
        };
        ProductDataSource<ProductList> dataSource = new ProductFileDataSource("data", "product.csv");
        ProductList productList1 = dataSource.readData();
        productList1.sort(productComparator);
        productView.getItems().addAll(productList1.getAllProductList());
    }

    @FXML
    public void sortNewtoOld(ActionEvent event){
        clearListView();
        productView.refresh();
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getDate().compareTo(o2.getDate()) < 0) return 1;
                if (o1.getDate().compareTo(o2.getDate()) > 0) return -1;
                return 0;
            }
        };
        ProductDataSource<ProductList> dataSource = new ProductFileDataSource("data", "product.csv");
        ProductList productList1 = dataSource.readData();
        productList1.sort(productComparator);
        productView.getItems().addAll(productList1.getAllProductList());
    }

    @FXML
    public void gotostore(ActionEvent event){
        String storename = storrenameLabel.getText();
        productList.checkStoreName(storename);
        clearListView();
        productView.refresh();
        productView.getItems().addAll(productList.checkStoreName(storename));
    }


}
