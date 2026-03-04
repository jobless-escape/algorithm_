import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 1) 노드 수 찾기
        int n = 0;
        for (int[] e : edges) n = Math.max(n, Math.max(e[0], e[1]));

        // 2) 인접/차수
        List<List<Integer>> g = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());

        int[] indeg = new int[n + 1];
        int[] outdeg = new int[n + 1];

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g.get(u).add(v);
            outdeg[u]++; indeg[v]++;
        }

        // 3) 시작 노드 찾기: out>=2 && in==0
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (outdeg[i] >= 2 && indeg[i] == 0) {
                start = i;
                break;
            }
        }

        int donut = 0, stick = 0, eight = 0;

        // 4) 가지별 경로 추적 (토큰 마킹으로 local-visit 구현)
        int[] mark = new int[n + 1];
        int token = 1;

        for (int child : g.get(start)) {
            int cur = child;
            while (true) {
                if (outdeg[cur] == 0) {           // 막대
                    stick++;
                    break;
                }
                if (outdeg[cur] >= 2) {           // 8자
                    eight++;
                    break;
                }
                if (mark[cur] == token) {         // 현재 경로에서 재방문 → 도넛
                    donut++;
                    break;
                }
                mark[cur] = token;                // 경로 방문 표시

                // outdeg == 1 이므로 다음 노드로
                cur = g.get(cur).get(0);
            }
            token++;
        }

        return new int[] { start, donut, stick, eight };
    }
}
