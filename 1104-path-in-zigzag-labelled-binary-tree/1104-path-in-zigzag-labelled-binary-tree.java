class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> l = new ArrayList<>();
        int i = Integer.toBinaryString(label).length()-1;
        while(label>1) {
            l.add(label);
            i--;
            label = label>>1;
            label = label^((1<<i)-1);
        }
        l.add(1);
        Collections.reverse(l);
        return l;
    }
}