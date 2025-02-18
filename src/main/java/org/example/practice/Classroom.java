package org.example.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Classroom {

    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest)
        {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[])
    {
        for(int i=0; i<graph.length; i++)
        {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));

        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));
    }

    public static void BFS(ArrayList<Edge> graph[], int V, boolean[] visited, int start)
    {
        // Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>();

        q.add(start);

        while(!q.isEmpty())
        {
            int curr = q.remove();
            if(visited[curr] == false){
            System.out.print(curr + " ");
            visited[curr] = true;

            for(int i=0; i<graph[curr].size(); i++)
            {
                Edge e = graph[curr].get(i);
                q.add(e.dest);
            }
        }
        }
    }

    public static void DFS(ArrayList<Edge> graph[], int curr, boolean[] vis)
    {
        System.out.print(curr + " ");
        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(vis[e.dest] == false)
            {
                DFS(graph, e.dest, vis);
            }
        }
    }

    public static void printAllPath(ArrayList<Edge> graph[], boolean[] vis, int curr, String path, int tar)
    {
        if( curr == tar)
        {
            System.out.println(path);
            return;
        }

        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(!vis[e.dest])
            {
                vis[curr] = true;
                printAllPath(graph, vis, e.dest, path+e.dest+" ", tar);
                vis[curr] = false;
            }
        }
    }

    public static boolean isCyclicDirected(ArrayList<Edge> graph[], boolean[] vis, boolean[] rec, int curr)
    {
        vis[curr] = true;
        rec[curr] = true;

        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(rec[e.dest])
            {
                return true;
            } else if(!vis[e.dest])
            {
                if(isCyclicDirected(graph, vis, rec, e.dest)){
                    return true;
                }
            }
        }
        rec[curr] = false;
        return false;
    }

    public static void topSortutil(ArrayList<Edge> graph[], int curr, boolean[] vis, Stack<Integer> stack)
    {
        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++)
        {
            Edge e = graph[curr].get(i);
            if(!vis[e.dest])
            {
                topSortutil(graph, e.dest, vis, stack);
            }
        }
        stack.push(curr);
    }

    public static void topSort(ArrayList<Edge> graph[] , int V)
    {
        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<V; i++)
        {
            if(!vis[i])
            {
                topSortutil(graph, i, vis, stack);
            }
        }

        while(!stack.isEmpty())
        {
            System.out.print(stack.pop() + " ");
        }
    }

    public static boolean isCyclicUndirected(ArrayList<Edge> graph[], int curr, boolean[] vis, int par)
    {
        vis[curr] = true;

        for(int i=0; i<graph[curr].size();i++)
        {
            Edge e = graph[curr].get(i);
            if(vis[e.dest] && e.dest !=par)
            {
                return true;
            } else if(!vis[e.dest])
            {
                if(isCyclicUndirected(graph, e.dest, vis, curr))
                {
                    return true;
                }
            }
        }


        return false;
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        boolean[] vis = new boolean[V];

        // for(int i=0; i<V; i++)
        // {
        //     if(vis[i] == false)
        //     {
        //         BFS(graph, V, vis, i);
        //     }
        // }
        //BFS(graph, V);

        // for(int i=0; i<graph[2].size(); i++)
        // {
        //     Edge e = graph[2].get(i);
        //     System.out.println(e.dest);
        // }
        
        //DFS

        // for(int i=0; i<V; i++)
        // {
        //     if(vis[i] == false)
        //     {
        //         DFS(graph, i, vis);
        //     }
        // }

        //DFS(graph, 0, vis);

        //printAllPath(graph, vis, 0, "0 ", 5);
        // boolean[] rec = new boolean[V];

        // for(int i=0; i<V; i++)
        // {
        //     if(!vis[i])
        //     {
        //         boolean isCycle = isCyclicDirected(graph, vis, rec, 0);
        //         System.out.println(isCycle);
        //         break;
        //     }
        // }
        // boolean res = isCyclic(graph, vis, new boolean[V], 0);
        // System.out.println(res);

        topSort(graph, V);

    }
}
