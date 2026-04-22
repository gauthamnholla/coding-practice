class Solution {
    static HashMap<Long, Integer> dp;
    static Boolean[][][][] dp2;

    public int maxProduct(int[] nums, int k, int limit) {
        dp = new HashMap<>();
        dp2 = new Boolean[nums.length + 1][1801][2][2];
        int ans = -1;
        if(k > -36 && k < 36) ans = solve(0, 0, 1, 1, nums, k, limit, 0); 

        boolean doesContainsZero = canBe(0, 0, 0, nums, 1, k);
        return ans == -1 ? (doesContainsZero ? 0 : ans) : ans;
    }

    static boolean canBe(int i, int sum, int zeroTaken, int[] arr, int isOdd, int req) {
        if (i >= arr.length) return (sum == req && zeroTaken == 1);

        if (dp2[i][sum + 900][zeroTaken][isOdd] != null) return dp2[i][sum + 900][zeroTaken][isOdd];

        boolean take;
        if (isOdd == 1) {
            take = canBe(i + 1, sum + arr[i], zeroTaken | (arr[i] == 0 ? 1 : 0), arr, 0, req);
        } else {
            take = canBe(i + 1, sum - arr[i], zeroTaken | (arr[i] == 0 ? 1 : 0), arr, 1, req);
        }

        boolean notTake = canBe(i + 1, sum, zeroTaken, arr, isOdd, req);
        return dp2[i][sum + 900][zeroTaken][isOdd] = (take || notTake);
    }

    static int solve(int i, int sum, int pro, int isOdd, int[] arr, int req, int limit, int start) {
        if (sum <= -40 || sum >= 40 || pro > limit) return -1;

        if (i >= arr.length) return (sum == req && pro <= limit && start == 1) ? pro : -1;

        long key = encodeState(i, sum, pro, isOdd, start, limit);
        if (dp.containsKey(key)) return dp.get(key);

        int pick = -1;
        if (arr[i] != 0) {
            if (isOdd == 1) {
                pick = solve(i + 1, sum + arr[i], pro * arr[i], 0, arr, req, limit, 1);
            } else {
                pick = solve(i + 1, sum - arr[i], pro * arr[i], 1, arr, req, limit, 1);
            }
        }
        
        int notPick = solve(i + 1, sum, pro, isOdd, arr, req, limit, start);
        int res = Math.max(pick, notPick);
        dp.put(key, res);
        return res;
    }

    static long encodeState(int i, int sum, int pro, int isOdd, int start, int limit) {
        long F3 = 4L;
        long F2 = ((long) limit + 1) * F3;
        long F1 = 81L * F2;
        return i * F1 + (sum + 40) * F2 + pro * F3 + (isOdd * 2L + start);
    }
}