class Solution {
    
    public int countDistinctIntegers(int[] nums) {
        
        //creating hashset 
        HashSet<Integer> ans = new HashSet<>();
        for(int x : nums){
            //adding elements in hashset
            ans.add(x);

            //reversing each elements of nums 
            int rev =0;
            while(x!=0){
                rev += x % 10 ;
                x /= 10 ;
                if(x>0) rev *= 10;
            }

            //Adding reverse element in hashset
            ans.add(rev);
        }

        return ans.size();
    }
}