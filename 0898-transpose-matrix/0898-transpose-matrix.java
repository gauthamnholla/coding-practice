class Solution {
    public int[][] transpose(int[][] matrix) {
        List<List<Integer>> resList = new ArrayList<>();

        for (int c = 0; c < matrix[0].length; c++) {
            List<Integer> temp = new ArrayList<>();

            for (int r = 0; r < matrix.length; r++) {
                temp.add(matrix[r][c]);
            }

            resList.add(temp);
        }

        // Convert List<List<Integer>> to int[][]
        int[][] res = new int[resList.size()][];
        for (int i = 0; i < resList.size(); i++) {
            List<Integer> row = resList.get(i);
            res[i] = row.stream().mapToInt(Integer::intValue).toArray();
        }

        return res;        
    }
}