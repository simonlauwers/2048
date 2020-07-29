package be.kdg.game.model;

import javafx.scene.paint.Color;

/**
 * De klasse Tegel bevat alle atributen en eigenschappen van de tegels
 * die op het bord verschijnen behalve de TEGEL_GROOTTE (zie klasse Spel)
 * Alle kleuren worden hier opgeslagen in een switch met cases tot en met 2048
 */
public class Tegel {
    protected int waarde;


    /**
     * De eerste constructor is voor het geval er geen waarde wordt meegegeven als parameter.
     * Deze maakt een Tegel aan met standaardwaarde 0.
     */
    public Tegel() {
        this.waarde = 0;
    }

    /**
     * De tweede constructor is voor het geval er wel een waarde als parameter wordt meegegeven;
     * Deze maakt een Tegel aan met de meegegeven waarde.
     * @param waarde het getal dat op de tegel zal komen.
     */
    public Tegel(int waarde) {
        this.waarde = waarde;
    }

    /**
     * Een tegel kan ook leeg zijn vandaar deze boolean.
     * @return 0 (de waarde van elk leeg vak is 0)
     */
    public boolean isLeeg() {
        return waarde == 0;
    }

    /**
     * Deze switch bevat de respectievelijke kleur dat elke tegel moet krijgen
     * bij een bepaalde waarde.
     * @return In geval dat de waarde 0 is of bij een waarde
     * groter dan 2048 zal het de beige kleur krijgen die na het return statement geschreven staat.
     */
    public Color getBackground() {
        switch (waarde) {
            case 2:		return Color.rgb(238, 228, 218, 1.0);
            case 4: 	return Color.rgb(237, 224, 200, 1.0);
            case 8: 	return Color.rgb(242, 177, 121, 1.0);
            case 16: 	return Color.rgb(245, 149, 99, 1.0);
            case 32: 	return Color.rgb(246, 124, 95, 1.0);
            case 64:	return Color.rgb(246, 94, 59, 1.0 );
            case 128:	return Color.rgb(237, 207, 114, 1.0);
            case 256: 	return Color.rgb(237, 204, 97, 1.0);
            case 512: 	return Color.rgb(237, 200, 80, 1.0);
            case 1024: 	return Color.rgb(237, 197, 63, 1.0);
            case 2048: 	return Color.rgb(237, 194, 46, 1.0);
        }
        return Color.rgb(205, 193, 180, 1.0);
    }

    /**
     * De nummers in de tegels krijgen ook een kleur.
     * Bij een waarde onder de  krijgen ze de bruine kleur en bij 16
     * of meer krijgen ze de witte kleur zoals bij het origenel spel.
     * @return de kleur voor het nummer in de tegel
     */
    public Color getNummerKleur() {
        Color nummerKleur;
        if(waarde < 16) {
            nummerKleur = Color.rgb(119, 110, 101, 1.0); //0x776e65
        } else {
            nummerKleur = Color.rgb(249, 246, 242, 1.0);    //0xf9f6f2
        }
        return nummerKleur;
    }

}