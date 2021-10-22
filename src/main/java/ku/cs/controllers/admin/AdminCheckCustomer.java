package ku.cs.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.model.User.CustomerAccount;
import ku.cs.model.User.CustomerAccountList;
import ku.cs.service.account.CustomerDataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;

public class AdminCheckCustomer {
    @FXML private ListView<CustomerAccount> customerview;
    @FXML private Label csanme;
    @FXML private Label cslastname;
    @FXML private Label username;
    @FXML private Label storename;
    @FXML private Label login;
    @FXML private ImageView userImage;

    private CustomerAccountList customerAccountList;
    private CustomerDataSource dataSource;
    private CustomerAccount selectedCustomer;

    @FXML
    public void initialize() {
        dataSource = new CustomerDataSource("data", "customer.csv");
        customerAccountList = dataSource.readData();
        System.out.println(customerAccountList.getAllCustomer());
        System.out.println(System.getProperty("user.dir"));

        clearSelectedCustomer();
        handleSelectedListView();
        showCustomerData();
    }

    private void handleSelectedListView() {
        customerview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerAccount>() {
            @Override
            public void changed(ObservableValue<? extends CustomerAccount> observableValue, CustomerAccount customerAccount, CustomerAccount t1) {
                System.out.println(t1 + " is selected.");
                showSelectedCustomer(t1);
            }
        });
    }

    private void showCustomerData() {
        customerview.getItems().addAll(customerAccountList.getAllCustomer());
        customerview.refresh();
    }

    private void showSelectedCustomer(CustomerAccount css){
        selectedCustomer = css;
        csanme.setText(css.getFirstname());
        cslastname.setText(css.getLastname());
        username.setText(css.getUsername());
        storename.setText(css.getStatus());
        login.setText(css.getDate());
        userImage.setImage(new Image("file:" +selectedCustomer.getImage(), true));
    }

    private void clearSelectedCustomer() {
        selectedCustomer = null;
        csanme.setText("");
        cslastname.setText("");
        username.setText("");
        storename.setText("");
        login.setText("");
        userImage.setImage(new Image(getClass().getResource("/images/defaultprofile.jpg").toExternalForm()));
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        FXRouter.goTo("admincontroller");
    }
}
