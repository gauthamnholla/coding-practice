class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> s = new HashSet<>();
        int[][] set = {p1, p2, p3, p4};

        for(int i = 0; i < 4; i++){
            for(int j = i + 1; j<4; j++){

                if(Arrays.equals(set[i], set[j])){
                    return false;
                }
                int dx = set[i][0] - set[j][0];
                int dy = set[i][1] - set[j][1];

                int dist = dx * dx + dy * dy;
                s.add(dist);
            }
        }
        return s.size() == 2;
    }
}