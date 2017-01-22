package sample;

import java.util.ArrayList;

/**
 * Created by vladimir on 19.01.17.
 */
public class GaussSolver {

    int size;
    double[][] matrix;
    double[] freeVector;
    int[] indexes;

    public GaussSolver(double[][] matrix, double[] vector, int size){
        freeVector = vector;
        this.matrix = matrix;
        this.size = size;

        indexes = new int[size];
        for(int i = 0; i < size; i++)
            indexes[i] = i;

    }

    private void Gauss_straight(){
        double c;
        for(int i = 0; i < size - 1; i++)
            for(int j = i + 1; j < size; j++){
                if(matrix[i][i] != 0.0)
                    swap(i);
                c = - matrix[j][i] / matrix[i][i];
                freeVector[j] += freeVector[i] * c;
                for(int k = i; k < size; k++)
                    matrix[j][k] += matrix[i][k] * c;
            }
    }

    private double[] Gauss_reverse(){
        double[] result = new double[size];
        //Нули на диагоналях(есть нулевые столбцы)
        if(matrix[size - 1][size - 1] != 0.0)
            result[size - 1] = freeVector[size - 1] / matrix[size - 1][size - 1];
        else
            result[size-1] = 0.0;
        for(int i = size - 2; i >= 0; i--){
            result[i] = freeVector[i];
            for(int j = size - 2; j >= i; j--){
                result[i] -= matrix[i][j+1] * result[j + 1];
            }
            if(matrix[i][i] != 0.0)
                result[i] /= matrix[i][i];
            else
                result[i] = 0.0;
        }
        return result;
    }
    public double[] GaussSolve(){
        Gauss_straight();
        return Gauss_reverse();
    }

    public void setFreeVector(double[] freeVector) {
        this.freeVector = freeVector;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void swap(int index){
        for(int i = index; i < size; i++){
            if(matrix[i][index] != 0.0){
                //Then swap these rows
                double[] tempRow = new double[size];
                for (int j = 0; j < size; j++)
                    tempRow[j] = matrix[index][j];
                for (int j = 0; j < size; j++)
                    matrix[index][j] = matrix[i][j];
                for (int j = 0; j < size; j++)
                    matrix[i][j] = tempRow[j];
                //Swap free-vector elements

                double temp = freeVector[index];
                freeVector[index] = freeVector[i];
                freeVector[i] = temp;
            }
        }
    }
}
