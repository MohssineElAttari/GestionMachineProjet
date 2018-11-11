/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author imadeddine
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/MachineVue.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Parc Informatique");
            scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
            // Add a custom icon.
            stage.getIcons().add(new Image(this.getClass().getResource("images/network.png").toString()));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        launch(args);
    }

}
