import java.util.*;

public class Solution {
    // 0 = DRAW/unknown, 1 = MOUSE wins, 2 = CAT wins
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] color = new int[n][n][3];
        int[][][] degree = new int[n][n][3];

        // compute degree for each state:
        // turn == 1 (mouse): degree = number of mouse moves = graph[mouse].length
        // turn == 2 (cat): degree = number of cat moves excluding moving to hole (0)
        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][1] = graph[m].length;
                int cnt = 0;
                for (int nxt : graph[c]) {
                    if (nxt != 0) cnt++; // cat cannot move to hole 0
                }
                degree[m][c][2] = cnt;
            }
        }

        Deque<int[]> queue = new ArrayDeque<>();
        // Initialize terminal states:
        // Mouse at hole => Mouse wins
        for (int c = 0; c < n; c++) {
            if (c == 0) continue; // cat at hole is not a valid game state (cat can't go to hole)
            if (color[0][c][1] == 0) {
                color[0][c][1] = 1;
                queue.offer(new int[] {0, c, 1});
            }
            if (color[0][c][2] == 0) {
                color[0][c][2] = 1;
                queue.offer(new int[] {0, c, 2});
            }
        }
        // Cat meets mouse => Cat wins
        for (int i = 0; i < n; i++) {
            if (color[i][i][1] == 0) {
                color[i][i][1] = 2;
                queue.offer(new int[] {i, i, 1});
            }
            if (color[i][i][2] == 0) {
                color[i][i][2] = 2;
                queue.offer(new int[] {i, i, 2});
            }
        }

        // BFS / retrograde propagation
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0], c = cur[1], turn = cur[2];
            int result = color[m][c][turn]; // who wins at this state (1 or 2)

            // find all predecessor states that can move to (m,c,turn)
            // if current turn == MOUSE, previous move was by CAT
            if (turn == 1) {
                // prev states: cat was at prevC and moved to c
                for (int prevC : graph[c]) {
                    if (prevC == 0) continue; // cat can never be at hole
                    int prevM = m;
                    int prevTurn = 2; // previous was cat's turn
                    if (color[prevM][prevC][prevTurn] != 0) continue;

                    // If it's the winner's turn in the predecessor, they can move to a winning state
                    if (prevTurn == result) {
                        color[prevM][prevC][prevTurn] = result;
                        queue.offer(new int[] {prevM, prevC, prevTurn});
                    } else {
                        // otherwise reduce degree; if no moves avoid the opponent win, mark it
                        degree[prevM][prevC][prevTurn]--;
                        if (degree[prevM][prevC][prevTurn] == 0) {
                            color[prevM][prevC][prevTurn] = result;
                            queue.offer(new int[] {prevM, prevC, prevTurn});
                        }
                    }
                }
            } else { // turn == 2, previous move was by MOUSE
                for (int prevM : graph[m]) {
                    int prevC = c;
                    int prevTurn = 1; // previous was mouse's turn
                    if (color[prevM][prevC][prevTurn] != 0) continue;

                    if (prevTurn == result) {
                        color[prevM][prevC][prevTurn] = result;
                        queue.offer(new int[] {prevM, prevC, prevTurn});
                    } else {
                        degree[prevM][prevC][prevTurn]--;
                        if (degree[prevM][prevC][prevTurn] == 0) {
                            color[prevM][prevC][prevTurn] = result;
                            queue.offer(new int[] {prevM, prevC, prevTurn});
                        }
                    }
                }
            }
        }

        // initial state: mouse at 1, cat at 2, mouse to move
        return color[1][2][1]; // 0 if draw, 1 if mouse wins, 2 if cat wins
    }
}
