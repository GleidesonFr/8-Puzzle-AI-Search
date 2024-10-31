package com.eightpuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthSearch {
    
    private static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public Node breadthSearchAlgorithm(int[][] startMatrix, int[][] targetMatrix){
        Node startNode = findZeroPosition(startMatrix);
        Node targetNode = findZeroPosition(targetMatrix);

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);

        while(!queue.isEmpty()){
            Node currentNode = queue.poll();

            if(currentNode.equals(targetNode)){
                System.out.println("You Win!");
                printMatrix(currentNode.getMatrix());
                return currentNode;
            }

            for(int[] direction : directions){
                int newRow = currentNode.getZeroRow() + direction[0];
                int newCol = currentNode.getZeroCol() + direction[1];

                if(newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3){
                    printMatrix(currentNode.getMatrix());
                    System.out.println();
                    int[][] newMatrix = copyMatrix(currentNode.getMatrix());
                    swap(newMatrix, currentNode.getZeroRow(), currentNode.getZeroCol(), newRow, newCol);
                    Node newNode = new Node(newMatrix, newRow, newCol);

                    if(!visited.contains(newNode)){
                        queue.add(newNode);
                        visited.add(newNode);

                        return currentNode;
                    }
                }
            }
        }

        System.out.println("Solution not found...");
        return null;
    }

    private void printMatrix(int[][] matrix){
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private void swap(int[][] newMatrix, int zeroRow, int zeroCol, int newRow, int newCol) {
        int temp = newMatrix[zeroRow][zeroCol];
        newMatrix[zeroRow][zeroCol] = newMatrix[newRow][newCol];
        newMatrix[newRow][newCol] = temp;
    }

    private int[][] copyMatrix(int[][] matrix) {
        int[][] copy = new int[3][3];

        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, 3);
        }

        return copy;
    }

    private Node findZeroPosition(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == 0){
                    return new Node(matrix, i, j);
                }
            }
        }

        return null;
    }
}
