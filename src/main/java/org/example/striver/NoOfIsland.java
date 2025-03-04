package org.example.striver;


/*
 * 200. Number of Islands
Solved
Medium
Topics
Companies
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 */

 
class NoOfIsland {
    public int numIslands(char[][] grid) {
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        if(n==0) return 0;

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(grid[i][j] == '1')
                {
                    DFSMarking(grid, i, j, n, m);
                    count++;
                }
            }
        }

        return count;
    }

    public void DFSMarking(char[][] grid, int i, int j, int n, int m)
    {
        if(i<0 || j < 0 || i>=n || j>=m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i+1, j, n, m);
        DFSMarking(grid, i-1, j, n, m);
        DFSMarking(grid, i, j+1, n, m);
        DFSMarking(grid, i, j-1, n, m);
    }
}