class Solution {
    public int maximumPossibleSize(int[] nums) {
        // Increasing order: 1 2 3 4 5 6 7 8 9
        // Here we have to just count how many increasing number we found from start till end of the array amd that is the answer.
        // Note: This works here only, it will not work in other questions because this question is type of maximum subarray problems.
        
        int maxLenCount = 0;
        int prevElement = 0;
        for(int num : nums){
            if(num >= prevElement){
                maxLenCount++;
                prevElement = num;
            }
        }
        return maxLenCount;
    }
}