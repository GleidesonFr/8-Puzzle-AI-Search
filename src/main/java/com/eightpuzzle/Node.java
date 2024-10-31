package com.eightpuzzle;

import java.util.Arrays;

public class Node {
    
    private int[][] matrix = new int[3][3];
    private int zeroRow, zeroCol;

    public Node(int[][] matrix, int zeroRow, int zeroCol){
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, 3);
        }

        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
    }

    public int getZeroCol() {
        return zeroCol;
    }

    public int getZeroRow() {
        return zeroRow;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }if(!(obj instanceof Node)){
            return false;
        }
        Node node = (Node) obj;
        return Arrays.deepEquals(matrix, node.matrix);        
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
