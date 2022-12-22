package com.example.dodge_game;


import Entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class HelloApplication extends Application {
    private static final int width = 805;
    private static final int height = 605;

    private boolean moving = true;



    Image move1 = new Image("move1.png");
    Image move2 = new Image("move2.png");

    Player player = new Player(move1,(width/2),0);

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Dodge game");
        stage.setResizable(false);

        Canvas canvas = new Canvas(width, height);
        canvas.requestFocus();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Rectangle rectangle = new Rectangle(50, 50, 100, 100);


        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1000), e -> run1(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> run1(gc)));
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(3), new KeyValue(player.rec.xProperty(),600)
        ));

        Scene scene = new Scene(new StackPane(canvas));


        stage.setScene(scene);
        stage.show();
        tl.play();
        timeline.play();
    }

    private void run1(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(5, 5, width-10, height-10);

        //gc.drawImage(player.getImage(), player.getPositionX(), 302);

    }

    public static void main(String[] args) {
        launch();
    }
}