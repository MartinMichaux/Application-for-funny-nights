package MiniGames.TicTacToe;

import Data.Data;
import Menu.StartScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TicTacToe extends Application {
    Stage stage;
    String[]players;
    String currentP;
    int[][]board;

    private Pane principal;
    Pane gameBoardRep;
    final int DIMENSION = 3;
    final int CELL_SIZE = 100;

    Label winner;

    public TicTacToe(Stage stage) throws Exception {
        this.stage = stage;
        principal = new Pane();
        board = new int[DIMENSION][DIMENSION];
        paint();
        this.players= Data.getPlayersName();
        currentP = players[0];
        start(stage);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        principal.setBackground(Data.createBackGround());
        Parent root = principal;
        stage.setTitle("MiniGames.Blokus Game Group 15");
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * method that paint each case of board when an update is done
     */
    public void paint() {
        //Clear previous cells
        principal.getChildren().clear();
        gameBoardRep = new GridPane();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {

                Rectangle tile = new Rectangle(CELL_SIZE, CELL_SIZE);
                tile.setFill(Color.WHITE);
                tile.setStrokeWidth(2.0);
                tile.setStroke(Color.BLACK);

                Text text = new Text();
                text.setScaleX(5);
                text.setScaleY(5);
                text.setTranslateX(50);

                action(tile,text,i,j);
                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                GridPane.setRowIndex(text, i);
                GridPane.setColumnIndex(text, j);

                gameBoardRep.getChildren().addAll(tile,text);
            }
        }
        gameBoardRep.setTranslateX(250);gameBoardRep.setTranslateY(250);

        Button exit = new Button("Back to Menu");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new StartScreen().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        exit.setTranslateX(380);exit.setTranslateY(650);

        winner = new Label("");
        winner.setTextFill(Color.WHITE);
        winner.setScaleX(5);winner.setScaleY(5);
        winner.setTranslateX(270);winner.setTranslateY(150);


        principal.getChildren().addAll(gameBoardRep,exit,winner);
    }

    public void action(Rectangle tile,Text text,int i,int j){
        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(currentP.equals(players[0])){
                    text.setText("X");
                    board[i][j]=1;
                }else{
                    text.setText("O");
                    board[i][j]=2;
                }
                refresh();
                event.consume();
            }
        });
    }

    public void refresh(){
        int index = 1;
        if(currentP==players[1]){
            index=2;
        }
        if(noOneCanPlay()){
            winner.setText("No one won !");
            gameBoardRep.setDisable(true);
        }else{
            if(checkWin(index)){
                winner.setText(players[index-1] + " won !");
                gameBoardRep.setDisable(true);
            }else{
                nextPlayer(index);
            }
        }

    }

    public boolean noOneCanPlay(){
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if(board[i][j]!=1&&board[i][j]!=2) return false;
            }
        }
        return true;
    }
    public boolean checkWin(int symb){
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j <DIMENSION; j++) {
                //vertical checking
                if (board[0][j] == symb && board[1][j] == symb && board[2][j] == symb)return true;
            }
            //horizontal checking
            if(board[i][0] == symb && board[i][1] == symb && board[i][2] == symb)return true;
        }
        //diagonal checking
        if (board[0][0] == symb && board[1][1] == symb && board[2][2] == symb)return true;
        if (board[0][2] == symb && board[1][1] == symb && board[2][0] == symb)return true;
        return false;
    }

    public void nextPlayer(int index){
        if(currentP.equals(players[players.length-1])){
            currentP = players[0];
        }else{
            currentP = players[index];
        }
    }
}
