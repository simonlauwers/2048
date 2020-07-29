package be.kdg.game.view.spel;

import be.kdg.game.model.GameModel;

public class SpelPresenter {
    private GameModel model;
    private SpelView view;

    public SpelPresenter(GameModel model, SpelView view){
        this.model = model;
        this.view = view;
        this.addEventhandlers();
        this.updateview();
    }


    private void addEventhandlers(){

    }

    private void updateview(){


    }
}
