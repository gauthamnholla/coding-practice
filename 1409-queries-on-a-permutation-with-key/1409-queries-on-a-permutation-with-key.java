class Solution {
    public int[] processQueries(int[] queries, int m) {
        int[] ans = new int[queries.length];
        int k = 0;
        LinkedList<Integer> perm = new LinkedList<>();
        for (int i = 1; i <= m; i++)
            perm.addLast(i);

        for (int q : queries){
            Iterator<Integer> it = perm.iterator();
            int step = 0;
            while (it.hasNext()){
                Integer value = it.next();
                if (value == q){
                    ans[k++] = step;
                    it.remove();
                    perm.addFirst(value);
                    break;
                }                
                step++;
            }
        }
        return ans;
    }
} 