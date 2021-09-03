package ku.cs.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.customer.CustomerList;
import ku.cs.service.admin.CustomerListHardCodeDataSource;

import java.io.*;

public class CustomerListController{
    @FXML private ListView<CustomerAccount> customerListView;
    @FXML private Label idLabel;
    @FXML private Label firstnameLabel;
    @FXML private Label lastnameLabel;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;

   private CustomerListHardCodeDataSource dataSource;
   private CustomerList customerList;

   String path = "C:/Administrator/project3/data/customer.csv";
   File file = new File(path);

   @FXML
    public void initialize() {
       dataSource = new CustomerListHardCodeDataSource();
       customerList = dataSource.getCustomerList();
       showListView();
       clearSelectedCustomer();
       handleSelectedListView();
   }

    private void clearSelectedCustomer() {
        idLabel.setText("");
        firstnameLabel.setText("");
        lastnameLabel.setText("");
        usernameLabel.setText("");
        passwordLabel.setText("");
    }

    private void handleSelectedListView() {
       customerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerAccount>() {
           @Override
           public void changed(ObservableValue<? extends CustomerAccount> observableValue, CustomerAccount customerAccount, CustomerAccount t1) {
               System.out.println(t1 + " Is Selected.");
               showSelectedCustomer(t1);
           }
       });
    }

    private void showSelectedCustomer(CustomerAccount account){
       firstnameLabel.setText(account.getFirstname());
       lastnameLabel.setText(account.getLastname());
       usernameLabel.setText(account.getUsername());
       passwordLabel.setText(account.getPassword());
    }

    private void showListView() {

    }

    @FXML public void backBtn(ActionEvent event) throws IOException{
        FXRouter.goTo("admincontroller");
    }




}