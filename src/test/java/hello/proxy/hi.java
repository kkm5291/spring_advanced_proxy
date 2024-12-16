package hello.proxy;

import org.junit.jupiter.api.Test;
import java.util.*;

public class hi {
    @Test
    void process() {
        // given
        int n = 9;
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int answer = Integer.MAX_VALUE;

        List<Integer>[] graph = new ArrayList[n+1];

        for(int i=1; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=0; i<wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];

            graph[a].remove(Integer.valueOf(b));
            graph[b].remove(Integer.valueOf(a));

            answer = Math.min(answer, Math.abs(bfs(graph, a) - bfs(graph, b)));

            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(answer
        );
    }

    static int bfs(List<Integer>[] graph, int start) {
        boolean[] visited = new boolean[graph.length+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int cnt = 1;
        while(!q.isEmpty()) {
            start = q.poll();
            List<Integer> temp = graph[start];

            for(int t : temp) {
                if (!visited[t]) {
                    visited[t] = true;
                    q.add(t);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    @Test
    void test() {
        List<Integer>[] list = new ArrayList[2];
        list[0] = new ArrayList<>();
        list[1] = new ArrayList<>();

        list[0].add(1);
        list[0].remove(1);
        list[0].remove(Integer.valueOf(1));
        System.out.println(Arrays.toString(list));
    }
}
