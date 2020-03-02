package matrix;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixMultiplicationTest {

    Integer[][] A;
    Integer[][] B;


    @Before
    public void before(){
        A = new Integer[100][100];
        B = new Integer[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                A[i][j] = i*j+1;
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                B[i][j] = i+j+1;
            }
        }
    }

    @Test
    public void multiplication() {
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        ThreadMatrixMultiplication thread1 = new ThreadMatrixMultiplication(1);
        ThreadMatrixMultiplication thread2 = new ThreadMatrixMultiplication(4);
        ThreadMatrixMultiplication thread3 = new ThreadMatrixMultiplication(10);
        Integer[][] C = matrixMultiplication.multiplication(A,B);
        Integer[][] C1 = thread1.multiplication(A,B);
        Integer[][] C2 = thread2.multiplication(A,B);
        Integer[][] C3 = thread3.multiplication(A,B);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                assertEquals(C[i][j],C1[i][j]);
                assertEquals(C[i][j],C2[i][j]);
                assertEquals(C[i][j],C3[i][j]);
            }
        }
    }
}