class Solution {

    Integer dp[];
    public int deleteAndEarn(int[] nums) {

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) 
        {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int size = map.size();
        int arr[]=new int[size];
        int sum[]=new int[size];
        dp=new Integer[size];

        int idx = 0;
        for (int key : new TreeSet<>(map.keySet())) 
        {
            arr[idx] = key;
            sum[idx] = key * map.get(key); 
            idx++;
        }

        return findmaxsum(arr,sum,0,size);
    }

    int findmaxsum(int[] arr, int[] sum, int i, int n) 
    {
        if (i >= n) return 0;

        if(dp[i] != null) return dp[i];

        int pick = sum[i];

        if (i + 1 < n && arr[i + 1] == arr[i] + 1)
            pick += findmaxsum(arr, sum, i + 2, n);
        else
            pick += findmaxsum(arr, sum, i + 1, n);

        int nopick = findmaxsum(arr, sum, i + 1, n);

        return dp[i]=Math.max(pick, nopick);
    }
}