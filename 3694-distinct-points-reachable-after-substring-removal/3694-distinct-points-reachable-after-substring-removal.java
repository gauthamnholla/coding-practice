class Solution {
        public int distinctPoints(String s, int k) {
        Set<Long> seen = new HashSet<>();
        seen.add(0L);
        int x = 0, y = 0;
        for (int i = k; i < s.length(); i++) {
            // add new step
            switch(s.charAt(i)) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x++; break;
                case 'R': x--; break;
            }
            // remove old step
            switch(s.charAt(i - k)) {
                case 'U': y--; break;
                case 'D': y++; break;
                case 'L': x--; break;
                case 'R': x++; break;
            }
            seen.add(1000000L * x + y);
        }
        return seen.size();
    }
}