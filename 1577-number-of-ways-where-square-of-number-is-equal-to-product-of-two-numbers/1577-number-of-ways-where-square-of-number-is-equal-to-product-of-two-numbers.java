class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Map<Long,Integer> mp1 = new HashMap<>();
        Map<Long,Integer> mp2 = new HashMap<>(); 
        for(int i=0;i<n1;i++){
            long sq = 1L * nums1[i] * nums1[i];
            int temp = mp1.getOrDefault(sq, 0);
            mp1.put(sq, temp + 1);
        }
        for(int i=0;i<n2;i++){
            long sq = 1L * nums2[i] * nums2[i];
            int temp = mp2.getOrDefault(sq, 0);
            mp2.put(sq, temp + 1);
        }
        int cnt=0;
        for(int i=0;i<n1-1;i++){
            for(int j=i+1;j<n1;j++){
                long prod = 1L * nums1[i] * nums1[j];
                cnt += mp2.getOrDefault(prod, 0);
            }
        }
        for(int i=0;i<n2-1;i++){
            for(int j=i+1;j<n2;j++){
                long prod = 1L * nums2[i] * nums2[j];
                cnt += mp1.getOrDefault(prod, 0);
            }
        }
        return cnt;
    }
}