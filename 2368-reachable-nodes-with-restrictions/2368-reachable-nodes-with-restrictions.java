class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> res_set = new HashSet<>(); for(int num : restricted) res_set.add(num);
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for(int[] e : edges)
        {
            map.putIfAbsent(e[0], new ArrayList<>());
            map.putIfAbsent(e[1], new ArrayList<>());

            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
        }
        Set<Integer> res = new HashSet<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        res.add(0);
        while(!q.isEmpty())
        {
            ArrayList<Integer> v = map.get(q.poll());
            for(int num : v)
            {
                if(!res_set.contains(num) && !res.contains(num))
                {
                    q.offer(num);
                    res.add(num);
                }
            }
        }

        return res.size();

       
    }
}