class Solution {
    public int mostFrequentEven(int[] nums) {
        HashMap <Integer,Integer> frq = new HashMap<>() ;
        for (int i = 0 ; i < nums.length ; i++){
            if (nums[i] % 2 == 0){
            if (frq.containsKey(nums[i])){
                frq.put(nums[i] , frq.get(nums[i]) + 1 ) ;
            }else{
                frq.put(nums[i] ,1);
            }
            }
        }
        int maxValue = 0 ; 
        for (int value : frq.values()){
            if (value > maxValue){
                maxValue = value ;
            }
        }
        ArrayList<Integer> maxFrq = new ArrayList<>() ; 
        for (Map.Entry<Integer,Integer> entry : frq.entrySet()){
                if (entry.getValue() == maxValue){
                    maxFrq.add(entry.getKey());
                }     
         }
        if (maxFrq.size()>1){
         Collections.sort(maxFrq );
         return maxFrq.get(0) ; 
        }else if (maxFrq.size() == 0){
            return -1 ;
        }else{
            return maxFrq.get(0) ; 
        }
    }
}