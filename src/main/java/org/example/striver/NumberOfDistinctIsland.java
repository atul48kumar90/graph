package org.example.striver;


/*
 * Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island.
 *  Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).
 */
import java.util.ArrayList;
import java.util.HashSet;

class Pair{
    int first;
    int second;
    
    Pair(int f, int s)
    {
        this.first = f;
        this.second = s;
    }
}
class NumberOfDistinctIsland {
    
    public void dfs(int row, int col, int[][] vis, int[][] grid, ArrayList<String> vector, int row0, int col0)
    {
        vis[row][col] = 1;
        vector.add(toString(row - row0, col-col0));
        int n = grid.length;
        int m = grid[0].length;
        
        int[] delRow = {1, 0, -+1, 0};
        int[] delCol = {0, -1, 0, +1};
        
        for(int i=0; i<4; i++)
        {
            int newR = row + delRow[i];
            int newC = col + delCol[i];
            
            if(newR >=0 && newR < n && newC>=0 && newC <m && vis[newR][newC] == 0 && grid[newR][newC] == 1)
            {
                dfs(newR, newC, vis, grid, vector, row0, col0);
            }
        }
    }
    
    private String toString(int r, int c)
    {
        return Integer.toString(r) + " " + Integer.toString(c);
    }

    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] vis = new int[n][m];
        HashSet<ArrayList<String>> set = new HashSet<>();
        
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(vis[i][j] == 0 && grid[i][j] == 1)
                {
                    ArrayList<String> vector = new ArrayList<>();
                    dfs(i, j, vis, grid, vector, i, j);
                    set.add(vector);
                }
            }
        }
        
        return set.size();
        
    }
}

