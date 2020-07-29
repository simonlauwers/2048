package be.kdg.game.view.startscherm;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartschermView extends GridPane {
    private Label lblTitle;
    private Button btnPlay;
    private Button btnQuit;
    private Button btnHighScores;
    private VBox vbox;

    public StartschermView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes(){
        this.btnPlay = new Button("Play");
        this.btnHighScores = new Button("Highscores");
        this.btnQuit = new Button("Quit");
        this.lblTitle = new Label("2048");
        this.vbox = new VBox(10);

        //Fonts
        Font titleFont = new Font("Verdana", 80);
        Font btnFont =new Font("Verdana", 30);

        lblTitle.setFont(titleFont);
        btnPlay.setFont(btnFont);
        btnHighScores.setFont(btnFont);
        btnQuit.setFont(btnFont);

        //Styling
        btnPlay.setPrefWidth(300);
        btnQuit.setPrefWidth(300);
        btnHighScores.setPrefWidth(300);
    }

    public void layoutNodes(){
        this.setPadding(new Insets(20));

        this.add(lblTitle, 0, 0, 2, 1); //col, row, colspan, rowspan
        this.add(vbox, 0, 3);

        vbox.getChildren().addAll(btnPlay, btnHighScores, btnQuit);
        vbox.setAlignment(Pos.CENTER);


        GridPane.setHalignment(lblTitle, HPos.CENTER);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setHalignment(btnHighScores, HPos.CENTER);
        GridPane.setHalignment(btnPlay, HPos.CENTER);


        setVgap(30);
    }



    //Getters


    public Button getBtnPlay() {
        return btnPlay;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Button getBtnHighScores() {
        return btnHighScores;
    }

}
