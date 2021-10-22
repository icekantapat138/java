package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "MIN Shop");
        configRoute();
        FXRouter.goTo("startprogram");
    }

    private static void configRoute() {
        String packageStr = "ku/cs/";
        FXRouter.when("startprogram", packageStr+"startprogram.fxml");
        FXRouter.when("adminlogin", packageStr + "adminlogin.fxml");
        FXRouter.when("customerlogin", packageStr+"customerlogin.fxml");
        FXRouter.when("customercreate", packageStr + "customercreate.fxml");
        FXRouter.when("admincontroller", packageStr + "admincontroller.fxml");
        FXRouter.when("admincheckcustomer", packageStr + "admincheckcustomer.fxml");
        FXRouter.when("storeaddproduct", packageStr + "storeaddproduct.fxml");
        FXRouter.when("adminsetting", packageStr + "adminsetting.fxml");
        FXRouter.when("customercontroller", packageStr + "customercontroller.fxml");
        FXRouter.when("createstore", packageStr + "createstore.fxml");
        FXRouter.when("shop" , packageStr + "shop.fxml");
        FXRouter.when("customerinfo", packageStr + "customerinfo.fxml");
        FXRouter.when("marketplace", packageStr + "marketplace.fxml");
        FXRouter.when("store", packageStr + "store.fxml");
        FXRouter.when("creator", packageStr + "creator.fxml");
        FXRouter.when("checkaddproduct", packageStr + "checkaddproduct.fxml");
        FXRouter.when("confirnorder", packageStr + "confirmorder.fxml");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
