package be.kdg.game.view.winscherm;


import be.kdg.game.model.GameModel;
import be.kdg.game.model.Spel;
import be.kdg.game.view.highscores.HighScorePresenter;
import be.kdg.game.view.highscores.HighScoreView;
import be.kdg.game.view.spel.SpelView;
import be.kdg.game.view.startscherm.StartschermPresenter;
import be.kdg.game.view.startscherm.StartschermView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinschermPresenter extends GridPane {
    private WinschermView view;
    private GameModel model;

    public WinschermPresenter(WinschermView view, GameModel model, Stage stage) {
        this.view = view;
        this.model = model;

        this.addEventHandlers(stage);
    }


    private void addEventHandlers(Stage stage) {
        view.getBtnQuit().setOnAction(actionEvent -> Platform.exit());

        view.getBtnContinue().setOnAction(actionEvent -> {
            //continue playing
            ((Stage) view.getScene().getWindow()).close();



        });

        view.getBtnRestart().setOnAction(actionEvent -> {
            view.getScene().getWindow().hide(); //winscherm gaat dicht
            model.resetGame();


        });

        view.getBtnHighScores().setOnAction(actionEvent -> {

            stage.initOwner(view.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
    }

}
