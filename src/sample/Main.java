package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.plaf.SplitPaneUI;
import java.beans.EventHandler;
import java.util.DoubleSummaryStatistics;


public class Main extends Application {

    private enum State {
        Free, IsDragged, lineSet1, lineSet2
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

        FlowPane infoPane = new FlowPane();
        infoPane.setAlignment(Pos.CENTER_LEFT);
        infoPane.setStyle(toolStyles);
        infoPane.setHgap(10);
        Label xLabel = new Label("X: 0");
        Label yLabel = new Label("Y: 0");
        Label infoLabel = new Label("Info:");
        infoPane.getChildren().addAll(xLabel,yLabel,infoLabel);

        Pane drawBox = new Pane();
        MyGroup groupShapes = new MyGroup();
        MyGroup suppShapes = new MyGroup();


        drawBox.setStyle(paintAreaStyles);
        drawBox.getChildren().addAll(groupShapes, suppShapes);

        Button pointAddButton = new Button("Add point");
        Button lineAddButton = new Button("Add line");
        lineAddButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(state == State.Free){
                    state = State.lineSet1;
                    infoLabel.setText("Info: choose first point of line");
                }else
                    state = State.Free;
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                groupShapes.getChildren().clear();
                state = State.Free;
                infoLabel.setText("Info:");
            }
        });

        SplitPane split = new SplitPane();
        Button makeFixedButton = new Button("Make fixed");
        Button makeParallButton = new Button("Make parallel");



        toolsPane.getChildren().addAll(pointAddButton, lineAddButton, clearButton);
        toolsPane.getChildren().add(split);
        toolsPane.getChildren().addAll(makeFixedButton, makeParallButton);



//        Rectangle rect = new Rectangle(100,100,100,100);
//        rect.setOpacity(1);
//        rect.setStroke(Color.BLACK);
//        rect.setStrokeWidth(3);
//        rect.setFill(null);
//        drawBox.getChildren().add(rect);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(toolsPane);
        borderPane.setCenter(drawBox);
        borderPane.setBottom(infoPane);
        borderPane.setMinSize(600, 500);

        Scene scene = new Scene(borderPane);

        MyLine myline = new MyLine(100,100,200,200);
        groupShapes.addLine(myline);

        drawBox.setOnMouseMoved(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                xLabel.setText("X:" + event.getX());
                yLabel.setText("Y:" + event.getY());
                suppShapes.getChildren().clear();
                if(state == State.lineSet2) {
                    MyLine line = new MyLine(buffPosX,buffPosY,x,y);
                    suppShapes.addLine(line);
                }
            }
        });
        drawBox.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                switch(state){
                    case lineSet1:
                        buffPosX = x;
                        buffPosY = y;
                        state = State.lineSet2;
                        infoLabel.setText("Info: choose second point of line");
                        break;
                    case lineSet2:
                        MyLine line = new MyLine(buffPosX,buffPosY,x,y);
                        groupShapes.addLine(line);
                        suppShapes.getChildren().clear();
                        state = State.Free;
                        infoLabel.setText("Info:");
                        break;
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
