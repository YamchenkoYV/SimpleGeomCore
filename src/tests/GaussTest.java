package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;
import sample.GaussSolver;

/**
 * Created by vladimir on 20.01.17.
 */

public class GaussTest{

    @Test
    public void testGauss1(){
        int size = 3;
        double[][] matrix = {{3, 2, -5},{2, -1, 3},{1, 2, -1}};
        double[] vector = {-1, 13, 9};

        GaussSolver gSolver = new GaussSolver(matrix, vector, size);

        double[] result = gSolver.GaussSolve();

        double[] expRes = {3, 5 ,4};

        boolean flag = true;
        for(int i = 0 ; i < size; i++){
            if(result[i] != expRes[i]){
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }
    @Test
    public void testGauss2(){
        int size = 3;
        double[][] matrix = {{2, 0, -7},{0, 2, 0},{-7, 0, 0}};
        double[] vector = {0, 0, 0};

        GaussSolver gSolver = new GaussSolver(matrix, vector, size);

        double[] result = gSolver.GaussSolve();

        System.out.println("Result: ");
        for(int i = 0; i < size; i++)
            System.out.println("x["+i+"]: "+result[i]);
    }

}
