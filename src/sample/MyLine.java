package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.function.Function;


/**
 * Created by vladimir on 19.01.17.
 */
public class MyLine extends Line{
    private Pane drawPane;
    private int id;
    private double xDir;
    private double yDir;
    private ArrayList<Constraint> constraints;

    MyPoint startPoint;
    MyPoint endPoint;

    double strokeWidth = 3;
    Color color = Color.BLUE;

    {
        constraints = new ArrayList<Constraint>();
        startPoint = new MyPoint(10,10);
        endPoint = new MyPoint(10,10);
        startPoint.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                setStartPoint(new MyPoint(x, y));
            }
        });
        endPoint.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                setEndPoint(new MyPoint(x, y));
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStrokeWidth(5);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStrokeWidth(3);
            }
        });
    }

    public MyLine(double x, double y, double x2, double y2){
        super(x,y,x2,y2);
        xDir = x2 - x;
        yDir = y2 - y;
        startPoint.setX(x);
        startPoint.setY(y);
        endPoint.setX(x2);
        endPoint.setY(y2);
        setStrokeWidth(strokeWidth);
        setStroke(color);
        //dP.getChildren().addAll(startPoint,endPoint);
    }

    public MyLine(double x, double y, double x2, double y2, Pane dP, int _id){
        super(x, y, x2, y2);
        xDir = x2 - x;
        yDir = y2 - y;
        drawPane = dP;
        id = _id;
        startPoint.setX(x);
        startPoint.setY(y);
        endPoint.setX(x2);
        endPoint.setY(y2);

        //dP.getChildren().addAll(startPoint,endPoint);
    }

    public void setStartPoint(MyPoint point){
        setStartX(point.getX());
        setStartY(point.getY());
        startPoint.setX(point.getX());
        startPoint.setY(point.getY());
    }
    public void setEndPoint(MyPoint point){
        setEndX(point.getX());
        setEndY(point.getY());
        endPoint.setX(point.getX());
        endPoint.setY(point.getY());
    }

    public void addConstraint(Constraint constr){
        constraints.add(constr);
    }

//    public double[][] prepareConstrMatrix(){
//        int size = constraints.size() + 2;
//        Function<Double[], Double>[][] result = new Function[size][size];
//
//        for(int i = 0; i < size; i++){
//            Function<Double[], Double>[][] constr = constraints.get(i).getHesseMatrix();
//        }
//
//        return result;
//    }

    public int getShapeId() {
        return id;
    }

    public double getXDir() {
        return xDir;
    }

    public double getYDir() {
        return yDir;
    }
}
