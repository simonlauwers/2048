package be.kdg.game;

import be.kdg.game.model.GameModel;
import be.kdg.game.view.startscherm.StartschermView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import be.kdg.game.view.startscherm.*;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameModel model = new GameModel();
        model.maakFileEnBestandAan();
        StartschermView view = new StartschermView();
        StartschermPresenter presenter = new StartschermPresenter(model, view, primaryStage);
        primaryStage.setScene(new Scene(view));
        primaryStage.setTitle("2048");
        primaryStage.show();
        primaryStage.getIcons().add(new Image("icon2048.jpg"));
        primaryStage.setResizable(false);
    }
}
