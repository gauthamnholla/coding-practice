class Solution {
    public int minimumTotal(List<List<Integer>> tri) {
        // Start from the 2nd last row and move upwards
        for (int i = tri.size() - 2; i >= 0; i--) {
            // For each element in row i
            for (int j = 0; j < tri.get(i).size(); j++) {
                // Update tri[i][j] by adding the minimum of the two possible paths
                // from the row directly below: tri[i+1][j] and tri[i+1][j+1]
                tri.get(i).set(
                    j,
                    tri.get(i).get(j) + Math.min(
                        tri.get(i + 1).get(j),       // value from directly below
                        tri.get(i + 1).get(j + 1)    // value from diagonal right below
                    )
                );
            }
        }
        // After processing, tri[0][0] will contain the minimum path sum
        return tri.get(0).get(0);
    }
}
