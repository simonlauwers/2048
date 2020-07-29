package be.kdg.game.view.highscores;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HighScoreView extends GridPane {
    private Label lblTitel;
    private Label lbl1;
    private Label lbl2;
    private Label lbl3;
    private Label lbl4;
    private Label lbl5;
    private VBox vbox;
    private int nr1;
    private int nr2;
    private int nr3;
    private int nr4;
    private int nr5;
    private Button btnBack;


    public HighScoreView() {
        this.getHoogsteScore();
        this.initaliaseNodes();
        this.layoutNodes();
    }

    private void initaliaseNodes() {
        this.lblTitel = new Label("Highscores");
        this.lbl1 = new Label("1: " + nr1);
        this.lbl2 = new Label("2: " + nr2);
        this.lbl3 = new Label("3: " + nr3);
        this.lbl4 = new Label("4: " + nr4);
        this.lbl5 = new Label("5: " + nr5);
        this.vbox = new VBox(10);
        this.btnBack = new Button("Back");

        //Font
        Font titleFont = new Font("Verdana", 50);
        Font scoreFont = new Font("Verdana", 30);

        lbl1.setFont(scoreFont);
        lbl1.setTextFill(Color.web("#FFD700", 1));

        lbl2.setFont(scoreFont);
        lbl2.setTextFill(Color.web("#C0C0C0", 1));

        lbl3.setFont(scoreFont);
        lbl3.setTextFill(Color.web("#B08D57", 1));
        btnBack.setFont(scoreFont);

        lbl4.setFont(scoreFont);
        lbl5.setFont(scoreFont);
        lblTitel.setFont(titleFont);
    }

    private void layoutNodes() {
        this.setPadding(new Insets(20));


        this.add(lblTitel, 0, 0, 1, 1);
        this.add(vbox, 0, 1);

        vbox.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, btnBack);
        vbox.setAlignment(Pos.CENTER);

        GridPane.setHalignment(lblTitel, HPos.CENTER);
        GridPane.setHalignment(lbl1, HPos.CENTER);
        GridPane.setHalignment(lbl2, HPos.CENTER);
        GridPane.setHalignment(lbl3, HPos.CENTER);
        GridPane.setHalignment(lbl4, HPos.CENTER);
        GridPane.setHalignment(lbl5, HPos.CENTER);
        GridPane.setHalignment(btnBack, HPos.CENTER);
        setVgap(30);

    }

    public String getHoogsteScore() {
        File mijnBestand = new File("C:\\2048\\Scores\\scores.txt");
        int hoogsteScore = 0;

        try {
            List<String> mijnRegelsTekst = Files.readAllLines(mijnBestand.toPath());
            String[] mijnCelGegevens = new String[100];

            for (String s : mijnRegelsTekst) {
                mijnCelGegevens = s.split(";");
            }
            int[] intArr = sorteerScores(mijnCelGegevens);
            Arrays.sort(intArr);

            nr1 = intArr[99];
            nr2 = intArr[98];
            nr3 = intArr[97];
            nr4 = intArr[96];
            nr5 = intArr[95];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(hoogsteScore);
    }

    public int[] sorteerScores(String[] alleGespeeldeScores) {
        int[] scoreAlsInt = new int[100];

        for (int i = 0; i < alleGespeeldeScores.length; i++) {
            scoreAlsInt[i] = Integer.parseInt(alleGespeeldeScores[i]);
        }
        return scoreAlsInt;

    }

    public Button getBtnBack() {
        return btnBack;
    }
}


