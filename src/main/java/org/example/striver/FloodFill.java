package org.example.striver;

/*
 * 733. Flood Fill
Solved
Easy
Topics
Companies
Hint
You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

 

Example 1:

Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2

Output: [[2,2,2],[2,2,0],[2,0,1]]
 */
class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if(originalColor != color)
        {
            dfs(image, sr, sc, originalColor, color);
        }

        return image;
    }

    public void dfs(int[][] image, int i, int j, int originalColor, int color)
    {
        if(i<0 || j<0 || i>= image.length || j>= image[0].length || image[i][j] != originalColor) return;

        image[i][j] = color;

        dfs(image, i+1, j, originalColor, color);
        dfs(image, i-1, j, originalColor, color);
        dfs(image, i, j+1, originalColor, color);
        dfs(image, i, j-1, originalColor, color);
    }
}
