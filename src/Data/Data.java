package Data;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Data {
    private static String[] playersName = {"PLAYER 1","PLAYER 2"};

    private static String game = "Tic-Tac-Toe";

    private static int DIMENSION = 20;

    public static Background createBackGround(){
        Image image = new Image(String.valueOf(Data.class.getResource("background.jpg")),800,800,false,true);

        // Image image = new Image("https://images.hdqwalls.com/wallpapers/simple-gray-background-4k-br.jpg",800,800,false,true);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        Background background = new Background(backgroundImage);
        return background;
    }

    public static String getGame() {
        return game;
    }

    public static void setGame(String game) {
        Data.game = game;
    }

    public static String[] getPlayersName() {
        return playersName;
    }

    public static void setPlayersName(String[] playersName) {
        Data.playersName = playersName;
    }

    public static int getDIMENSION() {
        return DIMENSION;
    }

    public static void setDIMENSION(int DIMENSION) {
        Data.DIMENSION = DIMENSION;
    }

}
