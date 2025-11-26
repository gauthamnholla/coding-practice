import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Solution.isPrintable(new int[][]{{1,1,1},{3,1,3}});
    }
}

class Solution {
    static int[] head = new int[61];
    static int[] next = new int[61<<6];
    static int[] to = new int[61<<6];
    static int[] inDegree = new int[61];
    static int[] queue = new int[61];
    static int cnt, l, r, n,m;

    static int[] top = new int[61];
    static int[] down = new int[61];
    static int[] left = new int[61];
    static int[] right = new int[61];
    static boolean[][] isConnected = new boolean[61][61];
    static boolean[] seen = new boolean[61];

    public static boolean isPrintable(int[][] targetGrid) {
        n = targetGrid.length;
        m = targetGrid[0].length;
        build();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int color = targetGrid[i][j];
                set.add(color);
                top[color] = Math.min(top[color], i);
                down[color] = Math.max(down[color], i);
                left[color] = Math.min(left[color], j);
                right[color] = Math.max(right[color], j);
            }
        }

        for (int i : set) {
            for (int j = top[i]; j <= down[i]; j++) {
                for (int k = left[i]; k <= right[i]; k++) {
                    int color = targetGrid[j][k];
                    if(color != i && !isConnected[i][color]){
                        insert(color, i);
                        isConnected[i][color] = true;
                    }
                }
            }
        }
        for (int u : set) {
            if(inDegree[u] == 0){
                queue[r++] = u;
            }
        }

        while (l < r){
            int size = r-l;
            while (size-->0){
                int u = queue[l++];
                seen[u] = true;
                for(int i = head[u], v; i > 0; i = next[i]){
                    v = to[i];
                    if(--inDegree[v] == 0)queue[r++] = v;
                }
            }
        }
        for (int u : set) {
            if(!seen[u])return false;
        }
        return true;
    }

    static void insert(int u, int v){
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
        inDegree[v]++;
    }

    static void build(){
        cnt = 1;
        l = r = 0;
        for (int i = 1; i < 61; i++) {
            head[i] = inDegree[i] = 0;
            top[i] = left[i] = 61;
            down[i] = right[i] = -1;
            Arrays.fill(isConnected[i],false);
            seen[i] = false;
        }
    }
}