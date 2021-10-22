package ku.cs.controllers.customer;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.model.customer.LoginCustomer;
import ku.cs.model.customer.ProductList;
import ku.cs.service.account.CustomerDataSource;
import ku.cs.service.product.ProductFileDataSource;

import java.io.IOException;

public class CreateStoreController {

    @FXML private TextField storenameText;
    @FXML private TextField lowproductTxt;

    private CustomerAccount customerAccount;
    private CustomerAccountList customerAccountList;
    private CustomerDataSource customerFileDataSource;
    private ProductList productList;
    private ProductFileDataSource productFileDataSource;

    public void initialize() {
        customerAccount = LoginCustomer.getCustomerAccount();
        storenameText.setText(customerAccount.getStatus());
        lowproductTxt.setText(customerAccount.getLowproduct());

        customerFileDataSource = new CustomerDataSource("data", "customer.csv");
        productFileDataSource = new ProductFileDataSource("data", "product.csv");
        customerAccountList = customerFileDataSource.readData();
        productList = productFileDataSource.readData();

    }

    @FXML
    public void nextBtn(ActionEvent event) throws IOException{
        if(storenameText.getText().equals("Not Have Store")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Please Enter Your Store Name");
            alert1.setContentText("Please Create Your Store Name.");
            alert1.showAndWait();
        }else {
            FXRouter.goTo("store");
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Welcome!");
            alert1.setContentText("Welcome To " + storenameText.getText() + ".");
            alert1.showAndWait();
        }
    }

    @FXML
    public void updateBtn(ActionEvent event){
        if(storenameText.getText().equals("Not Have Store")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Please Enter Your Store Name");
            alert1.setContentText("Not Have Store can't Use.");
            alert1.showAndWait();
        }else {
            CustomerAccount customerAccount1 = customerAccountList.checkStore(customerAccount.getStatus());
            customerAccount1.setStatus(storenameText.getText());
            customerAccount1.setLowproduct(lowproductTxt.getText());
            customerFileDataSource.writeData(customerAccountList);
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Save Information Successful");
            alert1.setContentText("Your Store Name Has Been Save.");
            alert1.showAndWait();
        }
    }
}
