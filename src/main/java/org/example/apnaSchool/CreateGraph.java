package org.example.apnaSchool;

import java.util.ArrayList;

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
    }

    public static void main(String[] args) {

        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        printGraph(graph);
    }

    private static void createGraph(ArrayList<Edge> graph[]) {

        for(int i=0;i<graph.length;i++)
        {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,2, 2));
        graph[1].add(new Edge(1,2, 10));
        graph[1].add(new Edge(1,3, 0));
        graph[2].add(new Edge(2,0, 2));
        graph[2].add(new Edge(2,1, 10));
        graph[2].add(new Edge(2,3, -1));
        graph[3].add(new Edge(3,1, 0));
        graph[3].add(new Edge(3,2, -1));
    }

    private static void printGraph(ArrayList<Edge> graph[])
    {
        for(int i=0;i<graph[2].size();i++)
        {
            Edge e = graph[2].get(i);
            System.out.println(e.dest+ "  "+ e.wt);
        }
    }
}
