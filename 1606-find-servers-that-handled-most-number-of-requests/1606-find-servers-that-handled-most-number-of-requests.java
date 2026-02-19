class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int len = arrival.length;
        long[][] comb = new long[len][3];
        for(int i = 0; i < len; i++){
            comb[i][0] = arrival[i];
            comb[i][1] = arrival[i] + load[i];
            comb[i][2] = -1;
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        int[] room = new int[k];
        ArrayList<Integer> free = new ArrayList<>();
        for(int i = 0;i < k;i++) free.add(i);

        for(int i = 0;i < len;i++){
            while(!pq.isEmpty() && pq.peek()[1] <= comb[i][0]){
                int rm = (int) pq.poll()[2];
                int ind = binary(free, 0, free.size()-1, rm);
                free.add(ind, rm);
            }
            int tar = i%k;
            int ind = binary(free, 0, free.size()-1, tar);

            if(ind < free.size()){
                comb[i][2] = free.get(ind);
                room[free.get(ind)]++;
                free.remove(ind);
                pq.offer(comb[i]);
            }
            else if(!free.isEmpty()){
                comb[i][2] = free.get(0);
                room[free.get(0)]++;
                free.remove(0);
                pq.offer(comb[i]);
            }
        }

        int fq = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < k; i++){
            if(room[i] > fq){
                fq = room[i];
                ans.clear();
                ans.add(i);
            }
            else if(room[i] == fq){
                ans.add(i);
            }
        }

        return ans;
    }

    public int binary(List<Integer> free, int st, int ed, int tar){
        while(st <= ed){
            int mid = st + (ed - st) / 2;
            if(free.get(mid) < tar) st = mid + 1;
            else ed = mid - 1;
        }

        return st;
    }
}