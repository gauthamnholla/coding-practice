class Solution {

    static int mod = 1_000_000_007 ;

    public int goodSubtreeSum(int[] vals, int[] par) {


        int n = vals.length ;

        List<List<Integer>> tree = new ArrayList<>() ;
        for (int i = 0 ; i < n ; i ++) 
        {
            tree.add(new ArrayList<>()) ;
        }

        int root = -1 ;

        for (int u = 0 ; u < n ; u ++) 
        {
            int v = par[u] ;

            if (v == -1) 
            {
                root = u ;
                continue ;
            }

            tree.get(v).add(u) ;
        }

        int[] res = new int[1] ;

        dfs(root, tree, vals, res) ;

        return res[0] % mod ;
    }

    
    List<Integer> dfs(int root, List<List<Integer>> tree, int[] vals, int[] res) 
    {
        List<Integer> nums = new ArrayList<>() ;

        nums.add(vals[root]) ;

        for (int child : tree.get(root)) 
        {
            nums.addAll(dfs(child, tree, vals, res)) ;
        }

        res[0] = (res[0] + calcMaxScore(nums)) % mod ;

        return nums;
    }

    int calcMaxScore(List<Integer> nums) 
    {
        long[] dp = new long[1 << 10] ;

        for (int num : nums) 
        {
            int mask = digitMask(num) ;

            if (mask == -1) continue ;

            long[] newDp = dp.clone() ;

            for (int used = 0 ; used < (1 << 10) ; used ++) 
            {

                if ((used & mask) == 0) 
                {
                    int newMask = used | mask ;
                    newDp[newMask] = Math.max(newDp[newMask], (dp[used] + num) % mod) ;
                }
            }

            dp = newDp ;
        }

        long max = 0 ;

        for (long v : dp) 
        {
            max = Math.max(max, v % mod) ;
        }

        return (int)(max % mod) ;
    }

    int digitMask(int num) 
    {
        if (num == 0) return 1 << 0 ;

        int mask = 0 ;

        while (num > 0) 
        {
            int d = num % 10 ;
            if ((mask & (1 << d)) != 0) return -1 ; 
            mask |= (1 << d) ;
            num /= 10 ;
        }

        return mask ;
    }
}