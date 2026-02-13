class Solution {
    int[][] arr;
    int[][] rightToLeft;
    int[][] leftToRight;
    int[][] upToDown;
    int[][] downToUp;
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        leftToRight = new int[n][n];
        rightToLeft = new int[n][n];
        upToDown = new int[n][n];
        downToUp = new int[n][n];
        arr = new int[n][n];

        for(int[] ele: arr){
            Arrays.fill(ele, 1);
        }
        for(int[] ele: mines){
            arr[ele[0]][ele[1]] = 0;
        }

        LR(n);
        RL(n);

        int ans = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] == 1){
                    int up = 0;
                    if(i > 0) up = upToDown[i-1][j];
                    int down = 0;
                    if(i < n-1) down = downToUp[i+1][j];
                    int left = 0;
                    if(j > 0) left = leftToRight[i][j-1];
                    int right = 0;
                    if(j < n-1) right = rightToLeft[i][j+1];

                    int minSide = Math.min(left, Math.min(down, Math.min(right, up)));

                    ans = Math.max(ans, minSide+1);
                }
            }
        }

        return ans;
    }

    public void LR(int len){
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(arr[i][j] != 0){
                    if(j == 0) leftToRight[i][j] += arr[i][j];
                    else leftToRight[i][j] = (arr[i][j]+leftToRight[i][j-1]);
                }
                if(arr[j][i] != 0){
                    if(j == 0) upToDown[j][i] += arr[j][i];
                    else upToDown[j][i] = (arr[j][i]+upToDown[j-1][i]);
                }
            }
        }
    }

    public void RL(int len){
        for(int i = 0; i < len; i++){
            for(int j = len-1; j >= 0; j--){
                if(arr[i][j] != 0){
                    if(j == len-1) rightToLeft[i][j] += arr[i][j];
                    else rightToLeft[i][j] = (arr[i][j]+rightToLeft[i][j+1]);
                }
                if(arr[j][i] != 0){
                    if(j == len-1) downToUp[j][i] += arr[j][i];
                    else downToUp[j][i] = (arr[j][i]+downToUp[j+1][i]);
                }
            }
        }
    }
}