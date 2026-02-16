class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;

        HashMap<Integer,List<Integer>> map = new HashMap<>(); //[size,people list]
        List<Integer> sizes = new ArrayList<>();

        for(int i=0;i<n;i++){
            List<Integer> data = new ArrayList<>();

            if(map.containsKey(groupSizes[i])) data = map.get(groupSizes[i]);
            else sizes.add(groupSizes[i]);
            data.add(i);
            map.put(groupSizes[i],data);
        }
        Collections.sort(sizes);

        List<List<Integer>> result = new ArrayList<>();
        for(int size : sizes){
            List<Integer> data = map.get(size);
            while(!data.isEmpty()){
                int i =0;
                List<Integer> ans = new ArrayList<>();
                while(i<size){
                    ans.add(data.remove(0));
                    i++;
                }
                result.add(ans);
            }
        }
        return result;
    }
}