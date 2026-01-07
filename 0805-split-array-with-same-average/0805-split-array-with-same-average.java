class Solution {

    private Map<Integer, TreeSet<Integer>> generator(int[] nums, int start, int end) {
        Map<Integer,TreeSet<Integer>> result = new HashMap<>();
        int n=end-start+1;
        int total=1<<n; // total subsets = 2^n

        for(int mask=0;mask<total;mask++){
            int sum=0;
            int count=0;
            for(int j=0;j<n;j++){
                if((mask & (1<<j))!=0){
                    sum+=nums[start+j];
                    count++;
                }
            }
            if(!result.containsKey(count)){
                result.put(count,new TreeSet<>());
            }
            result.get(count).add(sum);
        }
        return result;
    }

    public boolean splitArraySameAverage(int[] nums) {
        int n=nums.length;
        if(n==1){
            return false;
        }
        if(n==2){
            return nums[0]==nums[1];
        }

        int sum=Arrays.stream(nums).sum();

        Map<Integer,TreeSet<Integer>> subset1=new HashMap<>();
        Map<Integer,TreeSet<Integer>> subset2=new HashMap<>();

        subset1=generator(nums,0,n/2);
        subset2=generator(nums,n/2+1,n-1);


        for(int count=1;count<=n-1;count++){
            if((sum*count)%n!=0){
                continue;
            }       
            int T=(sum*count)/n;
            for(Integer key : subset1.keySet()) {
                if(key>count){
                    break;
                }
                int remainingEle=count-key;
                Set<Integer> s1=subset1.get(key);
                Set<Integer> s2=subset2.get(remainingEle);
                if(s2==null){
                    continue;
                }
                for(Integer x: s1){
                    int compl=T-x;
                    if(s2.contains(compl)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}