class Solution {
    class Pair implements Comparable<Pair>{
        int val;
        int lab;
        Pair(int val , int lab){
            this.val = val;
            this.lab = lab;
        }
        @Override
        public int compareTo(Pair p){
            return p.val-this.val;
        }
    }
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++){
            pq.add(new Pair(values[i] , labels[i]));
        }
        HashMap<Integer , Integer> map = new HashMap<>();
        int sum =0 ;
        while(!pq.isEmpty() && numWanted != 0){
            Pair p = pq.poll();
            if(!map.containsKey(p.lab) || map.get(p.lab) < useLimit){
               sum += p.val;
                map.put(p.lab , map.getOrDefault(p.lab , 0)+1);
                numWanted--;
            }
        }
        return sum;
    }
}