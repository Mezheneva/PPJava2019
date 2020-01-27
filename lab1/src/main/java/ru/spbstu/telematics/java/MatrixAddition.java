package ru.spbstu.telematics.java;

public class MatrixAddition {
    public static int[][] addition(int[][] A, int[][] B) {
        if (A == null || B == null || A.length != B.length) {
            return null;
        }

        for(int i = 0; i < A.length - 1; i++){
            if(A[i].length != A[i+1].length || B[i].length != B[i+1].length){
                return null;
            }
        }

        int[][] C = new int[A.length][A[0].length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }


    public static boolean isEqual(int[][] result, int[][] C) {
        if (result == null && C == null)
            return true;
        if (result == null || C == null || result.length != C.length
        || result[0].length != C[0].length)
            return false;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] != C[i][j] )
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(" ");
    }


}
