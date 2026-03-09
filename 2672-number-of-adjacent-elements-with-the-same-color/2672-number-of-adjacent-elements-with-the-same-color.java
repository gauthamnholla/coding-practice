class Solution {
    public int[] colorTheArray(int n, int[][] queries) {
        int[] colors = new int[n];
        int m = queries.length;
        int[] res = new int[m];
        int count = 0;
        for(int i = 0; i < m; i++){
            int idx = queries[i][0];
            int color = queries[i][1];
            count -= check(colors, idx, n);
            colors[idx] = color;
            count += check(colors, idx, n);
            res[i] = count;
        }
        return res;
    }

    public int check(int[] colors, int i, int n){
        if(colors[i] == 0){
            return 0;
        }
        int count = 0;
        if(i > 0 && colors[i] == colors[i - 1]){
            count++;
        }
        if(i < n - 1 && colors[i] == colors[i + 1]){
            count++;
        }
        return count;
    }
}