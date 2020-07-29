package be.kdg.game.view.gameover;


import be.kdg.game.model.GameModel;
import be.kdg.game.view.highscores.HighScorePresenter;
import be.kdg.game.view.highscores.HighScoreView;
import be.kdg.game.view.spel.SpelPresenter;
import be.kdg.game.view.spel.SpelView;
import be.kdg.game.view.winscherm.WinschermPresenter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverPresenter extends GridPane {
    private GameOverView view;
    private GameModel model;
    private GameModel gameModel;

    public GameOverPresenter(GameOverView view, GameModel model, Stage stage) {
        this.view = view;
        this.model = model;
        this.gameModel = new GameModel();

        this.addEventHandlers(stage);
        this.updateView();
    }

    private void updateView() {

    }

    private void addEventHandlers(Stage stage) {
        view.getBtnQuit().setOnAction(actionEvent -> Platform.exit());

        view.getBtnTryAgain().setOnAction(actionEvent -> ((Stage) view.getScene().getWindow()).close());

        view.getBtnHighScores().setOnAction(actionEvent -> {
            HighScoreView hsView = new HighScoreView();
            HighScorePresenter hsPres = new HighScorePresenter(model, hsView, stage);
            Stage hsStage = new Stage();
            hsStage.initOwner(view.getScene().getWindow());
            hsStage.setScene(new Scene(hsView));
            hsStage.setTitle("2048");
            hsStage.getIcons().add(new Image("icon2048.jpg"));
        });
    }


}

