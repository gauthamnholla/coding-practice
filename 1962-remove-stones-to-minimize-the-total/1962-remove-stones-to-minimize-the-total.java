class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        for(int i:piles) pq.add(i);
        for(int i=0;i<k;i++){
            int updatedVal=pq.poll();
            updatedVal=(int)Math.ceil(updatedVal/2.0);
            pq.add(updatedVal);
        }
        int ans=0;
        while(!pq.isEmpty()){
            ans+=pq.poll();
        }
        return ans;
    }
}