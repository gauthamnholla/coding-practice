class Solution {
    int[] root;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        root = new int[n];
        for(int i=0;i<n;i++){
            root[i] = i;
        }
        for(List<Integer> pair: pairs){
            int x = pair.get(0);
            int y = pair.get(1);
            union(x,y);
        }
        int count = 0;
        List<Integer> roots = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(root[i] == i) {
                count++;
                roots.add(i);
            }
        }
        Map<Integer, List<Character>> rootToString = new HashMap<>();
        for(int i=0;i<n;i++){
            int rootX = find(i);
            List<Character> temp = rootToString.getOrDefault(rootX, new ArrayList<>());
            temp.add(s.charAt(i));
            rootToString.put(rootX, temp);
        }
        for(Integer i: rootToString.keySet()){
            Collections.sort(rootToString.get(i));
        }
        if(count == 1){
            char[] cArr = s.toCharArray();
            Arrays.sort(cArr);
            return new String(cArr);
        }else{
            StringBuilder out = new StringBuilder();
            for(int i=0;i<n;i++){
                Character c = rootToString.get(find(i)).get(0);
                out.append(c);
                List<Character> temp = rootToString.get(find(i));
                temp.remove(c);
            }
            return out.toString();
        }
    }
    public int find(int x){
        if(x == root[x]){
           return x;
        }
        return root[x] = find(root[x]);
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY){
            root[rootY] = rootX;
        }
    }
}