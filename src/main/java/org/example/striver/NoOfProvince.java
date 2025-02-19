/*
 * 547. Number of Provinces
Solved
Medium
Topics
Companies
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
 */


import java.util.ArrayList;
import java.util.List;

class NoOfProvince {
    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;
        List<List<Integer>> adlList = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            adlList.add(new ArrayList<>());
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(isConnected[i][j] == 1 && i != j)
                {
                    adlList.get(i).add(j);
                }
            }
        }

        boolean[] vis = new boolean[n];
        int count = 0;
        for(int i=0; i<n; i++)
        {
            if(!vis[i])
            {
                count++;
                dfs(adlList, i, vis);
            }
        }

        return count;
    }

    public void dfs(List<List<Integer>> graph, int curr, boolean[] vis)
    {
        vis[curr] = true;

        for(int neighbour : graph.get(curr))
        {
            if(!vis[neighbour])
            {
                dfs(graph, neighbour, vis);
            }
        }
    }
}