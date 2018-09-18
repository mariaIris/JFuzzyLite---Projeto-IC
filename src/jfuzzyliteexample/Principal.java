package jfuzzyliteexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Laura
 */
public class Principal extends Application {
    private static Stage stagePrincipal;
    
    @Override
    public void start(Stage stage) throws Exception {
        stagePrincipal = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("/visao/FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        //Ocupa a tela toda
        /*
        stage.centerOnScreen();
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());*/
        
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStagePrincipal() {
        return stagePrincipal;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
