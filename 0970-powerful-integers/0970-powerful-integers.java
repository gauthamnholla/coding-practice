class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer>res = new ArrayList<>();
        Set<Integer>set = new HashSet<>();
        for (int xPow=1;xPow<bound;xPow*=x){ 
            for (int yPow=1;yPow+xPow<=bound;yPow*=y){
                set.add(yPow+xPow);
                if (y==1) break;
            }
            if (x==1)break;
        }
        res.addAll(set);
        return res;
    }
}