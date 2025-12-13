class Solution {
    static {
        for(int i = 0; i <= 500; i++){
            getKth(12, 15, 2);
        }
    }
    public static int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> {
            if(!a.getValue().equals(b.getValue())){
                return a.getValue() - b.getValue();
            } else {
                return a.getKey() - b.getKey();
            }
        });

        for(int i = lo; i <= hi; i++){
            int x = i;
            int c = 0;
            while(x != 1){
                if(x%2 == 0){
                    x = x / 2;
                } else {
                    x = 3 * x + 1;
                }
                c++;
            }
            map.put(i, c);
        }
        q.addAll(map.entrySet());
        while(k != 0){
            if(k == 1){
                Map.Entry<Integer, Integer> entry = q.poll();
                return entry.getKey();
            }
            q.poll();
            k--;
        }
        return 0;
    }
}