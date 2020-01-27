package ru.spbstu.telematics.java;

import org.junit.Test;
import org.junit.Assert;

public class MatrixAdditionTest
{
    @Test
    public void testAddition() {

        int[][]A = {{1,2,3},{4,5,6}};
        int[][]B = {{1,2,3},{4,5,6}};
        int[][] C = MatrixAddition.addition(A,B);
        int[][] result = {{2,4,6},{8,10,12}};
        Assert.assertTrue(MatrixAddition.isEqual(result, C));
    }

    @Test
    public void simpleTest(){
        int[][]A = null;
        int[][]B = null;
        int[][] C = MatrixAddition.addition(A,B);
        int[][] result = null;
        Assert.assertTrue(MatrixAddition.isEqual(result, C));
    }

    @Test
    public void simpleTest2() {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{1, 2, 3}};
        int[][] C = MatrixAddition.addition(A, B);
        int[][] result = null;
        Assert.assertTrue(MatrixAddition.isEqual(result, C));
    }

    @Test
    public void simpleTest3() {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{1, 2} , {4, 5, 6}};
        int[][] C = MatrixAddition.addition(A, B);
        int[][] result = null;
        Assert.assertTrue(MatrixAddition.isEqual(result, C));
    }

    @Test
    public void simpleTest4() {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{1, 2, 3}, {4, 5}};
        int[][] C = MatrixAddition.addition(A, B);
        int[][] result = null;
        Assert.assertTrue(MatrixAddition.isEqual(result, C));
    }

}
