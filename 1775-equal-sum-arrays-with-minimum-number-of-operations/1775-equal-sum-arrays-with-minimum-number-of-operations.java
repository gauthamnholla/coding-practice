class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1=0,sum2=0;
        for(int i:nums1) sum1+=i;
        for(int j:nums2) sum2+=j;
        if(nums1.length>nums2.length*6 || nums2.length>nums1.length*6){
            return -1;
        }
        if(sum1==sum2) return 0;
        int ope=0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        if(sum1<sum2){
            int i=0,j=nums2.length-1;
            while(sum1!=sum2){
                if(j>=0 && nums2[j]-Math.max(1,nums2[j]+sum1-sum2)>=Math.min(6,nums1[i]+sum2-sum1)-nums1[i]){
                    int p=nums2[j];
                    nums2[j]=Math.max(1,nums2[j]+(sum1-sum2));
                    sum2+=nums2[j]-p;
                    j--;
                }
                else{
                    int p=nums1[i];
                    nums1[i]=Math.min(6,nums1[i]+sum2-sum1);
                    sum1+=nums1[i]-p;
                    i++;
                }
                ope++;
            }
        }
        else{
            int i=nums1.length-1,j=0;
            while(sum1!=sum2){
                 if(i>=0 && nums1[i]-Math.max(1,nums1[i]+sum2-sum1)>=Math.min(6,nums2[j]+sum1-sum2)-nums2[j]){
                    int p=nums1[i];
                    nums1[i]=Math.max(1,nums1[i]+sum2-sum1);
                    sum1+=nums1[i]-p;
                    i--;
                }
                else{
                    int p=nums2[j];
                    nums2[j]=Math.min(6,nums2[j]+sum1-sum2);
                    sum2+=nums2[j]-p;
                    j++;
                }
                ope++;
            }
        }
        return ope;
    }
}