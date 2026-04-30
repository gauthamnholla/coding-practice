class Solution {
    public int buttonWithLongestTime(int[][] events) {

        int ans = events[0][0];
        int prev = events[0][1];
        int mx = prev;

        for (int i = 1; i < events.length; i++) {
            int pusher = events[i][0];
            int time = events[i][1];
            int diff = time - prev;

            if (diff > mx) {
                mx = diff;
                ans = pusher;
            } else if (diff == mx && pusher < ans) {
                ans = pusher;}

            prev = time;}

        return ans;}
}