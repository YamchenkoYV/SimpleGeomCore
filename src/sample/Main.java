package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.swing.plaf.SplitPaneUI;
import java.beans.EventHandler;
import java.util.DoubleSummaryStatistics;


public class Main extends Application {

    private enum State {
        Free, IsDragged
    }

    private State state = State.Free;

    private double curPosX;
    private double curPosY;

    private double buffPosX;
    private double buffPosY;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Geometric");

        String toolStyles =
                "-fx-background-color: #aaaaaa;" +
                        "-fx-border-color: #ffffff;" ;
        String paintAreaStyles =
                "-fx-background-color: #dddddd;" +
                        "-fx-border-color: #111111;" ;

        FlowPane toolsPane = new FlowPane();
        toolsPane.setOrientation(Orientation.VERTICAL);
        toolsPane.setMinSize(100, 500);
        toolsPane.setStyle(toolStyles);
        toolsPane.setAlignment(Pos.TOP_CENTER);
        toolsPane.setVgap(30);
        toolsPane.setPadding(new Insets(30));

        Button pointAddButton = new Button("Add point");
        Button lineAddButton = new Button("Add line");
        Button clearButton = new Button("Clear");
        SplitPane split = new SplitPane();
        Button makeFixedButton = new Button("Make fixed");
        Button makeParallButton = new Button("Make parallel");



        toolsPane.getChildren().addAll(pointAddButton, lineAddButton, clearButton);
        toolsPane.getChildren().add(split);
        toolsPane.getChildren().addAll(makeFixedButton, makeParallButton);


        Canvas canvas = new Canvas(500, 500);
        canvas.setStyle(paintAreaStyles);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(toolsPane);
        borderPane.setCenter(canvas);
        borderPane.setMinSize(600, 500);

        Scene scene = new Scene(borderPane);

        canvas.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!state.equals(State.IsDragged)) {
                    buffPosX = event.getX();
                    buffPosY = event.getY();
                    state = State.IsDragged;
                } else {
                    state = State.Free;
                }
            }
        });

        canvas.setOnMouseMoved(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (state.equals(State.IsDragged)) {
                    gc.setStroke(Color.CORAL);
                    gc.setLineWidth(3);

                    gc.strokeLine(buffPosX, buffPosY, event.getX(), event.getY());
                }
            }
        });

        clearButton.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
            }
        });

        //gc.strokeLine(100,100,200,200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
