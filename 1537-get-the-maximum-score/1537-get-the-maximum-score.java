class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        long[] prefixSum1 = new long[nums1.length + 1];
        prefixSum1[0] = 0;

        long[] prefixSum2 = new long[nums2.length + 1];
        prefixSum2[0] = 0;

        Fb(prefixSum1, nums1);
        Fb(prefixSum2, nums2);

        int si1 = 0;
        int si2 = 0;

        int ei1 = 0;
        int ei2 = 0;

        long sum = 0;
        while(ei1 < nums1.length && ei2 < nums2.length) {
            if(nums1[ei1] < nums2[ei2]) {
                ei1++;
            }
            else if(nums1[ei1] > nums2[ei2]) {
                ei2++;
            }
            else {
                long sum1 = prefixSum1[ei1 + 1] - prefixSum1[si1];
                long sum2 = prefixSum2[ei2 + 1] - prefixSum2[si2];

                sum += Math.max(sum1, sum2);
                ei1++;
                ei2++;

                si1 = ei1;
                si2 = ei2;
            }
        }

        long sum1 = prefixSum1[prefixSum1.length - 1] - prefixSum1[si1];
        long sum2 = prefixSum2[prefixSum2.length - 1] - prefixSum2[si2];

        sum += Math.max(sum1, sum2);
        return (int) (sum % 1000_000_007);
    }

    public void Fb(long[] nums, int[] arr) {
        for(int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + arr[i - 1];
        }   
    }
}