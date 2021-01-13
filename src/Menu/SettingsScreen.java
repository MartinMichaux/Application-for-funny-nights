package Menu;

import Data.Data;
import Menu.menu.MenuTitle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsScreen extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private String[] playersName;

    //each button contains a function that is run everytime it is pressed

    private Stage stage;

    private StackPane root = new StackPane();
    private VBox menuBox = new VBox(-5);

    @Override
    public void start(Stage primaryStage) throws Exception {
        root.setId("settings-screen-pane");
        Background background = Data.createBackGround();
        root.setBackground(background);
        Scene scene = new Scene(root, 800, 800);
        addContent();
        scene.setFill(Color.BLACK);
        //TODO fix issues with the settings
        primaryStage.setTitle("MiniGames.Blokus Settings Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
        playersName = Data.getPlayersName();
    }

    private void addContent() {
        addTitle();
        double lineX = WIDTH / 2. - 100;
        double lineY = HEIGHT / 3. + 50;
        addMenu(lineX + 5, lineY + 5);

        startAnimation();
    }

    private void addTitle() {
        MenuTitle title = new MenuTitle("BLOKUS:Settings");
        title.setTranslateX(WIDTH / 2. - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3.);
        root.getChildren().add(title);
    }

    private void addMenu(double x, double y) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        TextField opt2TextField = new TextField("");
        TextField opt3TextField = new TextField("");

        Text opt2 = new Text("Player 1 name :");
        Text opt3 = new Text("Player 2 name :");

        String optionsG[] = {"Random","Tic-Tac-Toe","Other Games..."};

        grid.add(opt2, 0, 2);

        grid.add(opt2TextField, 1, 2);

        grid.add(opt3, 0, 3);

        grid.add(opt3TextField, 1, 3);

        Text opt6 = new Text("Game To Play");
        grid.add(opt6, 0, 7);
        // create a choiceBox
        ChoiceBox c1P1 = new ChoiceBox(FXCollections.observableArrayList(optionsG));
        c1P1.getSelectionModel().select(0);
        grid.add(c1P1, 1, 7);

        Button exitButton = new Button("Back to Menu");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Data.setGame((String) c1P1.getSelectionModel().getSelectedItem());
                    if(opt2TextField.getText().equals("")){
                        playersName[0] = "PLAYER 1";
                    }else{
                        playersName[0] = opt2TextField.getText();
                    }

                    if(opt3TextField.getText().equals("")){
                        playersName[1] = "PLAYER 2";
                    }else{
                        playersName[1] = opt3TextField.getText();
                    }
                    Data.setPlayersName(playersName);
                    new StartScreen().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        grid.add(exitButton,0,9);
        exitButton.setTranslateX(80);
        root.getChildren().add(grid);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1));
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

}