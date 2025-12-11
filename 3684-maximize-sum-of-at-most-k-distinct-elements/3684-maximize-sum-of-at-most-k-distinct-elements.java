class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        Set<Integer>st = new HashSet();
        PriorityQueue<Integer>pq = new PriorityQueue<>((a,b) -> b-a);

        for(int num : nums){
            if(!st.contains(num)){
                st.add(num);
                pq.offer(num);
            }
        }

        int[] res = new int[(int)Math.min(pq.size(),k)];

        for(int i=0;i<res.length;i++){
            int kl = pq.poll();
            res[i] = kl;
        }

        return res;
    }
}