package be.kdg.game.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;


public class GameModel extends javafx.scene.canvas.Canvas {
    protected Tegel[] tegels;
    protected boolean win = false;
    protected boolean lose = false;
    protected int score =0;


    public Tegel[] getTegels() {
        return tegels;
    }

    public GameModel() {
        super(330, 390); //zet canvas size (super verwijst naar parent)
        setFocused(true);
        resetGame();
    }

    public GameModel(double width, double height) {
        super(width, height);
        setFocused(true);
        resetGame();
    }


    public void resetGame() {
        score = 0;
        win = false;
        lose = false;
        tegels = new Tegel[4 * 4];
        for (int i = 0; i < tegels.length; i++) {
            tegels[i] = new Tegel();                       //bord vullen met tegels
        }
        tegelToevoegen();      //starten met twee tegels
        tegelToevoegen();      // ^^^^^
    }

    public void tegelToevoegen() {
        List<Tegel> list = vrijeTegels(); //vrije plaatsen
        if(!vrijeTegels().isEmpty()) { //zijn er nog vrije plaatsen?
            int index = (int) (Math.random() * list.size()) % list.size(); //casten naar int dus afronding
            Tegel legeTegel = list.get(index);
            legeTegel.waarde = Math.random() < 0.9 ? 2 : 4;     //10% kans dat een 4 spawnt 90% kans op 2
        }

    }

    public List<Tegel> vrijeTegels() {
        List<Tegel> list = new ArrayList<>(16);      //(4x4)16 tegels
        for(Tegel c : tegels)
            if(c.isLeeg())
                list.add(c);
        return list;
    }

    public boolean isVol() {
        return vrijeTegels().size() == 0;
    }

    public Tegel TegelOpPositie(int x, int y) {
        return tegels[x + y * 4]; //*4 is nodig omdat het anders enkel de diagonalen doet (1,1) (2,2) enz..
    }

    public boolean kanBewegen() {
        if(!isVol()) return true;          //als het bord niet vol is kan je altijd bewegen
        for(int x = 0; x < 4; x++) {    //iteren over alle tegels
            for (int y = 0; y < 4; y++) {
                Tegel tegel = TegelOpPositie(x, y);
                if ((x < 3 && tegel.waarde == TegelOpPositie(x + 1, y).waarde) ||        //check of er een tegel met dezelfde waarde ernaast staat
                        (y < 3) && tegel.waarde == TegelOpPositie(x, y + 1).waarde) {     //check of er een tegel met dezelfde waarde er boven of onder staat
                    return true;
                }
            }
        }
        return false;
    }

    public boolean vergelijk(Tegel[] lijn1, Tegel[] lijn2) {
        if(lijn1 == lijn2) {
            return true;
        }
        if (lijn1.length != lijn2.length) {
            return false;
        }

        for(int i = 0; i < lijn1.length; i++) {
            if(lijn1[i].waarde != lijn2[i].waarde) {
                return false;
            }
        }
        return true;
    }

    public Tegel[] draaien(int hoek) {
        Tegel[] tegels = new Tegel[4 * 4];
        int offsetX = 3;
        int offsetY = 3;
        if(hoek == 90) {
            offsetY = 0;
        } else if(hoek == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(hoek);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                int newX = (x*cos) - (y*sin) + offsetX;
                int newY = (x*sin) + (y*cos) + offsetY;
                tegels[(newX) + (newY) * 4] = TegelOpPositie(x, y);
            }
        }
        return tegels;
    }

    public Tegel[] verplaatsLijn(Tegel[] oudeLijn) {
        LinkedList<Tegel> list = new LinkedList<Tegel>();
        for(int i = 0; i < 4; i++) {
            if(!oudeLijn[i].isLeeg()){
                list.addLast(oudeLijn[i]);
            }
        }

        if(list.size() == 0) {
            return oudeLijn;
        } else {
            Tegel[] nieuweLijn = new Tegel[4];
            while (list.size() != 4) {
                list.add(new Tegel());
            }
            for(int j = 0; j < 4; j++) {
                nieuweLijn[j] = list.removeFirst();
            }
            return nieuweLijn;
        }
    }

    public Tegel[] voegLijnBijeen(Tegel[] oudeLijn) {
        LinkedList<Tegel> list = new LinkedList<Tegel>();
        for(int i = 0; i < 4 && !oudeLijn[i].isLeeg(); i++) {
            int num = oudeLijn[i].waarde;
            if (i < 3 && oudeLijn[i].waarde == oudeLijn[i+1].waarde) {
                num *= 2;
            score +=num;
            if (num == 2048) {
                    win = true;
                }
                i++;
            }
            list.add(new Tegel(num));
        }

        if(list.size() == 0) {
            return oudeLijn;
        } else {
            while (list.size() != 4) {
                list.add(new Tegel());
            }
            return list.toArray(new Tegel[4]);
        }
    }

    public Tegel[] getLine(int index) {
        Tegel[] resultaat = new Tegel[4];
        for(int i = 0; i < 4; i++) {
            resultaat[i] = TegelOpPositie(i, index);
        }
        return resultaat;
    }

    public void setLijn(int index, Tegel[] re) {
        System.arraycopy(re, 0, tegels, index * 4, 4);
    }

    public void left() {
        boolean moetTegelToevoegen = false;
        for(int i = 0; i < 4; i++) {
            Tegel[] line = getLine(i);
            Tegel[] bijeenGevoegd = voegLijnBijeen(verplaatsLijn(line));
            setLijn(i, bijeenGevoegd);
            if( !moetTegelToevoegen && !vergelijk(line, bijeenGevoegd)) {
                moetTegelToevoegen = true;
            }
        }
        if(moetTegelToevoegen) {
            tegelToevoegen();
        }
    }

    public void right() {
        tegels = draaien(180);
        left();
        tegels = draaien(180);
    }

    public void up() {
        tegels = draaien(270);
        left();
        tegels = draaien(90);
    }

    public void down() {
        tegels = draaien(90);
        left();
        tegels = draaien(270);
    }

    public int getScore() {
        return score;
    }

    public String getScoreTekst()
    {
        return score + ";";
    }

    public void maakFileEnBestandAan()
    {
        File files = new File("C:\\2048\\Scores");
        Path mijnpad = Paths.get("C:\\2048\\Scores\\scores.txt");
        if (!files.exists()) {
            if (files.mkdirs()) { //documentatie: true if and only if the directory was created, along with all necessary parent directories; false otherwise
                try{
                    Files.createFile(mijnpad);
                    String eersteVijfWaardes = "0;0;0;0;0;";
                    Files.write(mijnpad, eersteVijfWaardes.getBytes(), StandardOpenOption.APPEND);
                }
                catch (Exception ex){

                }
            } else {
            }
        }
    }

    public void schrijfScoreWeg()
    {
        Path mijnFile = Paths.get("C:\\2048\\Scores\\scores.txt");
        try{
            Scanner fileScanner = new Scanner(mijnFile);
            String tekst = getScoreTekst();
            Files.write(mijnFile,tekst.getBytes(), StandardOpenOption.APPEND); //path, bytes, StandardOpenOption.APPEND
        }
        catch (Exception ex){
            System.out.println("Fout bij het vinden van de score file");
        }
    }
}