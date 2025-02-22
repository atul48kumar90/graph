package org.example.striver;


/*
 * 210. Course Schedule II
Solved
Medium
Topics
Companies
Hint
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CourseShedule2 {
    public int[] findOrder(int n, int[][] prerequisites) {
     List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];
        int[] ans = new int[n];

        for(int[] pair : prerequisites)
        {
            int course = pair[0];
            int prerequisite = pair[1];
            if(adj[prerequisite] == null)
            {
                adj[prerequisite] = new ArrayList<>();
            }
            adj[prerequisite].add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            if(indegree[i] == 0)
            {
                q.offer(i);
            }
        }
        int k =0;
        while(!q.isEmpty()) {
            int current = q.poll();
            ans[k++] = current;

            if(adj[current] != null) {
                for(int next : adj[current])
                {
                    indegree[next]--;
                    if(indegree[next] == 0)
                    {
                        q.offer(next);
                    }
                }
            }
        }

        return (k == n) ? ans : new int[0];
    }
}