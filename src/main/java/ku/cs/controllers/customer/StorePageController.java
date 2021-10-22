package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import ku.cs.model.User.AdminAccount;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.LoginCustomer;
import ku.cs.model.customer.Product;
import ku.cs.model.customer.ProductAdd;
import ku.cs.model.customer.ProductList;
import ku.cs.service.account.CustomerDataSource;
import ku.cs.service.product.ProductFileDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StorePageController {

    @FXML private Label storenameLabel;
    @FXML private Label lowproductLabel;
    @FXML private Label storenpdameLabel;
    @FXML private TextField pdnameTxt;
    @FXML private TextField pdDetailsTxt;
    @FXML private TextField pdAmountTxt;
    @FXML private TextField pdPriceTxt;
    @FXML private ListView<Product> productView;
    @FXML private ImageView pdImage;
    @FXML private TextField pdImageTxt;

    private CustomerAccount customerAccount;
    private CustomerAccountList customerAccountList;
    private CustomerDataSource customerFileDataSource;
    private Product product;
    private Product selectedProduct;
    private ProductList productList;
    private ProductFileDataSource productFileDataSource;
    private ProductAdd productAdd;

    @FXML
    public void initialize() {
        customerAccount = LoginCustomer.getCustomerAccount();
        storenameLabel.setText(customerAccount.getStatus());
        lowproductLabel.setText(customerAccount.getLowproduct());
        customerFileDataSource = new CustomerDataSource("data", "customer.csv");
        productFileDataSource = new ProductFileDataSource("data", "product.csv");
        productList = productFileDataSource.readData();
        customerAccountList = customerFileDataSource.readData();
        pdImage.setImage(new Image(getClass().getResource("/images/pdnoimage.jpg").toExternalForm()));
        pdImageTxt.setText("images/pdnoimage.jpg");
        showProductData();
        handleSelectedProduct();
        clearAllField();
    }

    private void showProductData() {
        String storename = customerAccount.getStatus();
        productList.checkStoreName(storename);
        productView.refresh();
        productView.getItems().addAll(productList.checkStoreName(storename));
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
        storenpdameLabel.setText(product.getStorename());
        if(storenameLabel.getText() == storenpdameLabel.getText()) {
            pdnameTxt.setText(product.getPdname());
            pdDetailsTxt.setText(product.getPddetail());
            if (product.checkLowAmount() == true){
                pdAmountTxt.setText(product.getPdamount());
                pdAmountTxt.setStyle("-fx-text-fill: #FF0000 ; -fx-font-weight: bold");
            }else {
                pdAmountTxt.setText(product.getPdamount());
                pdAmountTxt.setStyle("-fx-text-fill: #000000");
            }
            pdPriceTxt.setText(product.getPdprice());
            pdImage.setImage(new Image("file:" +selectedProduct.getPdimage(), true));
            pdImageTxt.setText(product.getPdimage());
            pdnameTxt.setEditable(false);
            pdDetailsTxt.setEditable(false);
            pdPriceTxt.setEditable(false);
            pdImageTxt.setEditable(false);
            pdAmountTxt.setEditable(false);
        }
        pdnameTxt.setText(product.getPdname());
        pdDetailsTxt.setText(product.getPddetail());
        if (product.checkLowAmount() == true){
            pdAmountTxt.setText(product.getPdamount());
            pdAmountTxt.setStyle("-fx-text-fill: #FF0000 ; -fx-font-weight: bold");
        }else {
            pdAmountTxt.setText(product.getPdamount());
            pdAmountTxt.setStyle("-fx-text-fill: #000000");
        }
        pdPriceTxt.setText(product.getPdprice());
        pdImage.setImage(new Image("file:" +selectedProduct.getPdimage(), true));
        pdImageTxt.setText(product.getPdimage());
    }

    private void clearListView() {
        productView.getItems().clear();
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customercontroller");
    }

    @FXML
    void addBtn(ActionEvent event) throws IOException {
        if(pdnameTxt.getText().equals("") || pdDetailsTxt.getText().equals("") || pdAmountTxt.getText().equals("") || pdPriceTxt.getText().equals("") || pdImageTxt.getText().equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Enter All Field");
            alert1.setContentText("Please Enter All Field!!");
            alert1.showAndWait();
        }else{
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm a");
            String dateShow = formatTime.format(currentTime);
            System.out.println("Pass");
            productFileDataSource = new ProductFileDataSource("data", "product.csv");
            productList = productFileDataSource.readData();
            product = new Product(storenameLabel.getText(),
                    lowproductLabel.getText(),
                    pdnameTxt.getText(),
                    pdDetailsTxt.getText(),
                    pdAmountTxt.getText(),
                    pdPriceTxt.getText(),
                    pdImageTxt.getText(),
                    dateShow.trim());
        }

            selectedProduct = product;
            System.out.println(product.toCsv());

            productList.addProduct(product);
            productFileDataSource.writeData(productList);

            clearAllField();
            clearListView();
            showProductData();
            productAdd = new ProductAdd(product);
            FXRouter.goTo("checkaddproduct");
        }

    private void clearAllField() {
        storenpdameLabel.setText("");
        pdnameTxt.clear();
        pdDetailsTxt.clear();
        pdAmountTxt.clear();
        pdAmountTxt.setStyle("-fx-text-fill: #000000");
        pdPriceTxt.clear();
        pdImageTxt.setText("images/pdnoimage.jpg");
        pdImage.setImage(new Image(getClass().getResource("/images/pdnoimage.jpg").toExternalForm()));
    }

    @FXML
    void browseImgBtn(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null) {
            try {
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_" + System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath() + System.getProperty("file.separator") + filename
                );
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                // SET NEW FILE PATH TO IMAGE
                pdImage.setImage(new Image(target.toUri().toString()));
                pdImageTxt.setText(destDir + "/" + filename);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    @FXML
    void clearBtn(ActionEvent event) {
        handleSelectedProduct();
        productView.getSelectionModel().clearSelection();
        storenpdameLabel.setText("");
        pdnameTxt.clear();
        pdDetailsTxt.clear();
        pdAmountTxt.clear();
        pdPriceTxt.clear();
        pdImageTxt.setText("/images/pdnoimage.jpg");
        pdImage.setImage(new Image(getClass().getResource("/images/pdnoimage.jpg").toExternalForm()));
        pdnameTxt.setEditable(true);
        pdDetailsTxt.setEditable(true);
        pdPriceTxt.setEditable(true);
        pdImageTxt.setEditable(true);
        pdAmountTxt.setEditable(true);
    }

    @FXML
    void updateBrn(ActionEvent event) {
        String pdAmount = pdAmountTxt.getText();
        ProductFileDataSource dataSource = new ProductFileDataSource("data", "product.csv");
        Product pd = productList.searchProductName(pdnameTxt.getText());
        pd.setPdamount(pdAmountTxt.getText());
        dataSource.writeData(productList);
        Dialog d = new Alert(Alert.AlertType.CONFIRMATION);
        d.setTitle("Password Has Been Changed.");
        d.showAndWait();

    }
}
