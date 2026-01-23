class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        Integer[] mem = new Integer[1<<nums.length];
        int min =  min(nums,k,(1<<nums.length)-1,mem);
        return min==Integer.MAX_VALUE?-1:min;
    }

    public int min(int[] nums,int k,int mask,Integer[] mem){
        if(mask==0)
            return 0;
        if(mem[mask]!=null)
            return mem[mask];
        int min = Integer.MAX_VALUE;
        a:
        for(int sub = mask;sub>0;sub = (sub-1)&mask){
            if(Integer.bitCount(sub)!=nums.length/k)
                continue;

            TreeSet<Integer> set = new TreeSet<>();
            for(int i = 0;i<nums.length;i++){
                if((sub&(1<<i))>0){
                    if(!set.add(nums[i]))
                        continue a;

                }
            }
            int next = min(nums,k,mask&(~sub),mem);
            if(next!=Integer.MAX_VALUE){
                min = Math.min(min,set.last()-set.first()+next);
            }
        }
        mem[mask] = min;
        return min;
    }
}