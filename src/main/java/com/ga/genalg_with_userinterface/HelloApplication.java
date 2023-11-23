package com.ga.genalg_with_userinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);

        Application.setUserAgentStylesheet(Objects.requireNonNull(getClass().getResource("styling.css")).toString());

        stage.setTitle("Genetic Algorithm");
        stage.getIcons().add(new Image("/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public void startApplication(){
        launch();
    }
}