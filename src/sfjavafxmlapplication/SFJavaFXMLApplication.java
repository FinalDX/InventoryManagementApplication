package sfjavafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * SFJava FXML Application class
 *
 * @author Sean Fenner
 */
public class SFJavaFXMLApplication extends Application {
    
    // =========================================================================
    // START METHOD
    // =========================================================================
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------
    
    // =========================================================================
    // MAIN METHOD
    // =========================================================================
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // -------------------------------------------------------------------------
}
