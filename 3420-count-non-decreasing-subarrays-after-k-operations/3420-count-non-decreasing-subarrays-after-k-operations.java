class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        Deque<int []> dq = new LinkedList<>();

        int i=nums.length - 1;
        int j=nums.length - 1;
        long operations = 0;
        long ans = 0;

        while(i>-1){
            int pops = 0;
            while(dq.size() > 0 && dq.peekFirst()[0] <= nums[i]){
                int [] remove = dq.poll();
                pops+=remove[1];
                operations += (long)remove[1] * (long)(nums[i] - remove[0]);    
            }
            dq.offerFirst(new int[] {nums[i], pops+1});

            if(operations<=(long)k){
                ans+=(long)(j-i+1);
            }else{
                while(i<j && operations > (long) k){
                    int [] remove = dq.pollLast();
                    operations -= (long)(remove[0] - nums[j]);
                    remove[1]--;
                    if(remove[1] != 0){
                        dq.offerLast(remove);
                    }
                    j--;
                }

                if(operations<= (long) k){
                    ans += (long)(j-i+1);
                }
            }

            // System.out.println(i + " " + j + " " + operations + " " + ans);
            i--;
        }

        return ans;

    }
}