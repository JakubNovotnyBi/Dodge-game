package com.example.dodge_game;


import Entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int width = 805;
    private static final int height = 605;

    private boolean moving = true;

    private int jumpLength = 150;

    private int[] position = {363 - jumpLength, 363, 363 + jumpLength};

    private byte currentPosition = 1;

    int time = 0;


    Image move1 = new Image("move1.png");
    Image move2 = new Image("move2.png");

    Image background = new Image("background.png");

    private boolean is2ndImage = true;

    private boolean isRunning = false;

    Text text = new Text();



    Player player = new Player(move1, (width / 2 - (move1.getWidth()) / 2), (height - move1.getHeight()));

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Dodge game");
        stage.setResizable(false);

        Canvas canvas = new Canvas(width, height);
        Canvas canvas1 = new Canvas(width, height);
        canvas.requestFocus();
        canvas1.requestFocus();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();



        text.setText("Time  " + Integer.toString(time));
        text.setX((width * 0.5));
        text.setY(225);

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(200), e -> run1(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        Timeline tl1 = new Timeline(new KeyFrame(Duration.millis(1000), e -> timer()));
        tl1.setCycleCount(Timeline.INDEFINITE);

        Timeline tl2 = new Timeline(new KeyFrame(Duration.millis(10), e -> run2(gc1)));
        tl2.setCycleCount(Timeline.INDEFINITE);






        Scene scene = new Scene(new StackPane(canvas));
        Scene scene1 = new Scene(new StackPane(canvas1));


        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            //System.out.println(keyEvent.getCode().getCode());

            if (keyEvent.getCode() == KeyCode.A) {
                currentPosition--;
                if (currentPosition >= 0)
                    player.setPositionX(position[currentPosition]);

                else
                    currentPosition++;
            } else if (keyEvent.getCode() == KeyCode.D) {
                currentPosition++;
                if (currentPosition <= 2)
                    player.setPositionX(position[currentPosition]);

                else
                    currentPosition--;
            }

        });


        scene1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && !isRunning){
                    isRunning = true;
                    tl.play();
                    tl1.play();
                    stage.setScene(scene);
                    stage.show();
                    tl2.stop();
                }
            }
        });

        stage.setScene(scene1);
        stage.show();
        tl2.play();

    }

    private void run2(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(5, 5, width - 5, height - 5);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(30));

        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("Click to start", width/2, height/2);

    }


    private void run1(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(5, 5, width - 5, height - 5);
        gc.drawImage(background, 0, 0, width, height);

        player.setImage(is2ndImage ? move1 : move2);
        is2ndImage = !is2ndImage;


        gc.setFont(new Font(30));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(text.getText(), text.getX(), text.getY());

        gc.drawImage(player.getImage(), player.getPositionX(), player.getPositionY());


    }

    private void timer() {
        time += 1;
        text.setText("Time  " + Integer.toString(time));
    }

    public static void main(String[] args) {
        launch();
    }
}