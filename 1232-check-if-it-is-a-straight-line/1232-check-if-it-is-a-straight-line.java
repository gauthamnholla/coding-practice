class Solution {
    public boolean checkStraightLine(int[][] arr) {
        for (int i = 2; i < arr.length; i++)
            if ((arr[i][0] - arr[0][0]) * (arr[1][1] - arr[0][1]) !=
                (arr[i][1] - arr[0][1]) * (arr[1][0] - arr[0][0]))
                return false;
        return true;
    }
}