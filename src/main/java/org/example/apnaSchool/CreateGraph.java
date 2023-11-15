package org.example.apnaSchool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CreateGraph {

    static class Edge{
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int wt)
        {
            this.src = s;
            this.dest = d;
            this.wt = wt;
        }

        public Edge(int s, int d)
        {
            this.src = s;
            this.dest = d;
        }

    }



    private static void createGraph(ArrayList<Edge> graph[]) {

        for(int i=0;i<graph.length;i++)
        {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1, 2));
        graph[0].add(new Edge(0,2, 2));
        graph[1].add(new Edge(1,2, 10));
        graph[1].add(new Edge(1,3, 0));
        graph[2].add(new Edge(2,0, 2));
        graph[2].add(new Edge(2,1, 10));
        graph[2].add(new Edge(2,3, -1));
        graph[3].add(new Edge(3,1, 0));
        graph[3].add(new Edge(3,2, -1));
    }

    public static void createGraph2(ArrayList<Edge> graph[])
    {
        for(int i=0;i<graph.length;i++)
        {
            graph[i] = new ArrayList<>();
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

    private static void printGraph(ArrayList<Edge> graph[])
    {
        for(int i=0;i<graph[2].size();i++)
        {
            Edge e = graph[2].get(i);
            System.out.println(e.dest+ "  ");
        }
    }

    public static void BFS(ArrayList<Edge> graph[], int V, boolean[] vis, int start)
    {
        Queue<Integer> q = new LinkedList<>();


        q.add(start);

        while(!q.isEmpty())
        {
            int curr = q.remove();
            if(vis[curr] == false)
            {
                System.out.println(curr+"  ");
                vis[curr] = true;

                for(int i=0;i<graph[curr].size();i++)
                {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void DFS(ArrayList<Edge> graph[], int curr, boolean[] vis)
    {
        System.out.println(curr + "  ");
        vis[curr] = true;

        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e = graph[curr].get(i);
            if(vis[e.dest] == false)
            {
                DFS(graph, e.dest,vis);
            }
        }
    }

    public static void printAllPath(ArrayList<Edge> graph[], boolean vis[], int curr, String path, int tar )
    {
        if(curr == tar)
        {
            System.out.println(path);
            return;
        }

        for(int i=0;i<graph[curr].size();i++)
        {
            Edge e = graph[curr].get(i);
            if(!vis[e.dest])
            {
                vis[curr] = true;
                printAllPath(graph, vis, e.dest, path+e.dest,tar);
                vis[curr] = false;
            }
        }
    }

    public static void main(String[] args) {

        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph2(graph);
        //printGraph(graph);

        boolean[] vis = new boolean[V];
        for(int i=0;i<V;i++)
        {
            if(vis[i] == false)
            {
                DFS(graph,i,vis);
            }
        }

//        DFS(graph, 0, vis);

    }
}
