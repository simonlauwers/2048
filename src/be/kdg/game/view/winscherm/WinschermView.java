package be.kdg.game.view.winscherm;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WinschermView extends GridPane {
    private Label lblWin;
    private Button btnContinue;
    private Label lblScore;
    private Label lblHighscore;
    private Button btnQuit;
    private Button btnHighScores;
    private VBox vbox;
    private HBox hbox;
    private Button btnRestart;

    public WinschermView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes() {
        this.btnHighScores = new Button("Highscores");
        this.btnQuit = new Button("Quit");
        this.lblWin = new Label("YOU WIN");
        this.vbox = new VBox(10);       //10 spacing
        this.lblHighscore = new Label("Highscore: " + getHoogsteScore());
        this.lblScore = new Label("Your Score: " + getHuidigeScore());
        this.btnContinue = new Button("Continue");
        this.btnRestart = new Button("Restart");
        this.hbox = new HBox(5);

        //Fonts
        Font titleFont = new Font("Verdana", 50);
        Font btnFont = new Font("Verdana", 25);
        Font scoreFont = new Font("Verdana", 25);
        Font conresFont = new Font("Verdana", 20);

        lblWin.setFont(titleFont);
        lblScore.setFont(scoreFont);
        lblHighscore.setFont(scoreFont);
        btnHighScores.setFont(btnFont);
        btnQuit.setFont(btnFont);
        btnContinue.setFont(conresFont);
        btnRestart.setFont(conresFont);


        //Styling
        btnQuit.setPrefWidth(300);
        btnHighScores.setPrefWidth(300);
        btnContinue.setPrefWidth(147.5);
        btnRestart.setPrefWidth(147.5);

    }

    public Button getBtnRestart() {
        return btnRestart;
    }

    public void layoutNodes() {
        this.setPadding(new Insets(20));

        this.add(lblWin, 0, 0, 2, 1); //col, row, colspan, rowspan
        this.add(vbox, 0, 1);

        hbox.getChildren().addAll(btnContinue, btnRestart);
        vbox.getChildren().addAll(lblScore, lblHighscore, hbox, btnHighScores, btnQuit);
        vbox.setAlignment(Pos.CENTER);


        GridPane.setHalignment(lblWin, HPos.CENTER);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setHalignment(btnHighScores, HPos.CENTER);
        GridPane.setHalignment(btnContinue, HPos.CENTER);
        GridPane.setHalignment(lblHighscore, HPos.CENTER);
        GridPane.setHalignment(lblScore, HPos.CENTER);


        setVgap(42);
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
            String[] mijnCelGegevens = new String[100];
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

    public Label getLblWin() {
        return lblWin;
    }

    public Button getBtnContinue() {
        return btnContinue;
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