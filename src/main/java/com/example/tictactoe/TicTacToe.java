package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {

    private Label PlayerXLabel , PlayerYLabel;
    private Button [][] button = new Button[3][3];

    private int xScore = 0,oScore = 0;

    private boolean playerXTurn = true,playerYTurn;
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        //   Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 24 pt ; -fx-font-weight : bold");
        root.setTop(titleLabel);
        root.setPadding(new Insets(20));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Game Board.



        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button click = new Button("");
                click.setStyle("-fx-font-size : 24 pt ; -fx-font-weight : bold");
                click.setPrefSize(100,100);
                click.setOnAction(event->buttonClicked(click) );
                button[i][j] = click;
                grid.add(click,j,i);
            }
        }

        root.setCenter(grid);
        grid.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(grid,Pos.CENTER);

        // Score Section.

        HBox scoreSection = new HBox(20);
        scoreSection.setAlignment(Pos.CENTER);

        PlayerXLabel = new Label("Player X : 0 ");
        PlayerXLabel.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold");
        PlayerYLabel = new Label("Player O : 0 ");
        PlayerYLabel.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold");

        scoreSection.getChildren().addAll(PlayerXLabel,PlayerYLabel);

        root.setBottom(scoreSection);

        return  root;
    }

    private void buttonClicked(Button button){

        if(button.getText().equals("")){
            if(playerXTurn){
                button.setText("X");
            }else{
                button.setText("O");
            }

            playerXTurn = !playerXTurn;
            checkWinner();
        }



    }


    private void checkWinner(){
        // row wise.

        for (int i = 0; i < 3 ; i++) {
            if( button[i][0].getText().equals(button[i][1].getText())  &&   button[i][1].getText().equals(button[i][2].getText())
                && !button[i][0].getText().isEmpty()){
                String winner = button[i][0].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
            }
        }


        // col wise

        for (int i = 0; i < 3 ; i++) {
            if( button[0][i].getText().equals(button[1][i].getText())  &&   button[1][i].getText().equals(button[2][i].getText())
                    && !button[0][i].getText().isEmpty()){
                String winner = button[i][0].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
            }
        }

        //diagonal
        if( button[0][0].getText().equals(button[1][1].getText())  &&   button[1][1].getText().equals(button[2][2].getText())
                && !button[0][0].getText().isEmpty()){
            String winner = button[0][0].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
        }

        if( button[0][2].getText().equals(button[1][1].getText())  &&   button[1][1].getText().equals(button[2][0].getText())
                && !button[0][2].getText().isEmpty()){
            String winner = button[0][2].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
        }

        //tie

        boolean tie = true;

        for (Button row[] : button){
            for(Button button : row){
               if(button.getText().isEmpty()){
                   tie = false;
                   break;
               }
            }
        }

        if(tie){

            showTie();
            resetBoard();

        }




    }

    private void showTie(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setHeaderText("");
        alert.setContentText("Game Over , Its a Tie");
        alert.showAndWait();
    }



    private void showWinner(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText("");
        alert.setContentText("Congratulation's "+winner+" You won the game !");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X")){
            xScore++;
            PlayerXLabel.setText("Player X : "+xScore);
        }else if(winner.equals("O")){
            oScore++;
            PlayerYLabel.setText("Player O : "+oScore);
        }
    }

    private void resetBoard(){
        for (Button row[] : button){
            for(Button button : row){
                button.setText("");
            }
        }


    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToe.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}