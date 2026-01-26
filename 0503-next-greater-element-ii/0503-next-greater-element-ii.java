class Solution {
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] nge = new int[n];
        nge[n-1]  = -1;
        Stack<Integer> st = new Stack<>();
       // bs ye loop extra h nge se jisme apun saare ele ko stack mei push krege 
       for(int i=n-1;i>=0;i--){
        st.push(arr[i]);
       }
        for(int i=n-1;i>=0;i--){
               while(st.size()>0 && arr[i]>=st.peek()) st.pop();
               if(st.size()==0) nge[i]=-1;
               else nge[i]= st.peek();
               st.push(arr[i]);
        }
        return nge;
    }
}