package content;

import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.animation.AnimationTimer;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class Main extends Application {

    private Stage window;                   //entire window handler
    private Scene mainScene;                //scene that holds whole content
    private GridPane grid;                  //layout to store our labels


    private final static int size = 40;     //in our board of labels width=height
    Label[][] board = new Label[size][size];

    Image bg;                               //background
    Image red;
    Image yellow;

    /*  initializing just our board             */
    private void initBoard(){
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++){
                board[x][y] = new Label();          //calling constructor

                //must-have variables for lambda expression
                final int finalX = x;
                final int finalY = y;
                
                board[x][y].setOnMouseEntered(e->{              //lambda expression for event handling
                    System.out.println(finalX + "," + finalY);  //display coordinates in console
                    final Image newTileImage;                   //another constant that holds reference for assigning new color of a tile

                    //doesn't work while event is mouseEntered.
                    //In order to change the newTileImage use setOnMouseClicked
                    if(e.getButton().equals(MouseButton.PRIMARY))
                        newTileImage = red;
                    else
                        newTileImage = yellow;

                    board[finalX][finalY].setGraphic(new ImageView(newTileImage));   //assigning new Image for event-caller
                });

                board[x][y].setGraphic(new ImageView(bg));                           //always fill board with background color
            }
    }

    /*  initializing as method name */
    private void initLabelToGridAssignment() {
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++){
                GridPane.setConstraints(board[x][y],x,y);   //bind board tile to proper COLUMN and ROW in our grid
                grid.getChildren().add(board[x][y]);        //finally add each of them
            }
    }

    /*  initializing multiple Image references to class variables   */
    private void initImages() {
        //basic textures, we can use Colors instead of Images
        bg = new Image(getClass().getResourceAsStream("resources/grey.png"));   //bg - background
        red = new Image(getClass().getResourceAsStream("resources/red.png"));
        yellow = new Image(getClass().getResourceAsStream("resources/yellow.png"));
    }

    /*  initializing variables/resources only   */
    @Override                                //override javaFX native method
    public void init(){
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));//10 pixel padding on each side
        grid.setVgap(0);                         //vertical spacing between each label
        grid.setHgap(0);                         //horizontal spacing

        initImages();               //call Images initialization for further use
        initBoard();                //call board initialization method
        initLabelToGridAssignment();//bind board tiles to proper place in grid
    }


    @Override                               //override javaFX native method
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;              //must-have assignment
        window.setTitle("Snake - first");   //window TITLE

        mainScene = new Scene(grid,10+40*20+10,10+40*20+10);//10 left padding, 40*20 tiles space, 10 right padding
        window.setScene(mainScene);
        window.show();                                      //display mainScene on the window

        /* gameloop. we must mull this over, how we'll handle everything in here    */
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //user input
                //update
                //render
                //sync
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);//must-have call (javaFX standard)
    }
}
