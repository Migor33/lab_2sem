package matrix;

import matrix.interfaces.IMultiplication;

public class MatrixMultiplication implements IMultiplication {
    /**
     * multiplies two matrices
     * @param A first matrix
     * @param B second matrix
     * @return result
     */
    public Integer[][] multiplication(Integer[][] A, Integer[][] B) {
        Integer height = A.length;
        Integer width = B[0].length;
        Integer n = B.length;
        Integer[][] C = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Integer temp = 0;
                for (int k = 0; k < n; k++) {
                    temp += A[i][k]*B[k][j];
                }
                C[i][j] = temp;
            }
        }
        return C;
    }
}
