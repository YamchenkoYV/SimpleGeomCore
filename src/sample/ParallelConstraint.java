package sample;

import javafx.scene.shape.Line;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by vladimir on 19.01.17.
 * Create the parallel constraint of line1 relative to line2
 */
public class ParallelConstraint implements Constraint {

    private MyLine line1;

    private MyLine line2;

    private Function<Double[], Double>[][] hesseMatrix;

    public ParallelConstraint(MyLine l1, MyLine l2){
        line1 = l1;
        line2 = l2;
    }

    @Override
    public ArrayList<MyLine> getMembers() {
        ArrayList<MyLine> members = new ArrayList<>();
        members.add(line1);
        members.add(line2);
        return members;
    }

    @Override
    public Function<Double[],Double> getGX(double dX, double dL) {
        double sX1 = line1.getStartX();
        double dirX2 = line2.getXDir();
        Function<Double[],Double> func = (y)->(-2.0 * (sX1 - y[0]) - dirX2 * y[2]);
        return func;
    }

    @Override
    public Function<Double[],Double> getGY(double dY, double dL) {
        double sY1 = line1.getStartY();
        double dirY2 = line2.getYDir();
        Function<Double[],Double> func = (y)-> -2.0 * (sY1 - y[1]) - dirY2 * y[2];
        return func;
    }

    @Override
    public Function<Double[],Double> getGL(double dX, double dY) {
        double dirX2 = line2.getXDir();
        double dirY2 = line2.getYDir();
        double sX1 = line1.getStartX();
        double sY1 = line1.getStartY();
        Function<Double[],Double> func = (y)->dirX2 * (sX1 - y[0]) - dirY2 * (sY1 - y[1]);
        return func;
    }

    @Override
    public Function<Double[], Double>[][] getHesseMatrix() {
        return hesseMatrix;
    }

    private void createHesseMatr(){
        Function<Double[], Double>[][] hesseMtrx = new Function[3][3];
        hesseMtrx[0][0] = (y)->2.0;
        hesseMtrx[0][1] = (y)->0.0;
        hesseMtrx[0][2] = (y)->- line2.getXDir();
        hesseMtrx[1][0] = (y)->0.0;
        hesseMtrx[1][1] = (y)->2.0;
        hesseMtrx[1][2] = (y)->- line2.getYDir();
        hesseMtrx[2][0] = (y)->- line2.getXDir();
        hesseMtrx[2][1] = (y)->- line2.getYDir();
        hesseMtrx[2][2] = (y)->0.0;

        hesseMatrix = hesseMtrx;
    }


}
