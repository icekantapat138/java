package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.model.customer.AddProduct;
import ku.cs.model.customer.AddProductList;
import ku.cs.service.customer.AddProductListFileDataSource;
import ku.cs.service.customer.AddProductListHardCodeDataSource;

import java.io.File;
import java.io.IOException;

public class StoreAddProductController {

    @FXML private ListView<AddProduct> productListview;
    @FXML private TextField imageTxt;
    @FXML private TextField nameTxt;
    @FXML private TextField describeTxt;
    @FXML private TextField countTxt;
    @FXML private TextField priceTxt;
    @FXML private ImageView img;
    @FXML private FileChooser fileChooser;

    private AddProductListHardCodeDataSource productDataSource;
    private AddProductListFileDataSource productfile;
    private AddProductList addProductList;
    private AddProduct addProduct;

    @FXML
    public void initialize() {
        productDataSource = new AddProductListHardCodeDataSource();
        productfile = new AddProductListFileDataSource("data", "product.csv");
        addProduct = new AddProduct();

        addProductList = productDataSource.getAddProductList();
        ShowListView();
        ClearSelectedListView();
        HandleSelectedListView();

    }

    @FXML
    public void broseImgBtn(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            img.setImage(image);
            imageTxt.setText(file.getAbsolutePath());
        }


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG file", "*.PNG")
                , new FileChooser.ExtensionFilter("png file", "*.png")
                , new FileChooser.ExtensionFilter("JPG file", "*.JPG")
                , new FileChooser.ExtensionFilter("jpg file", "*.jpg")
        );
    }

    private void ShowListView() {
        productListview.getItems().addAll(addProductList.getAllProducts());
        productListview.refresh();
    }
    private void HandleSelectedListView() {
        productListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AddProduct>() {
            @Override
            public void changed(ObservableValue<? extends AddProduct> observableValue, AddProduct addProduct, AddProduct t1) {
                System.out.println(t1 + " is selected");
                ShowSelectProduct(t1);
            }
        });
    }

    private void ShowSelectProduct(AddProduct pd){
        imageTxt.setText(pd.getImage());
        nameTxt.setText(pd.getProductname());
        describeTxt.setText(pd.getDescribeproduct());
        priceTxt.setText(String.valueOf(pd.getPriceproduct()));
        if (addProduct.checkCount() == false){
            countTxt.setText(String.valueOf(pd.getCount()));
            countTxt.setStyle("-fx-text-fill: red; -fx-font-size: 16px; ");
        }else {
            countTxt.setText(String.valueOf(pd.getCount()));
            countTxt.setStyle("-fx-text-fill: black; -fx-font-size: 16px; ");
        }
    }

    private void ClearSelectedListView() {
        imageTxt.setText("");
        nameTxt.setText("");
        describeTxt.setText("");
        countTxt.setText("");
        priceTxt.setText("");
        img.setImage(null);
    }

    @FXML
    public void addBtn(ActionEvent event) {
        if ((imageTxt.getText().equals("")) || (nameTxt.getText().equals("") || (describeTxt.getText().equals("")) || (countTxt.getText().equals("")) || (priceTxt.getText().equals("")))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
            alert.setContentText("Please Enter All Field.");
            alert.setTitle("Error");
            ClearSelectedListView();
        }
        else {
            System.out.println("Add Complete");

            AddProduct pd1 = new AddProduct(
                    imageTxt.getText(),
                    nameTxt.getText(),
                    describeTxt.getText(),
                    Integer.parseInt(countTxt.getText()),
                    Double.parseDouble(priceTxt.getText()));

            addProductList.addProduct(pd1);
            /**
             * productfile.writeData(pd1);
             */

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.showAndWait();
            alert.setContentText("Enter Complete.");
            alert.setTitle("Confirm");
            ClearSelectedListView();
            System.out.println(pd1);
        }
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("customercontroller");
    }

    @FXML
    void deleteBtn(ActionEvent event) {

    }

    @FXML
    void clearBtn(ActionEvent event) {
        imageTxt.setText("");
        nameTxt.setText("");
        describeTxt.setText("");
        countTxt.setText("");
        priceTxt.setText("");
        img.setImage(null);
    }

    @FXML
    void updateBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.showAndWait();
        alert.setContentText("Edit Complete.");
        alert.setTitle("Edit");

    }

    @FXML
    public void priceSort(ActionEvent event) {

    }

    @FXML
    public void countSort(ActionEvent event) {

    }

}

