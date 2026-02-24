class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> ans = new ArrayList<>();
        int[] count1 = new int[101];
        int[] count2 = new int[101];
        int[] count3 = new int[101];

        for(int i = 0 ; i < nums1.length ; i++){
            count1[nums1[i]]++;
        }
        for(int i = 0 ; i < nums2.length ; i++){
            count2[nums2[i]]++;
        }
        for(int i = 0 ; i < nums3.length ; i++){
            count3[nums3[i]]++;
        }

        for(int i = 0 ; i < 101 ; i++){
            if(count1[i]!=0 && count2[i]!= 0 )ans.add(i);
            else if(count1[i]!=0 && count3[i]!= 0 )ans.add(i);
            else if(count2[i]!=0 && count3[i]!= 0 )ans.add(i);
        }

        return ans;
    }
}