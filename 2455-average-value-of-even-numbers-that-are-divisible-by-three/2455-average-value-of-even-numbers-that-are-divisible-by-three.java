class Solution {
    public int averageValue(int[] nums){
        return (int) Arrays.stream(nums)      // convert array → IntStream
            .filter(ele -> ele % 6 == 0)      // keep numbers divisible by 6
            .average()                        // compute average as OptionalDouble
            .orElse(0);                       // return 0 if no such numbers
    }
}

// class Solution {
//     public int averageValue(int[] nums) {
//         int sum=0;int count=0;
//         for(int i=0;i<nums.length;i++){
//             if(nums[i]%6==0){
//                 sum=sum+nums[i];
//                 count++;
//             }
//         }
//         if (sum==0){
//             return 0;
//         }
//         return sum/count;
        
//     }
// }

// class Solution {
//     public int averageValue(int[] nums) {
//         List<Integer> ls = new ArrayList<>();
//         for(int ele : nums){
//             if(ele%3==0 && ele%2==0){
//                 ls.add(ele);
//             }
//         }
//         if(ls.size()==0){
//             return 0;
//         }
//         int sum  = ls.stream().mapToInt(Integer::intValue).sum();
//         return (int)sum/ls.size();
//     }
// }