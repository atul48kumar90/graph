package org.example.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Classroom {

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

    public static void createGraph2(ArrayList<Edge> graph[])
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
    public static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int n, int d)
        {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public static class Pair2 implements Comparable<Pair2> {
        int node;
        int cost;

        Pair2(int n, int c)
        {
            this.node= n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair2 p2)
        {
            return this.cost - p2.cost;
        }
    }

    public static void prims(ArrayList<Edge> graph[], int V)
    {
        PriorityQueue<Pair2> pq = new PriorityQueue<>();
        pq.add(new Pair2(0,0));

        boolean[] vis = new boolean[V];
         int mstCost = 0;

         while(!pq.isEmpty())
         {
            Pair2 curr = pq.remove();
            vis[curr.node] = true;
            mstCost += curr.cost;

            for(int i=0; i<graph[curr.node].size(); i++)
            {
                Edge e = graph[curr.node].get(i);
                if(!vis[e.dest])
                {
                    pq.add(new Pair2(e.dest, e.wt));
                }
            }
         }

         System.out.println(" min cost of mst: " + mstCost);
    }

    public static void dijkstra(ArrayList<Edge> graph[], int src, int V)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        for(int i=0; i<V; i++) {
            if(i != src)
            {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean vis[] = new boolean[V];

        pq.add(new Pair(0,0));

        while(!pq.isEmpty())
        {
            Pair curr = pq.remove();
            if(!vis[curr.node])
            {
                vis[curr.node] = true;

                for(int i=0; i<graph[curr.node].size();i++)
                {
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u] + e.wt < dist[v])
                    {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }

            for(int i=0; i<V; i++)
            {
                System.out.print(dist[i] + " ");
            }
            System.out.println();

        }

    }

    public static void BellmonFord(ArrayList<Edge> graph[], int src, int V)
    {
        int[] dist = new int[V];
        for(int i=0; i<V; i++)
        {
            if(i!=src)
            {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        for(int k=0; k<V-1; k++)
        {
            for(int i=0; i<V; i++)
            {
                for(int j=0; j<graph[i].size(); j++)
                {
                    Edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;

                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[v])
                    {
                        dist[v] = dist[u] + e.wt;
                    }
                }
            }
        }
        
        //-ve weight cycle
        for(int i=0; i<V; i++)
        {
            for(int j=0; j<graph[i].size(); j++)
            {
                Edge e = graph[i].get(j);
                int u = e.src;
                int v = e.dest;

                if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[v])
                {
                    System.out.println("graph has -ve weight cycle");
                }
            }
        }
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
