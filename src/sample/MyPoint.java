package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Created by vladimir on 20.01.17.
 */
public class MyPoint extends Circle{
    private double x;
    private double y;
    private static double arcDiam = 5;
    private ArrayList<Constraint> constraints;

    {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setRadius(6);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setRadius(5);
            }
        });
    }

    public MyPoint(double _x, double _y){
        super(_x,_y, arcDiam);
        x = _x;
        y = _y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
        setCenterX(x);
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
        setCenterY(y);
    }

    public void addConstraint(Constraint constr){
        constraints.add(constr);
    }

    @Override
    public String toString() {
        return "MyPoint{x: " + x + ", y: " + y +"}";
    }

}
