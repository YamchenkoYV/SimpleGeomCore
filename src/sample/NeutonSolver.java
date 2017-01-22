package sample;


import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by vladimir on 19.01.17.
 */
public class NeutonSolver {

    Function<Double[], Double>[][] hesseMatr;
    Function<Double[], Double>[] gradVector;
    double[] suppVector;
    double[][] suppMatr;

    GaussSolver gSolver;

    Double[] curV;
    int size;

    public NeutonSolver(Function<Double[], Double>[][] matrix, Function<Double[], Double>[] vector, int size, Double[] bV){

        gradVector = vector;
        hesseMatr = matrix;
        this.size = size;
        curV = new Double[size];
        suppVector = new double[size];
        suppMatr = new double[size][size];

        transformToSuppVec(bV);
        transformToSuppMatr(bV);

        gSolver = new GaussSolver(suppMatr, suppVector, size);

        curV = bV;
    }

    public Double[] neutonSolve(){
        Double[] prevV = curV.clone();

        double[] result = gSolver.GaussSolve();

        for(int i = 0; i < size; i++)
            curV[i] += result[i];

        while (!checker(prevV, curV)){

            transformToSuppVec(curV);
            transformToSuppMatr(curV);

            gSolver.setFreeVector(suppVector);
            gSolver.setMatrix(suppMatr);

            result = gSolver.GaussSolve();

            prevV = curV.clone();

            for(int i = 0; i < size; i++)
                curV[i] += result[i];
        }
        return curV;
    }

    private void transformToSuppVec(Double[] x){
        for(int i = 0; i < size; i++)
            suppVector[i] = gradVector[i].apply(x);
    }

    private void transformToSuppMatr(Double[] x){
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                suppMatr[i][j] = hesseMatr[i][j].apply(x);
    }


    boolean checker(Double[] firstV, Double[] secondV){
        boolean res = true;
        double eps = 0.000001;
        for(int i = 0; i < size; i++){
            if(Math.abs(firstV[i]-secondV[i]) > eps) {
                res = false;
                break;
            }
        }
        return res;
    }
}

