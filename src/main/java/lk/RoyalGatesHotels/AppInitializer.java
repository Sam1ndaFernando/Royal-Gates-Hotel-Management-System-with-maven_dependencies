package lk.RoyalGatesHotels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/welcomePage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Hotel Management System");
        primaryStage.getIcons().add(new Image("/assets/icons/icon3.png"));
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }
}