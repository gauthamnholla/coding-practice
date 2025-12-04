class Solution {

    class Pair{

        int num ;
        int freq ;

        public Pair(int num , int freq){
            this.num = num ;
            this.freq = freq ;
        }
    }

    class CustomComparator implements Comparator<Pair>{

        public int compare(Pair a , Pair b){

            return Integer.compare(b.freq  , a.freq);
        }
    }
    public int minSetSize(int[] arr) {

        HashMap<Integer , Integer> hm = new HashMap<>();

        for(int i = 0 ; i<arr.length ; i++){
            hm.put(arr[i] , hm.getOrDefault(arr[i] ,0)+1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new CustomComparator());

        for(Integer i : hm.keySet()){
            pq.offer(new Pair(i , hm.get(i)));
        }

        int len = 0 ;
        int count = 0 ;


        while( len*2 < arr.length){

            count++;
            Pair p = pq.poll();
            int freq = p.freq ;

            len += freq ;
        }

        return count ; 

 
    }
}