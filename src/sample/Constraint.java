package sample;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by vladimir on 19.01.17.
 */
public interface Constraint {

    ArrayList<MyLine> getMembers();

    Function<Double[],Double> getGX(double dX, double dL);

    Function<Double[],Double> getGY(double dY, double dL);

    Function<Double[],Double> getGL(double dX, double dY);

    Function<Double[],Double>[][] getHesseMatrix();
}
