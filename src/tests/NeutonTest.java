package tests;

import org.junit.Assert;
import org.junit.Test;
import sample.GaussSolver;
import sample.NeutonSolver;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by vladimir on 20.01.17.
 */
public class NeutonTest {

    //Parallel lines
    @Test
    public void testNeuton1(){
        int size = 3;
        Function<Double[],Double>[][] matrix = new Function[size][size];
        matrix[0][0] = (x)->0.0;
        matrix[0][1] = (x)->0.0;
        matrix[0][2] = (x)-> -7.0;

        matrix[1][0] = (x)->0.0;
        matrix[1][1] = (x)->0.0;
        matrix[1][2] = (x)-> 0.0;

        matrix[2][0] = (x)-> -7.0;
        matrix[2][1] = (x)-> 0.0;
        matrix[2][2] = (x)-> 0.0;

        Function<Double[],Double>[] vector = new Function[size];//{(x,y) -> -1.0, (x,y) -> 13.0, (x,y) -> 9.0};

        vector[0] = (y) -> 7.0 * y[2];
        vector[1] = (y) -> 0.0;
        vector[2] = (y) -> -28.0 + 7.0 * y[0];

        Double[] begV = {4.0, 2.0, 0.0};

        NeutonSolver nSolver = new NeutonSolver(matrix, vector, size, begV);

        Double[] result = nSolver.neutonSolve();

//        double[] expRes = {3, 5 ,4};
//
//        boolean flag = true;
//        for(int i = 0 ; i < size; i++){
//            if(result[i] != expRes[i]){
//                flag = false;
//                break;
//            }
//        }
       // Assert.assertTrue(flag);
        System.out.println("Test2: ");
        for(int i =0 ; i< size; i++)
            System.out.println(result[i] + " ");
        System.out.println("\n");
    }


    @Test
    public void testNeuton2(){
        int size = 3;
        //double[][] matrix = {{2, 0, -7},{0, 2, 0},{-7, 0, 0}};
        Function<Double[],Double>[][] matrix = new Function[size][size];
        matrix[0][0] = (x)-> -3.0 + 2 * x[2];
        matrix[0][1] = (x)->0.0;
        matrix[0][2] = (x)-> -3.0 + 2 * x[0];

        matrix[1][0] = (x)-> 0.0;
        matrix[1][1] = (x)-> -4.0 + 2 * x[2];
        matrix[1][2] = (x)-> -4.0 + 2 * x[1];

        matrix[2][0] = (x)->  2.0 * x[0];
        matrix[2][1] = (x)->  2.0 * x[1];
        matrix[2][2] = (x)-> 0.0;

        Function<Double[],Double>[] vector = new Function[size];//{(x,y) -> -1.0, (x,y) -> 13.0, (x,y) -> 9.0};

        vector[0] = (y) -> 3.0 - 2 * y[0] * y[2];
        vector[1] = (y) -> 4.0 - 2 * y[1] * y[2];
        vector[2] = (y) -> 25.0 - y[0]*y[0] - y[1]*y[1];

        Double[] begV = {4.0, 2.0, 0.0};

        NeutonSolver nSolver = new NeutonSolver(matrix, vector, size, begV);

        Double[] result = nSolver.neutonSolve();

//        double[] expRes = {3, 5 ,4};
//
//        boolean flag = true;
//        for(int i = 0 ; i < size; i++){
//            if(result[i] != expRes[i]){
//                flag = false;
//                break;
//            }
//        }
        // Assert.assertTrue(flag);
        System.out.println("Test2: ");
        for(int i =0 ; i< size; i++)
            System.out.println(result[i] + " ");
        System.out.println();
        double func1 = -3 + 2 * result[2]*result[0];
        double func2 = -4 + 2 * result[2]* result[0];
        System.out.println("Func1 : " + func1 + "\nFunc2: " + func2 + "\n\n");
    }

    //Perpendicular lines
    //Parallel lines
    @Test
    public void testNeuton3(){
        int size = 3;
        Function<Double[],Double>[][] matrix = new Function[size][size];
        matrix[0][0] = (x)->0.0;
        matrix[0][1] = (x)->0.0;
        matrix[0][2] = (x)-> -7.0;

        matrix[1][0] = (x)->0.0;
        matrix[1][1] = (x)->0.0;
        matrix[1][2] = (x)-> 0.0;

        matrix[2][0] = (x)-> -7.0;
        matrix[2][1] = (x)-> 0.0;
        matrix[2][2] = (x)-> 0.0;

        Function<Double[],Double>[] vector = new Function[size];//{(x,y) -> -1.0, (x,y) -> 13.0, (x,y) -> 9.0};

        vector[0] = (y) -> 7.0 * y[2];
        vector[1] = (y) -> 0.0;
        vector[2] = (y) -> 21.0 + 7.0 * y[0];

        Double[] begV = {4.0, 2.0, 0.0};

        NeutonSolver nSolver = new NeutonSolver(matrix, vector, size, begV);

        Double[] result = nSolver.neutonSolve();

//        double[] expRes = {3, 5 ,4};
//
//        boolean flag = true;
//        for(int i = 0 ; i < size; i++){
//            if(result[i] != expRes[i]){
//                flag = false;
//                break;
//            }
//        }
        // Assert.assertTrue(flag);
        System.out.println("Test3: ");
        for(int i =0 ; i< size; i++)
            System.out.println(result[i] + " ");
        System.out.println("\n");
    }

}
