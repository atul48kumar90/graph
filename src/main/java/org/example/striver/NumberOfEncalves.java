package org.example.striver;


/*
 * 1020. Number of Enclaves
Solved
Medium
Topics
Companies
Hint
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 */
import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int first;
    int second;

    Pair(int f, int s)
    {
        this.first = f;
        this.second = s;
    }
}

class NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[n][m];

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(i==0 || i==n-1 || j==0 || j==m-1)
                {
                    if(grid[i][j] == 1)
                    {
                        q.add(new Pair(i,j));
                        vis[i][j] = 1;
                    }
                }
            }
        }

        int[] delRow = {-1, 0, +1, 0};
        int[] delCol = {0, +1, 0, -1};

        while(!q.isEmpty())
        {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();

            for(int i=0; i<4; i++)
            {
                int newR = row + delRow[i];
                int newC = col + delCol[i];

                if(newR >=0 && newR <n && newC >=0 && newC <m && vis[newR][newC] == 0 && grid[row][col] == 1)
                {
                    q.add(new Pair(newR, newC));
                    vis[newR][newC] = 1;
                }
            }
        }

        int count = 0;

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(grid[i][j] == 1 && vis[i][j] == 0)
                {
                    count++;
                }
            }
        }

        return count;
    }
}
