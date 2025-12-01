class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        int[] ans = new int[n-k+1];
        Stack<Integer> st = new Stack<>();
        int[] nge=new int[n];
        st.push(n-1);
        nge[n-1]=n;
        for (int i=n-2;i>=0;i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            if (st.size()==0) nge[i]=n;
            else nge[i]=st.peek();
            st.push(i);
        }
        int j=0,z=0;
        for (int i=0;i<n-k+1;i++) {
            // j jabh tak i+k se chotta hai tabh tak vahin rehene
            // do baar baar j=i mat karo
            if (j>=i+k) j=i;
            int max=nums[j];
            while (j<i+k) {
                max=nums[j];
                j=nge[j];
            }
            ans[z++]=max;
        }
        return ans;
    }
}