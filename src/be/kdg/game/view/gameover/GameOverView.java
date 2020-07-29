package be.kdg.game.view.gameover;

import be.kdg.game.model.GameModel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class GameOverView extends GridPane {
    private Label lblGO;
    private Button btnTryAgain;
    private Label lblScore;
    private Label lblHighscore;
    private Button btnQuit;
    private Button btnHighScores;
    private VBox vbox;
    private GameModel gameModel;

    public GameOverView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes() {
        this.btnTryAgain = new Button("Try Again");
        this.btnHighScores = new Button("Highscores");
        this.btnQuit = new Button("Quit");
        this.lblGO = new Label("GAME OVER");
        this.vbox = new VBox(10);       //10 spacing
        this.lblHighscore = new Label("Highscore: " + getHoogsteScore());
        this.lblScore = new Label("Your Score: " + getHuidigeScore());

        //Fonts
        Font titleFont = new Font("Verdana", 50);
        Font btnFont = new Font("Verdana", 25);
        Font scoreFont = new Font("Verdana", 25);

        lblGO.setFont(titleFont);
        lblScore.setFont(scoreFont);
        lblHighscore.setFont(scoreFont);
        btnTryAgain.setFont(btnFont);
        btnHighScores.setFont(btnFont);
        btnQuit.setFont(btnFont);

        //Styling
        btnTryAgain.setPrefWidth(300);
        btnQuit.setPrefWidth(300);
        btnHighScores.setPrefWidth(300);
    }

    public void layoutNodes() {
        this.setPadding(new Insets(20));

        this.add(lblGO, 0, 0, 2, 1);
        this.add(vbox, 0, 1);

        vbox.getChildren().addAll(lblScore, lblHighscore, btnTryAgain, btnHighScores, btnQuit);
        vbox.setAlignment(Pos.CENTER);


        GridPane.setHalignment(lblGO, HPos.CENTER);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setHalignment(btnHighScores, HPos.CENTER);
        GridPane.setHalignment(btnTryAgain, HPos.CENTER);
        GridPane.setHalignment(lblHighscore, HPos.CENTER);
        GridPane.setHalignment(lblScore, HPos.CENTER);


        setVgap(30);
    }

    public String getHoogsteScore(){
        File mijnBestand = new File("C:\\2048\\Scores\\scores.txt");
        int hoogsteScore = 0;

        try{
            List<String> mijnRegelsTekst = Files.readAllLines(mijnBestand.toPath());
            String[] mijnCelGegevens = new String[100];
            for(String huidigeScore: mijnRegelsTekst) {
                mijnCelGegevens = huidigeScore.split(";");
            }

            for(String score: mijnCelGegevens)
            {
                int mijnScore = Integer.parseInt(score);
                if(mijnScore > hoogsteScore){
                    hoogsteScore = mijnScore;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(hoogsteScore);
    }

    public String getHuidigeScore(){
        File mijnBestand = new File("C:\\2048\\Scores\\scores.txt");
        String mijnHuidigeScore = "";


        try{
            List<String> mijnRegelsTekst = Files.readAllLines(mijnBestand.toPath());
            String[] mijnCelGegevens = new String[100]; //initialiseren op een array van 100
            for(String huidigeScore: mijnRegelsTekst) {
                mijnCelGegevens = huidigeScore.split(";");
            }
            int lengte = mijnCelGegevens.length;
            mijnHuidigeScore = mijnCelGegevens[lengte-1];
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return mijnHuidigeScore;
    }

    //Getters

    public Label getLblGO() {
        return lblGO;
    }

    public Button getBtnTryAgain() {
        return btnTryAgain;
    }

    public Label getLblScore() {
        return lblScore;
    }

    public Label getLblHighscore() {
        return lblHighscore;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnHighScores() {
        return btnHighScores;
    }

    public VBox getVbox() {
        return vbox;
    }
}
