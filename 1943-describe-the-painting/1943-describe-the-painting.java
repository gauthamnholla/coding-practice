class Solution {
    public List<List<Long>> splitPainting(int[][] segments) {
    long mix[] = new long[100002], sum = 0, last_i = 0;
    boolean ends[] = new boolean[100002];
    List<List<Long>> res = new ArrayList<>();
    for (var s : segments) {
        mix[s[0]] += s[2];
        mix[s[1]] -= s[2];
        ends[s[0]] = ends[s[1]] = true;
    }
    for (int i = 1; i < 100002; ++i) {
        if (ends[i] && sum > 0)
            res.add(Arrays.asList(last_i, (long)i, sum));
        last_i = ends[i] ? i : last_i;
        sum += mix[i];
    }    
    return res;
}
}