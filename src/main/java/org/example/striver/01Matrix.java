package org.example.striver;


/*
 * 542. 01 Matrix
Solved
Medium
Topics
Companies
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.
 */
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Step 1: Initialize queue with all `0`s and mark `1`s as Integer.MAX_VALUE
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j}); // Push all `0`s
                } else {
                    mat[i][j] = Integer.MAX_VALUE; // Mark `1`s as unvisited
                }
            }
        }

        // Step 2: BFS directions (up, down, left, right)
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        // Step 3: BFS traversal
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];

            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];

                if (newX >= 0 && newY >= 0 && newX < rows && newY < cols &&
                    mat[newX][newY] > mat[x][y] + 1) { // Update only if shorter path found
                    mat[newX][newY] = mat[x][y] + 1; // Update distance
                    queue.offer(new int[]{newX, newY}); // Push into queue for further updates
                }
            }
        }

        return mat;
    }
}
