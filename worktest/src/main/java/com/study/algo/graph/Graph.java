package com.study.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图
 * 邻接矩阵存
 *
 * @author ldb
 * @date 2020/08/24
 */
public class Graph {

    private String name;
    // 顶点个数
    private int v;
    // 邻接表数组
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 无向图一条边存两次
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索（Breadth-First-Search）
     * 时间复杂度O(E)
     * 空间复杂度O(V)
     *
     * @param s 顶点s
     * @param t 顶点t
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];
        // 记录当前顶点从哪个顶点来
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        visited[s] = true;
        queue.add(s);
        while (queue.size() > 0) {
            Integer n = queue.removeFirst();
            for (int i = 0; i < adj[n].size(); i++) {
                int q = adj[n].get(i);
                if (!visited[q]) {
                    prev[q] = n;
                    if (q == t) {
                        print1(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && s != t) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public void print1(int[] prev, int s, int t) {
        while (prev[t] != -1 && s != t) {
            System.out.print(t + " ");
            t = prev[t];
        }

    }

    boolean found = false;

    /**
     * 深度优先搜索（Depth-First-Search）
     * 时间复杂度是 O(E)，E 表示边的个数。
     * 空间复杂度就是 O(V)。
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        boolean[] visited = new boolean[v];
        int prev[] = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        recureDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recureDfs(int s, int t, boolean[] visited, int[] prev) {
        if (found) {
            return;
        }
        visited[s] = true;
        if (s == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[s].size(); i++) {
            int q = adj[s].get(i);
            if (!visited[q]) {
                prev[q] = s;
                recureDfs(q, t, visited, prev);
            }

        }
    }

    /**
     * 找n度好友
     *
     * @param s
     * @param n
     */
    public List<Integer> findFriend(int s, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[v];
        queue.add(s);
        visited[s] = true;
        List<Integer> res = new ArrayList<>();
        while (n-->0){
            int size = queue.size();
            while (size -->  0) {
                Integer p = queue.poll();
                for (int i = 0; i < adj[p].size(); i++) {
                    int q = adj[p].get(i);
                    if (!visited[q]) {
                        visited[q] = true;
                        queue.add(q);
                        res.add(q);
                    }

                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.bfs(0, 6);
        System.out.println();
        System.out.println("---------------------");

        graph.dfs(0, 6);
        System.out.println();
        System.out.println("---------------------");
        List<Integer> friends = graph.findFriend(0, 2);
        System.out.println(friends);
    }
}
