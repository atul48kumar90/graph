package org.example.striver;

/*
 * 785. Is Graph Bipartite?
Solved
Medium
Topics
Companies
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.
 */
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        int[] vis = new int[V];
        for(int i=0; i<V; i++)
        {
            vis[i] = -1;
        }

        for(int i=0; i<V; i++)
        {
            if(vis[i] == -1)
            {
                if(check(i, V, vis, graph) == false)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check(int start, int v, int[] color, int[][] graph)
    {
        color[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty())
        {
            int node = q.peek();
            q.remove();

            for(int adjacentNode : graph[node]) {
                if(color[adjacentNode] == -1)
                {
                    color[adjacentNode] = 1 - color[node];
                    q.add(adjacentNode);
                } else if(color[adjacentNode] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}