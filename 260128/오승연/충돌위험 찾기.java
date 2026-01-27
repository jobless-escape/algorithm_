import java.io.*;
import java.util.*;

class Solution {
    static int[][] move;
    static int conflict, n;
    static int[][] goal;
    static boolean[] flag;
    static int[] routeIdx; // ★ 각 로봇의 현재 목적지 인덱스
    
    public int solution(int[][] points, int[][] routes) {
        conflict = 0;
        n = routes.length;
        move = new int[n][2];
        flag = new boolean[n];
        routeIdx = new int[n];
        
        // 1. move에 시작포인트 저장하기
        for (int i = 0; i < n; i++) {
            move[i][0] = points[routes[i][0] - 1][0];
            move[i][1] = points[routes[i][0] - 1][1];
            routeIdx[i] = 1; // 다음 목적지부터 시작
        }

        // 도착지점 배열 (헷갈리니까 미리 넣어두자)
        goal = new int[n][2];
        for (int i = 0; i < n; i++) {
            goal[i][0] = points[routes[i][routeIdx[i]] - 1][0];
            goal[i][1] = points[routes[i][routeIdx[i]] - 1][1];
        }

        // 충돌감지 더해주기
        isConflict();

        // 2. routes에 맞게 움직이다가 충돌감지해주기 (x 먼저 움직이고 y 움직여주기)
        while (true) {
            // 움직이기
            for (int i = 0; i < n; i++) {
                if (flag[i]) continue;

                if (move[i][0] > goal[i][0]) {
                    move[i][0]--;
                } else if (move[i][0] < goal[i][0]) {
                    move[i][0]++;
                } else {
                    if (move[i][1] > goal[i][1]) {
                        move[i][1]--;
                    } else if (move[i][1] < goal[i][1]) {
                        move[i][1]++;
                    }
                }
            }

            // 충돌감지 더해주기
            isConflict();

            // 도착한 로봇 처리
            for (int i = 0; i < n; i++) {
                if (flag[i]) continue;

                if (Arrays.equals(move[i], goal[i])) {
                    routeIdx[i]++;

                    // 다음 목적지가 있으면 goal 덮어쓰기
                    if (routeIdx[i] < routes[i].length) {
                        goal[i][0] = points[routes[i][routeIdx[i]] - 1][0];
                        goal[i][1] = points[routes[i][routeIdx[i]] - 1][1];
                    } else {
                        flag[i] = true; // 모든 목적지 도착
                    }
                }
            }

            // 목적지에 다 도달했는지
            if (isGoal()) break;
        }

        return conflict;
    }
    
    static void isConflict() {
        Map<List<Integer>, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                List<Integer> key = Arrays.asList(move[i][0], move[i][1]);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        for (int count : map.values()) {
            if (count > 1) conflict++;
        }
    }
    
    static boolean isGoal() {
        for (int i = 0; i < n; i++) {
            if (!flag[i]) return false;
        }
        return true;
    }
}
