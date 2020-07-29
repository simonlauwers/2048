package be.kdg.game.view.startscherm;

import be.kdg.game.model.GameModel;
import be.kdg.game.model.Spel;
import be.kdg.game.view.gameover.GameOverPresenter;
import be.kdg.game.view.highscores.HighScorePresenter;
import be.kdg.game.view.highscores.HighScoreView;
import be.kdg.game.view.spel.SpelPresenter;
import be.kdg.game.view.spel.SpelView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class StartschermPresenter {
    private GameModel model;
    private StartschermView view;

    public StartschermPresenter(GameModel model, StartschermView view, Stage stage) {
        this.model = model;
        this.view = view;



        this.addEventHandlers(stage);
    }

    public void addEventHandlers(Stage stage) {
        //Play Game
        view.getBtnPlay().setOnAction(actionEvent -> {
            Spel spel = new Spel();
            spel.Play(model, view, stage);
        });


        //Quit Game
        view.getBtnQuit().setOnAction(actionEvent -> Platform.exit());

        //Highscores
        view.getBtnHighScores().setOnAction(actionEvent -> {
                HighScoreView hv = new HighScoreView();
                HighScorePresenter hp = new HighScorePresenter(model, hv, stage);
                Scene scene = new Scene(hv);
                stage.setScene(scene);
          });
    }
}