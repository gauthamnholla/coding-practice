class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        boolean flag = false;
        int n = packages.length;
        int m = boxes.length;
        long min = Long.MAX_VALUE, mod = 1_000_000_007;
        long[] pre = new long[n];
        
        Arrays.sort(packages);
        
        pre[0] = packages[0];
        
        for(int i = 1; i < n; ++i)
            pre[i] = pre[i - 1] + packages[i];
        
        for(int i = 0; i < m; ++i) {
            Arrays.sort(boxes[i]);
            
            if(boxes[i][boxes[i].length - 1] >= packages[n - 1]) 
                flag = true;
        }
        
        if(!flag)
            return -1;
        
        for(int[] a : boxes) {
            m = a.length;
            int prev = -1;
            long ans = 0;
            
            if(a[m - 1] < packages[n - 1])
                continue;
            
            for(int v : a) {
                int x = prev + 1, y = n - 1, now = prev;
                
                while(x <= y) {
                    int mid = (x + y) >> 1;
                    
                    if(packages[mid] <= v) {
                        now = mid;
                        x = mid + 1;
                    }
                    else {
                        y = mid - 1;
                    }
                }
                
                long prefix = (now >= 0 ? pre[now] : 0) - (prev >= 0 ? pre[prev] : 0);
                ans += 1l * (now - prev) * v - prefix;
                prev = now;
            }
            
            min = Math.min(ans, min);
        }
        
        return (int) (min % mod);
    }
}