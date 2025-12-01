class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int diffComparison = Integer.compare(Math.abs(a[0] - x), Math.abs(b[0] - x));
            if (diffComparison != 0) {
                return diffComparison;
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });
        for (int i = 0; i < arr.length; i++) {
            pq.add(new int[] { arr[i], i, Math.abs(arr[i] - x) });
        }
        List<Integer> list = new ArrayList<>();
        while (k-- > 0) {
            list.add(pq.poll()[0]);
        }
        Collections.sort(list);
        return list;
        // List<Integer> list=new ArrayList<>();
        // for(int i=0;i<k;i++)
        // {
        //     list.add(arr[i]);
        // }
        // for(int i=k;i<arr.length;i++)
        // {
        //     if(Math.abs(list.get(0)-x)<Math.abs(arr[i]-x)) return list;
        //     else if(Math.abs(list.get(0)-x)==Math.abs(arr[i]-x)){
        //         continue;
        //     }
        //     else{
        //         list.remove(list.size()-1);
        //         list.add(arr[i]);
        //     }
        // }
        // return list;
    }
}