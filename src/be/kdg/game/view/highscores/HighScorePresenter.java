package be.kdg.game.view.highscores;

import be.kdg.game.model.GameModel;
import be.kdg.game.view.startscherm.StartschermPresenter;
import be.kdg.game.view.startscherm.StartschermView;
import be.kdg.game.view.winscherm.WinschermPresenter;
import be.kdg.game.view.winscherm.WinschermView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HighScorePresenter {
    private GameModel model;
    private HighScoreView view;

    public HighScorePresenter(GameModel model, HighScoreView view, Stage stage) {
        this.model = model;
        this.view = view;

        this.addEventhandlers(stage);

    }

    public void addEventhandlers(Stage stage){
        view.getBtnBack().setOnAction(actionEvent -> {
            StartschermView sv = new StartschermView();
            StartschermPresenter sp = new StartschermPresenter(model, sv, stage);
            Scene scene = new Scene(sv);
            stage.setScene(scene);
        });
    }

    public void updateview(){


    }
}