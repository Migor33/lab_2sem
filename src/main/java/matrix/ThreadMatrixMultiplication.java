package matrix;

import matrix.interfaces.IMultiplication;

public class ThreadMatrixMultiplication implements IMultiplication {

    private class ThreadMultiplication implements Runnable {

        Integer x;
        Integer y;
        public ThreadMultiplication(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        /**
         * thread, computing part of matrix cells
         */
        public void run() {
            int j = y;
            for (int i = x; i < height; i++) {
                for (; j < width; j += countOfTreads) {
                    Integer temp = 0;
                    for (int k = 0; k < n; k++) {
                        temp += A[i][k]*B[k][j];
                    }
                    C[i][j] = temp;
                }
                j = j % width;
            }
        }
    }


    private ThreadMultiplication[] runnableThreads;
    private Integer countOfTreads;

    private Integer[][] A;
    private Integer[][] B;
    private Integer[][] C;
    private Integer height;
    private Integer width;
    private Integer n;

    public ThreadMatrixMultiplication(Integer n) {
        countOfTreads = n;
        runnableThreads = new ThreadMultiplication[n];
    }

    /**
     * multiplies two matrices
     * @param A first matrix
     * @param B second matrix
     * @return result
     */
    public Integer[][] multiplication(Integer[][] A, Integer[][] B) {
        this.A = A;
        this.B = B;
        height = A.length;
        width = B[0].length;
        n = B.length;
        C = new Integer[height][width];
        Thread[] threads = new Thread[countOfTreads];
        for (int i = 0; i < countOfTreads && i < height * width; i++) {
            runnableThreads[i] = new ThreadMultiplication(i / width,i % width);
             threads[i] = new Thread(runnableThreads[i]);
             threads[i].start();
        }
        for (int i = 0; i < countOfTreads; i++) {
            if (threads[i].isAlive()) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Integer[][] C = this.C;
        this.C = null;
        return C;
    }
}
