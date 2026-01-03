class Solution {
    public int hIndex(int[] citations) {
        int ans=0,i=1,j=citations.length;
        while(i<=j){
            int mid=i+(j-i)/2;
            if(citations[citations.length-mid]>=mid){
                ans=mid;
                i=mid+1;
            }
            else{
                j=mid-1;
            }
        }
        return ans;
    }
}