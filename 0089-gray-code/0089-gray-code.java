class Solution {
    public List<Integer> grayCode(int n) {
        int size = 1 << n; // Total numbers = 2^n
        List<Integer> result = new ArrayList<>();

        // Generate Gray code sequence
        for (int i = 0; i < size; i++) {
            result.add(i ^ (i >> 1)); // Gray code formula
        }

        return result; // Return the sequence
    }
}
