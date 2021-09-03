package ku.cs.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ku.cs.model.customer.CustomerList;

import java.io.*;

public class AdminSetting {

    @FXML private TextField userField;
    @FXML private PasswordField pwField;
    @FXML private Button addBtn;

    @FXML
    void handleAddButton(ActionEvent event) throws IOException {
        String user = userField.getText();
        String pw = pwField.getText();

        if ((userField.getText() != null) && (pwField.getText() != null)) {
            File file = new File("C:/Administrator/project3/data/customer.csv");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write("\r\n" + user + "," + pw);
            bufferedWriter.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setContentText("ใส่ข้อมูลสำเร็จ");
            alert.showAndWait();

        }
    }

    @FXML
    void backBtn(ActionEvent event) throws IOException{
        FXRouter.goTo("admincontroller");
    }
}


