package be.kdg.game.model;

import be.kdg.game.view.gameover.GameOverPresenter;
import be.kdg.game.view.gameover.GameOverView;
import be.kdg.game.view.spel.SpelPresenter;
import be.kdg.game.view.spel.SpelView;
import be.kdg.game.view.startscherm.StartschermView;
import be.kdg.game.view.winscherm.WinschermPresenter;
import be.kdg.game.view.winscherm.WinschermView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Spel {
    public void Play(GameModel model, StartschermView view, Stage stage) {
        final int TEGEL_SIZE = 64;
        SpelView spelView = new SpelView();
        SpelPresenter spelPresenter = new SpelPresenter(model, spelView);
        Stage toevoegenStage = new Stage();
        toevoegenStage.initOwner(view.getScene().getWindow());
        toevoegenStage.setScene(new Scene(spelView));
        view.getScene().getWindow().hide();
        toevoegenStage.setTitle("2048");
        toevoegenStage.getIcons().add(new Image("icon2048.jpg"));


        FlowPane flow = new FlowPane();

        toevoegenStage.setResizable(false);
        toevoegenStage.setOnCloseRequest(event -> Platform.exit());

        Scene myScene = new Scene(flow, model.getWidth(), model.getHeight());
        toevoegenStage.setScene(myScene);
        //System.out.println("De breedte van het spel is " + myScene.getWidth() + ", en de hoogte is " + myScene.getHeight());

        myScene.setOnKeyPressed(event -> {        //game over simuleren
            if (event.getCode() == KeyCode.L) {
                model.lose = true;
                model.schrijfScoreWeg();
            }

            if (event.getCode() == KeyCode.W) {      //win simuleren
                model.win = true;
                model.schrijfScoreWeg();
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                model.resetGame();
            }

            if (!model.kanBewegen() || (!model.win && !model.kanBewegen())) {
                model.lose = true;
                model.schrijfScoreWeg();
            }


            if (!model.win && !model.lose) {
                switch (event.getCode()) {
                    case LEFT:
                        model.left();
                        break;
                    case RIGHT:
                        model.right();
                        break;
                    case DOWN:
                        model.down();
                        break;
                    case UP:
                        model.up();
                        break;
                }
            }
            model.relocate(330, 390);
        });


        flow.getChildren().add(model);
        toevoegenStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = model.getGraphicsContext2D();
                gc.setFill(Color.rgb(187, 173, 160, 1.0));
                gc.fillRect(0, 0, model.getWidth(), model.getHeight());


                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 4; x++) {
                        Tegel tegel = model.getTegels()[x + y * 4];
                        int value = tegel.waarde;
                        int xOffset = offsetCoors(x);
                        int yOffset = offsetCoors(y);

                        gc.setFill(tegel.getBackground());
                        gc.fillRoundRect(xOffset, yOffset, TEGEL_SIZE, TEGEL_SIZE, 10, 10);       //vierkant met ronde hoeken
                        gc.setFill(tegel.getNummerKleur());

                        final int size = value < 100 ? 32 : value < 1000 ? 28 : 24; //anders past getal niet in tegel
                        gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                        gc.setTextAlign(TextAlignment.CENTER);  //horizontaal gecentreerd
                        gc.setTextBaseline(VPos.CENTER);        //verticaal  gecentreerd


                        String s = String.valueOf(value);

                        if (value != 0)
                            gc.fillText(s, xOffset + TEGEL_SIZE / 2, yOffset + TEGEL_SIZE / 2 - 2);

                        if (model.win) {
                            WinschermView wView = new WinschermView();
                            WinschermPresenter wPresenter = new WinschermPresenter(wView, model, stage);
                            Stage wStage = new Stage();
                            wStage.initOwner(view.getScene().getWindow());
                            wStage.initModality(Modality.APPLICATION_MODAL);        //je kan niet verder spelen tot er een beslissing wordt gemaakt in het winscherm
                            wStage.setScene(new Scene(wView));
                            wStage.setResizable(false);
                            wStage.show();

                            model.win = false;
                            wStage.setTitle("You Win!");
                            wStage.getIcons().add(new Image("icon2048.jpg"));


                        }
                        if (model.lose) {
                            GameOverView goView = new GameOverView();
                            GameOverPresenter goPresenter = new GameOverPresenter(goView, model, stage);
                            Stage goStage = new Stage();
                            goStage.initOwner(view.getScene().getWindow());
                            goStage.initModality(Modality.APPLICATION_MODAL);
                            model.resetGame();       //anders als je gameover schermt sluit kan je verderspelen, maar uiteraard niet eer bewegen en dan zit je vast
                            goStage.setScene(new Scene(goView));
                            goStage.show();
                            goStage.setResizable(false);
                            model.lose = false;
                            goStage.setTitle("Game Over");
                            goStage.getIcons().add(new Image("icon2048.jpg"));
                            //System.out.println(goStage.getWidth() + ", " + goStage.getHeight());

                        }

                    }
                    gc.setFont(Font.font("Verdana", 18));
                    if (model.score > Integer.parseInt(getHoogsteScore())) {
                        gc.fillText("Highscore: " + model.score, 165, 370);
                        gc.fillText("Score: " + model.score, 165, 350);
                    }else {
                        gc.fillText("Score: " + model.score, 165, 350);
                        gc.fillText("Highscore: " + getHoogsteScore(), 165, 370);
                    }

                }
            }
        }.start();
    }

    private int offsetCoors(int arg) { //arg is x of y waarde
        return arg * (16 + 64) + 16;
    }


    public String getHoogsteScore() {
        File mijnBestand = new File("C:\\2048\\Scores\\scores.txt");
        int hoogsteScore = 0;

        try {
            List<String> mijnRegelsTekst = Files.readAllLines(mijnBestand.toPath());
            String[] mijnCelGegevens = new String[100];
            for (String huidigeScore : mijnRegelsTekst) {
                mijnCelGegevens = huidigeScore.split(";");
            }

            for (String score : mijnCelGegevens) {
                int mijnScore = Integer.parseInt(score);
                if (mijnScore > hoogsteScore) {
                    hoogsteScore = mijnScore;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(hoogsteScore);
    }


}
