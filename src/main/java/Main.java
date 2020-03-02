import matrix.MatrixMultiplication;
import matrix.ThreadMatrixMultiplication;
import matrix.interfaces.IMultiplication;

public class Main {

    private static Integer[][] A;
    private static Integer[][] B;
    private static Integer[][] C;
    private static final Integer A_HEIGHT = 1000;
    private static final Integer A_WIDTH_B_HEIGHT = 1000;
    private static final Integer B_WIDTH = 100;

    /**
     * test speed of matrix multiplication
     * @param multiplication matrix multiplication interface
     * @return time taken by multiplication
     */
    public static Long timeTestMultiplicatiom(IMultiplication multiplication) {
        Long timeStart = System.nanoTime();
        C = multiplication.multiplication(A,B);
        return System.nanoTime() - timeStart;
    }

    public static void main(String[] args) {
        A = new Integer[A_HEIGHT][A_WIDTH_B_HEIGHT];
        B = new Integer[A_WIDTH_B_HEIGHT][B_WIDTH];
        for (int i = 0; i < A_HEIGHT; i++) {
            for (int j = 0; j < A_WIDTH_B_HEIGHT; j++) {
                A[i][j] = i*j+1;
            }
        }
        for (int i = 0; i < A_WIDTH_B_HEIGHT; i++) {
            for (int j = 0; j < B_WIDTH; j++) {
                B[i][j] = i+j+1;
            }
        }
        System.out.println(timeTestMultiplicatiom(new ThreadMatrixMultiplication(4)));
        System.out.println(timeTestMultiplicatiom(new MatrixMultiplication()));
    }

}
