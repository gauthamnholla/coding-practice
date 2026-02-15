class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        ArrayDeque<Integer> dq  = new ArrayDeque<>();
        int n= seq.length();
        int[]ans =  new int[n];
        for(int ind= 0;ind<n;ind++){
            char c = seq.charAt(ind);
            if(c=='('){
                if(dq.isEmpty()){
                    dq.offer(0);
                    ans[ind]=0;
                }
                else{
                    dq.offer(dq.peekLast()^1);
                    ans[ind] = dq.peekLast();
                }
            }
            else{
                ans[ind]= dq.pollLast();
            }
        }
        return ans;
    }
}