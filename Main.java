package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    static final int WINDOW_SIZE=800;
    static final int TILES_NUMBER=200;
    static Rectangle[][] board;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();

        Scene scene = new Scene(root, WINDOW_SIZE+5, WINDOW_SIZE+5);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of life");
        primaryStage.show();

        initialization(root);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> step()));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void step()
    {
        boolean[][] black = new boolean[TILES_NUMBER+2][TILES_NUMBER+2];

        for (int row = 1; row <board.length-1; row++) {
            for (int column = 1; column <board.length-1; column++) {
                int number = howManyNeighbours(row, column);
                if((number == 2 || number == 3) && board[row][column].getFill()==Color.BEIGE)
                {
                    black[row][column]=true;
                }
                if(number == 3 && board[row][column].getFill()!=Color.BEIGE)
                {
                    black[row][column]=true;
                }

            }
        }
        for (int row = 1; row < board.length-1; row++) {
            for (int column = 1; column < board.length-1; column++) {
                if(black[row][column])
                {
                    board[row][column].setFill(Color.BEIGE);
                }
                else{
                    board[row][column].setFill(Color.BLACK);
                }
            }
        }
    }

    public static void initialization(GridPane root)
    {
        board = new Rectangle[TILES_NUMBER+2][TILES_NUMBER+2];

        for (int row = 0; row <board.length; row++) {
            for (int column = 0; column <board.length; column++) {
                Rectangle rec = new Rectangle(WINDOW_SIZE/TILES_NUMBER, WINDOW_SIZE/TILES_NUMBER, Color.BLACK);
                if(Math.random()<0.2 && row!=0 && column!=0 && row!=board.length-1 && column != board.length-1)
                {
                    rec.setFill(Color.BEIGE);
                }
                board[row][column] = rec;
                root.add(rec, column, row);
            }
        }
    }

    public static int howManyNeighbours(int row, int column)
    {
        int counter=0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                if(!(i==0 && j==0) && board[row+i][column+j].getFill()==Color.BEIGE)
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
